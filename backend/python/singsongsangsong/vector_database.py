# -*- coding: utf-8 -*-

"""
Vector 데이터베이스 관리
=======================

Milvus 데이터베이스와 관련된 기능을 제공합니다
"""

from typing import Union
import os
from dotenv import load_dotenv
from pymilvus import MilvusClient, DataType, CollectionSchema
from pymilvus.milvus_client import IndexParams

load_dotenv()

def get_client() -> MilvusClient:
    """Milvus client를 생성합니다

    Returns
    -------
    MilvusClient
        Milvus client 객체
    """

    return MilvusClient(uri=os.environ.get("MILVUS_URI"))

def create_schema() -> CollectionSchema:
    """Collection의 schema를 생성합니다

    Returns
    -------
    CollectionSchema
        Schema 객체
    """

    schema = MilvusClient.create_schema(
        auto_id=False,
        enable_dynamic_field=False
    )
    schema.add_field(
        field_name="id",
        datatype=DataType.INT64,
        is_primary=True
    )
    schema.add_field(
        field_name="is_published",
        datatype=DataType.BOOL
    )
    schema.add_field(
        field_name="vector",
        datatype=DataType.FLOAT_VECTOR,
        dim=2048
    )

    return schema

def create_index_params(client: MilvusClient) -> IndexParams:
    """Index 생성에 필요한 parameter를 준비합니다

    Parameters
    ----------
    client : MilvusClient
        Milvus client

    Returns
    -------
    IndexParams
        Index parameters
    """

    index_parameters = client.prepare_index_params()
    index_parameters.add_index(
        field_name="id",
        index_type="STL_SORT"
    )
    index_parameters.add_index(
        field_name="vector",
        index_type="IVF_FLAT",
        metric_type="IP",
        params={ "nlist": 16384 }
    )

    return index_parameters

def create_collection(
    client: MilvusClient,
    schema: Union[CollectionSchema, None]=None,
    index_params: Union[IndexParams, None]=None
):
    """Collection을 생성합니다

    `embeddings`의 이름을 가진 collection을 생성합니다

    Parameters
    ----------
    client : MilvusClient
        Milvus client
    schema : CollectionSchema | None
        Schema 객체
    index_params : IndexParams | None
        Index parameters
    """

    if schema is None:
        schema = create_schema()
    if index_params is None:
        index_params = create_index_params(client)

    client.create_collection(
        collection_name="embeddings",
        schema=schema,
        index_params=index_params
    )

def insert_embedding(
    song_id: int,
    embedding: list[float],
    client: Union[MilvusClient, None]=None
) -> dict:
    """유사도 분석을 위한 embedding을 데이터베이스에 삽입합니다

    Parameters
    ----------
    song_id : int
        삽입할 곡의 ID
    embedding : list[float]
        곡의 정보를 담고 있는 embedding
    client : MilvusClient | None
        Milvus client; `None`일 시 새로 생성
    
    Returns
    -------
    dict
        삽입 결과 정보
    """

    if client is None:
        client = get_client()

    return client.insert(
        collection_name="embeddings",
        data=[
            {
                "id": song_id,
                "is_published": False,
                "vector": embedding
            }
        ]
    )

def get_record(song_id: int, client: Union[MilvusClient, None]=None) -> Union[dict, None]:
    """주어진 ID를 가진 곡 정보를 반환합니다

    결과가 존재하지 않을 시 `None`을 반환합니다

    Parameters
    ----------
    song_id : int
        검색할 곡의 ID
    client : MilvusClient | None
        Milvus client; `None`일 시 새로 생성
    """

    if client is None:
        client = get_client()

    result = client.get(collection_name="embeddings", ids=song_id)

    if result:
        return result[0]

    return None
