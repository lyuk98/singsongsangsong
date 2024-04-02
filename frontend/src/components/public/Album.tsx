import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { musicAction } from "../../store/musicSlice";
import styles from "./Album.module.css";
import { FaPlay } from "react-icons/fa";
import { RootState } from "../../store";

/** 앨범 이미지를 받아와서 해당 앨범을 hover하면 재생 버튼이 보이고
 * 클릭시 음악을 재생시켜줄 컴포넌트
 */
const Album = () => {
  const music = useSelector((state: RootState) => state.music);
  const dispatch = useDispatch();
  // const currentMusic = ("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3");
  const currentMusic = require("./../../sources/mp3/badday.m4a");
  return (
    <div
      style={{
        backgroundImage: `url(
          "https://images.saramingig.co.kr/product/3/q/W/3qWug03QOQaNqMY.jpeg/o2j/resize/900"
        )`,
      }}
      className={`flex-col-center ${styles.container}`}
    >
      <div className={styles.overlay}>
        <button
          onClick={() => dispatch(musicAction.addMusicList(currentMusic))}
        >
          <FaPlay size={"24px"} />
        </button>
        {}
      </div>
    </div>
  );
};

export default Album;
