import os
import time
from itertools import count, zip_longest
from dotenv import load_dotenv
import numpy as np
import librosa
from pymilvus import MilvusClient, DataType

def grouper(iterable, n, *, incomplete="fill", fillvalue=None):
    """Collect data into non-overlapping fixed-length chunks or blocks

    grouper("ABCDEFG", 3, fillvalue="x") --> ABC DEF Gxx
    grouper("ABCDEFG", 3, incomplete="strict") --> ABC DEF ValueError
    grouper("ABCDEFG", 3, incomplete="ignore") --> ABC DEF
    """

    args = [iter(iterable)] * n

    if incomplete == "fill":
        return zip_longest(*args, fillvalue=fillvalue)
    if incomplete == "strict":
        return zip(*args, strict=True)
    if incomplete == "ignore":
        return zip(*args)

    raise ValueError("Expected fill, strict, or ignore")

def load_audio(path: str, sr: float) -> np.ndarray:
    return librosa.load(path, sr=sr, mono=True)[0]

def get_mfcc(y: np.ndarray, sr: float) -> np.ndarray:
    return librosa.feature.mfcc(y=y, sr=sr, n_mfcc=1)[0]

# Parameters
REFERENCE_PATH = "data/train/"

SAMPLE_RATE = 22050
SPLIT_SAMPLES = librosa.time_to_samples(10, sr=SAMPLE_RATE)
NUM_DIMENSIONS = 431

load_dotenv()

client = MilvusClient(
    uri=os.environ.get("MILVUS_URI")
)

schema = MilvusClient.create_schema(
    auto_id=True,
    enable_dynamic_field=False
)
schema.add_field(field_name="id", datatype=DataType.INT64, is_primary=True)
schema.add_field(field_name="song_name", datatype=DataType.VARCHAR, max_length=128)
schema.add_field(field_name="order", datatype=DataType.INT32)
schema.add_field(field_name="vector", datatype=DataType.FLOAT_VECTOR, dim=NUM_DIMENSIONS)

index_parameters = client.prepare_index_params()
index_parameters.add_index(
    field_name="id",
    index_type="STL_SORT"
)
index_parameters.add_index(
    field_name="vector",
    index_type="IVF_FLAT",
    metric_type="IP",
    params={ "nlist": 128 }
)

client.create_collection(
    collection_name="mfcc_test",
    schema=schema,
    index_params=index_parameters
)

print(client.get_load_state(collection_name="mfcc_test"))

with os.scandir(REFERENCE_PATH) as it:
    for entry in it:
        if entry.name.startswith(".") or not entry.is_file():
            continue

        reference_audio = load_audio(entry.path, sr=SAMPLE_RATE)

        entities = []
        order = count(start=0, step=1)

        for segment in grouper(reference_audio, SPLIT_SAMPLES, incomplete="fill", fillvalue=0.0):
            entities.append(
                {
                    "song_name": entry.name,
                    "order": next(order),
                    "vector": get_mfcc(y=np.array(segment), sr=SAMPLE_RATE)
                }
            )

        print(f"Adding {entry.name} ({len(entities)} chunks) to collection")

        add_start = time.perf_counter_ns()
        client.insert(
            collection_name="mfcc_test",
            data=entities
        )
        add_end = time.perf_counter_ns()

        print(f"Adding {entry.name} took {(add_end - add_start) // 1000000} milliseconds")

FILENAME = "data/audio.wav"

audio = load_audio(path=FILENAME, sr=SAMPLE_RATE)

print("Searching similarity")

search_start = time.perf_counter_ns()
results = client.search(
    collection_name="mfcc_test",
    data=[
        get_mfcc(
            y=np.array(chunk),
            sr=SAMPLE_RATE
        ) for chunk in grouper(audio, SPLIT_SAMPLES, incomplete="fill", fillvalue=0.0)
    ],
    limit=3,
    search_params={},
    group_by_field="song_name",
    output_fields=["song_name", "order"]
)
search_end = time.perf_counter_ns()

print(f"Similarity search took {(search_end - search_start) // 1000000} milliseconds")

time_count = count(0, 1)
for result_set in results:
    current = next(time_count)
    print(f"Result for chunk {current} ({current * 10}-{(current + 1) * 10} seconds)")

    for result in result_set:
        print(
            f"  {result['entity']['song_name']}"
            f"({result['entity']['order'] * 10}-{(result['entity']['order'] + 1) * 10})"
            f" - Distance: {result['distance']}"
        )
