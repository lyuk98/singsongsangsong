import React, { useEffect } from "react";
import axios from "axios";
import { defer, useLoaderData, useSearchParams } from "react-router-dom";

import { SearchType, SearchParmasType } from "../../utils/types";
import MusicTable from "../../components/public/music/MusicTable";
import styles from "./SearchResultPage.module.css";
import { getSearchResult } from "../../utils/api/api";
import { useAxios } from "../../hooks/api/useAxios";

const SearchResultPage = () => {
  const [searchParams, setSearchParams] = useSearchParams();

  const keyword = searchParams.get("keyword");
  const genre = searchParams.get("genre");
  const bpm = searchParams.get("bpm");
  const atmosphere = searchParams.get("atmosphere");
  const sort = searchParams.get("sort");

  const userSearchParams: SearchParmasType = {
    keyword,
    genre,
    bpm,
    atmosphere,
    sort,
  };

  const { response, refetch, isLoading } = useAxios({
    method: "GET",
    url: "/music-playlist/search",
    data: {
      keyword,
      genre,
      atmosphere,
      bpm,
      sort,
    },
  });

  useEffect(() => {
    console.log(userSearchParams);
    console.log(getSearchResult(userSearchParams));
  }, []);

  return (
    <div className={`w-100 flex-col px-main my-main gap-15`}>
      <h1>검색 결과</h1>
      <div className={`w-100 flex-col py-15 gap-15`}>
        <h2 className={`${styles.borderBottom}`}>아티스트</h2>
        <div className={`flex-row gap-30`}>
          <div className={`${styles.artist}`}>
            <img src={""} alt="" />
          </div>
        </div>
      </div>
      <div className={`w-100 flex-col py-15 gap-15`}>
        <h2 className={`${styles.borderBottom}`}>음악</h2>
        <MusicTable />
      </div>
    </div>
  );
};

export default SearchResultPage;
