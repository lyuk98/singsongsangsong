import React from "react";
import { useParams } from "react-router-dom";

import styles from "./FavoriteArtistPage.module.css";

const FavoriteArtistPage = () => {
  const header = require(`./../../sources/imgs/header/headerimg1.jpg`);

  return (
    <div className={`w-100`}>
      <div className={`w-100 flex-row-center ${styles.header}`}>
        <div className={`w-100 ${styles.headerBackground}`}>
          <img src={header} alt="headerImg" />
        </div>
        <div className={`mt-auto mr-auto flex-col gap-15 ${styles.comment}`}>
          <h1>내가 좋아하는</h1>
          <h1>아티스트</h1>
        </div>
      </div>
      <div className={`flex-col p-15 w-100`}>
        <div className={"flex-col-center gap-15"}>
          <div
            style={{
              borderRadius: "50%",
              width: "200px",
              height: "200px",
              backgroundColor: "black",
            }}
          >
            <img src="" alt="" />
          </div>
          <h2>artistName</h2>
        </div>
      </div>
    </div>
  );
};

export default FavoriteArtistPage;
