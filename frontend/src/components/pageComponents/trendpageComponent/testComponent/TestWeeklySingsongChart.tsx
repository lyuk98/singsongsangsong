import React from "react";
import { FaHeart } from "react-icons/fa";
import { LiaHeadsetSolid } from "react-icons/lia";
import { MdOutlineFileDownload } from "react-icons/md";

import styles from "./TestWeeklySingsongChart.module.css";
import TrendSlider from "./TrendSlider";
import Album from "../../../public/Album";
import MoodTag from "../../../moodTag/MoodTag";
import EmotionBox from "../../../public/emotionBox/EmotionBox";
import RaderChart from "../../../public/chart/raderChart/RaderChart";

const TestWeeklySingsongChart = () => {
  return (
    <div className={`w-100 gap-15 flex-col-center ${styles.container}`}>
      <h1>금주의 싱송차트</h1>
      <div className={`w-100 p-15 b-15 shadow-box`}>
        <TrendSlider>
          <div className={`${styles.content}`}>
            <div className={`flex-col gap-15 ${styles.leftBox}`}>
              <div className={`w-100 flex-row gap-30 space-between`}>
                <div style={{ width: "100px", height: "100px" }}>
                  <Album />
                </div>
                <div className={`flex-col`}>
                  <h1>무슨노래</h1>
                  <h3>김작곡</h3>
                </div>
                <div
                  className={`flex-col border-box gap-15 p-15 ${styles.indicator}`}
                >
                  <p className={`flex-row-center gap-15`}>
                    <FaHeart size={24} />
                    Favorites
                    <span>24</span>
                  </p>
                  <p className={`flex-row-center gap-15`}>
                    <MdOutlineFileDownload size={24} />
                    Downloads
                    <span>24</span>
                  </p>
                  <p className={`flex-row-center gap-15`}>
                    <LiaHeadsetSolid size={24} />
                    Plays
                    <span>24</span>
                  </p>
                </div>
              </div>
              <div className={`w-100 p-15 border-box`}>
                이곳에는 곡설명이 들어올겁니다
              </div>
              <div className={`w-100 flex-row space-between`}>
                <MoodTag mood={"활기찬"} />
                <MoodTag mood={"활기찬"} />
                <MoodTag mood={"활기찬"} />
                <MoodTag mood={"활기찬"} />
              </div>
              <div>
                <EmotionBox />
              </div>
            </div>
            <div className={`${styles.rightBox}`}>
              <div style={{ height: "400px" }} className={`w-100`}>
                <RaderChart />
              </div>
            </div>
          </div>
        </TrendSlider>
      </div>
    </div>
  );
};

export default TestWeeklySingsongChart;
