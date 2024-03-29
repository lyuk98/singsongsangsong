import React from "react";
import styles from "./SongWithEmotion.module.css";
import emotion1 from "./../../../sources/imgs/emotions/emotion1.gif";
import emotion2 from "./../../../sources/imgs/emotions/emotion2.gif";
import emotion3 from "./../../../sources/imgs/emotions/emotion3.gif";
import emotion4 from "./../../../sources/imgs/emotions/emotion4.gif";
import emotion5 from "./../../../sources/imgs/emotions/emotion5.gif";
import emotion6 from "./../../../sources/imgs/emotions/emotion6.gif";
import Album from "../../public/Album";
import { useNavigate } from "react-router-dom";

type Song = {
  songId: number;
  title: string;
  artistId: number;
  artistName: string;
  lyrics: string;
  play: number;
  download: number;
  like: number;
  emotions: any;
  atmospheres: any;
};

type EmotionMap = Record<string, Song>;

const EMOTIONS = [
  {
    emotion: emotion1,
    count: "41",
  },
  {
    emotion: emotion2,
    count: "141",
  },
  {
    emotion: emotion3,
    count: "462",
  },
  {
    emotion: emotion4,
    count: "22",
  },
  {
    emotion: emotion5,
    count: "253",
  },
  {
    emotion: emotion6,
    count: "223",
  },
];

const SongWithEmotion = ({ emotions }: EmotionMap) => {
  const navigate = useNavigate();

  return (
    <div className={`flex-col-center ${styles.container}`}>
      <h1>감정 극대화 곡</h1>
      <div
        className={`flex-row-center p-15 w-100 bg-box b-15 shadow-box ${styles.content}`}
      >
        {Object.entries(emotions).map((element: any) => {
          console.log(element);
          return (
            <div className={`flex-col-center ${styles.songBox}`}>
              <div className={`flex-row-center ${styles.emotions}`}>
                {/* <img src={element.emotion} alt="" /> */}
                <p>{element[1].count}</p>
              </div>
              <div style={{ width: "100px", height: "100px" }}>
                <Album />
              </div>
              <div className={`flex-col-center ${styles.info}`}>
                <h2
                  style={{ cursor: "pointer" }}
                  onClick={() => navigate(`/song/${element[1].songId}`)}
                >
                  {element[1].title}
                </h2>
                <h3
                  style={{ cursor: "pointer" }}
                  onClick={() => navigate(`/song/${element[1].artistId}`)}
                >
                  {element[1].artistName}
                </h3>
              </div>
            </div>
          );
        })}
        {/* {EMOTIONS.map((element, indenx) => {
          return (
            <div className={`flex-col-center ${styles.songBox}`}>
              <div className={`flex-row-center ${styles.emotions}`}>
                <img src={element.emotion} alt="" />
                <p>{element.count}</p>
              </div>
              <div style={{ width: "100px", height: "100px" }}>
                <Album />
              </div>
              <div className={`flex-col-center ${styles.info}`}>
                <h2>무슨노래</h2>
                <h3>김작곡</h3>
                <p>#태그 #태그</p>
              </div>
            </div>
          );
        })} */}
      </div>
    </div>
  );
};

export default SongWithEmotion;
