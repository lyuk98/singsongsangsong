import React from "react";

import styles from "./SongHeader.module.css";
import coverimg from "./../../../sources/testimg/cover.png";
import Album from "../../public/Album";

type PropsType = {
  songTitle: string;
  artist: any;
};

const SongHeader = () => {
  return (
    <div className={`flex-col-center ${styles.container}`}>
      <div className={styles.background}>
        <img src={coverimg} alt="" />
      </div>
      <div className={`flex-row ${styles.content}`}>
        <div className={`${styles.musicAlbum}`}>
          <Album />
        </div>
        <div className={`flex-col gap-15`}>
          <h1>곡 제목</h1>
          <h2>김작곡</h2>
          <h3>러닝타임</h3>
        </div>
      </div>
    </div>
  );
};

export default SongHeader;
