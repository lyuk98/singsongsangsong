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
        enable_dynamic_field=False
    )
    schema.add_field(
        field_name="id",
        datatype=DataType.INT64,
        is_primary=True
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
