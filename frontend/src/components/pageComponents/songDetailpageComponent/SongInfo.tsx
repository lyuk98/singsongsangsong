import React from "react";
import { PiVinylRecord } from "react-icons/pi";
import styles from "./SongInfo.module.css";
import MoodTag from "../../moodTag/MoodTag";

const SongInfo = () => {
  return (
    <div className={`flex-col-center ${styles.container}`}>
      <header>
        <h2>
          곡설명
          <PiVinylRecord color="#0085E5" size={"42"} />
        </h2>
      </header>
      <div className={`${styles.explanationBox}`}>
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dignissimos
        quis culpa sunt minima animi inventore quos ducimus placeat sed,
        incidunt delectus. Nulla aliquam odio laudantium, magnam reiciendis
        quas? Quidem, incidunt.
      </div>
      <div className={`flex-row-center ${styles.tagBox}`}>
        <MoodTag mood="발라드" />
        <MoodTag mood="테마" />
        <MoodTag mood="130bpm" />
        <MoodTag mood="C#" />
      </div>
    </div>
  );
};

export default SongInfo;
