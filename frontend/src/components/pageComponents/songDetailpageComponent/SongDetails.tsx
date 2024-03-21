import React, { useState } from "react";
import styles from "./SongDetails.module.css";
import RaderChart from "../../public/chart/raderChart/RaderChart";
import MusicSectionIndicator from "../../public/MusicSectionIndicator";
const TAB_CONTENT = ["가사", "분위기 / 장르", "유사곡", "구간분석"];

const SongDetails = () => {
  const [focused, setFocused] = useState<string>("가사");

  const handleSelectButton = (content: string): void => {
    setFocused(content);
  };

  return (
    <div className={styles.container}>
      <ul className={`flex-row-center ${styles.tabButton}`}>
        {TAB_CONTENT.map((element) => {
          return (
            <>
              <li
                className={focused === element ? styles.focused : ""}
                key={element}
                onClick={() => handleSelectButton(element)}
              >
                {element}
              </li>
            </>
          );
        })}
      </ul>
      <div className={styles.selectedDetail}>
        {focused === "가사" && (
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex rerum
            saepe in dignissimos consequatur officiis, officia labore qui,
            ratione magnam placeat, molestias facere illum cupiditate
            consectetur illo quaerat maxime minus?
          </p>
        )}
        {focused === "분위기 / 장르" && (
          <div>
            <h2>분위기 / 장르 분석 결과</h2>
            <div className={`flex-row-center ${styles.radderBox}`}>
              <div className={styles.moodBox}>
                <h2>분위기 분석</h2>
                <div className={styles.chartBox}>
                  <RaderChart />
                </div>
                <div className={`flex-row-center ${styles.topFiveResult}`}>
                  <div className={`flex-col-center ${styles.result}`}>
                    <p>기쁨</p>
                    <h3>78%</h3>
                  </div>
                </div>
                <div className={`flex-col-center ${styles.summary}`}>
                  와결과다
                </div>
              </div>
              <div className={styles.genreBox}>
                <h2>장르 분석</h2>
                <div className={styles.chartBox}>
                  <RaderChart />
                </div>
                <div className={`flex-row-center ${styles.topFiveResult}`}>
                  <div className={`flex-col-center ${styles.result}`}>
                    <p>발라드</p>
                    <h3>78%</h3>
                  </div>
                </div>
                <div className={`flex-col-center ${styles.summary}`}>
                  와결과다
                </div>
              </div>
            </div>
          </div>
        )}
        {
          // 응애
        }
        {focused === "구간분석" && <MusicSectionIndicator />}
      </div>
    </div>
  );
};

export default SongDetails;
