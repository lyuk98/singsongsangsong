import React, { useEffect } from "react";
import styles from "./FavoriteSongPage.module.css";
import { useAxios } from "../../hooks/api/useAxios";

const FavoriteSongPage = () => {
  const header = require(`./../../sources/imgs/header/headerimg1.jpg`);

  const { response, isLoading } = useAxios({
    method: "GET",
  });

  return (
    <div className={`w-100`}>
      <div className={`w-100 flex-row-center ${styles.header}`}>
        <div className={`w-100 ${styles.headerBackground}`}>
          <img src={header} alt="headerImg" />
        </div>
        <div className={`mt-auto mr-auto flex-col gap-15 ${styles.comment}`}>
          <h1>내가 좋아요한</h1>
          <h1>아티스트</h1>
        </div>
      </div>
      <div className={`flex-col p-15 w-100`}>
        <div></div>
      </div>
    </div>
  );
};

export default FavoriteSongPage;
