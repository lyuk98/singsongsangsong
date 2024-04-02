import React from "react";

import styles from "./SongHeader.module.css";
import coverimg from "./../../../sources/testimg/cover.png";
import Album from "../../public/Album";

type PropsType = {
  songtitle: string;
  artist: any;
  likeCount: any;
  playCount: any;
  downloadCount: any;
};

const SongHeader = ({
  songtitle,
  artist,
  likeCount,
  playCount,
  downloadCount,
}: PropsType) => {
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
          <h1>{songtitle}</h1>
          <h2>{artist.username}</h2>
        </div>
      </div>
    </div>
  );
};

export default SongHeader;
