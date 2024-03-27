import React from "react";
import styles from "./TopRankSongPage.module.css";
import MusicTable from "../../components/public/music/MusicTable";

/**
 * 분위기 / 장르별 상위 10개의 음악들을 보여줄 페이지.
 */
const TopRankSongPage = () => {
  const randNum = Math.floor(Math.random() * 5) + 1;
  const header = require(`./../../sources/imgs/header/headerimg${randNum}.jpg`);

  return (
    <div className={`w-100 flex-col gap-30`}>
      <div className={`w-100 flex-row-center ${styles.header}`}>
        <div className={`w-100 ${styles.headerBackground}`}>
          <img src={header} alt="headerImg" />
        </div>
        <div className={` mt-auto mr-auto ${styles.content}`}>
          <h1>금주의 인기곡 Top 10</h1>
          <h1>asgkas gkljieagneionio</h1>
        </div>
      </div>
      <MusicTable />
    </div>
  );
};

export default TopRankSongPage;
