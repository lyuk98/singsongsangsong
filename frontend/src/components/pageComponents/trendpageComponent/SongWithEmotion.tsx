import React from "react";
import styles from "./SongWithEmotion.module.css";
import emotion1 from "./../../../sources/imgs/emotions/emotion1.gif";
import emotion2 from "./../../../sources/imgs/emotions/emotion2.gif";
import emotion3 from "./../../../sources/imgs/emotions/emotion3.gif";
import emotion4 from "./../../../sources/imgs/emotions/emotion4.gif";
import emotion5 from "./../../../sources/imgs/emotions/emotion5.gif";
import emotion6 from "./../../../sources/imgs/emotions/emotion6.gif";
import Album from "../../public/Album";

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

const SongWithEmotion = () => {
  return (
    <div className={styles.container}>
      <div className={`flex-row-center ${styles.content}`}>
        {EMOTIONS.map((element, indenx) => {
          return (
            <div className={`flex-col-center ${styles.songBox}`}>
              <div className={`flex-row-center ${styles.emotions}`}>
                <img src={element.emotion} alt="" />
                <p>{element.count}</p>
              </div>
              <Album />
              <div className={`flex-col-center ${styles.info}`}>
                <h2>무슨노래</h2>
                <h3>김작곡</h3>
                <p>#태그 #태그</p>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
};

export default SongWithEmotion;
