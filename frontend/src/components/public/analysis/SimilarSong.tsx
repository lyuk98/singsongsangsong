import React, { useEffect, useState } from "react";
import styles from "./SimilarSong.module.css";
import RankedSongAndArtist from "../../pageComponents/trendpageComponent/RankedSongAndArtist";
import Album from "../Album";
import { getSongSimilarity } from "../../../utils/api/songDetailApi";
import { useParams } from "react-router-dom";

const DUMMY = [
  { artist: "test1", song: "testmusic1" },
  { artist: "test2", song: "testmusic2" },
  { artist: "test3", song: "testmusic3" },
  { artist: "test4", song: "testmusic4" },
];

const SimilarSong = () => {
  const { songId } = useParams();
  const [selectIndex, setSelectIndex] = useState<number>(0);

  const changeIndex = (index: number) => {
    setSelectIndex(index);
  };

  useEffect(() => {
    // const response = getSongSimilarity(songId);
  }, []);

  return (
    <div className={`flex-col-center ${styles.container}`}>
      <h3>비슷한 노래에는 이런 노래가 있어요</h3>
      <div className={`flex-row-center ${styles.content}`}>
        <div className={`flex-col-center ${styles.similarSong}`}>
          {DUMMY.map((element, index) => {
            return (
              <div
                key={index}
                onClick={() => changeIndex(index)}
                className={`${styles.box} ${
                  selectIndex === index ? styles.selceted : ""
                }`}
              >
                <RankedSongAndArtist type={"song"} showIndicator={false} />
              </div>
            );
          })}
        </div>
        <div className={`flex-col-center ${styles.compareSongSection}`}>
          <p>가장 일치한 부분의 </p>
          <h3>유사도는 {"78%"} 입니다</h3>
          <div className={`flex-row-center ${styles.compareSong}`}>
            <div className={`flex-col-center ${styles.selectedSong}`}>
              <div style={{ width: "100px", height: "100px" }}>
                <Album />
              </div>
              <p>{DUMMY[selectIndex].song}</p>
              <p>{DUMMY[selectIndex].artist}</p>
            </div>
            <div className={`flex-col-center ${styles.mySong}`}>
              <div style={{ width: "100px", height: "100px" }}>
                <Album />
              </div>
              <p>{`노래 이름`}</p>
              <p>{"노래 작곡가"}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SimilarSong;
