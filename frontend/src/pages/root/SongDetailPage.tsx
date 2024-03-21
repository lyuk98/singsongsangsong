import React from "react";
import { useParams } from "react-router";

import testArtist from "./../../sources/testimg/artistProfile.jpg";
import styles from "./SongDetailPage.module.css";
import Album from "../../components/public/Album";
import SongInfo from "../../components/pageComponents/songDetailpageComponent/SongInfo";

const SongDetailPage = () => {
  const { songId } = useParams();
  return (
    <>
      <div className={styles.container}>
        <div
          style={{
            backgroundImage: `url('https://png.pngtree.com/thumb_back/fh260/background/20230613/pngtree-man-wearing-a-black-jacket-image_2924738.jpg')`,
          }}
          className={styles.artistCover}
        >
          <div className={`flex-col-center ${styles.songImg}`}>
            <div style={{ width: "100px", height: "100px" }}>
              <Album />
            </div>
            <h1>무슨노래</h1>
            <h3>김작곡</h3>
            <p>러닝타임</p>
          </div>
        </div>
        <div className={styles.content}>
          <SongInfo />
        </div>
      </div>
    </>
  );
};

export default SongDetailPage;
