import React from "react";
import { useSearchParams } from "react-router-dom";

import styles from "./SearchResultPage.module.css";
import MusicTable from "../../components/public/music/MusicTable";

const SearchResultPage = () => {
  const [searchParams, setSearchParams] = useSearchParams();

  const title = searchParams.get("title");
  const gender = searchParams.get("gender");
  const atmosphere = searchParams.get("atmosphere");
  const sort = searchParams.get("sort");

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
        <div className={`flex-row gap-30`}>
          <MusicTable />
        </div>
      </div>
    </div>
  );
};

export default SearchResultPage;
