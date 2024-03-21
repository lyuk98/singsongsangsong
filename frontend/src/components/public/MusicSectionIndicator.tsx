import React from "react";
import img from "./../../sources/testimg/sectionImg.png";
import styles from "./MusicSectionIndicator.module.css";
import SectionButton from "./SectionButton";

const MusicSectionIndicator = () => {
  return (
    <div className={`flex-col-center ${styles.content}`}>
      <h2>각 구간은 이런 특징을 가져요</h2>
      <div className={`${styles.imgSection}`}>
        <img src={img} alt="sectionimg" />
      </div>
      <div className={`${styles.tagSection}`}>
        <div className={`flex-row-center ${styles.innerSection}`}>
          <div className={styles.type}>
            <h3>intro</h3>
          </div>
          <SectionButton section="intro" startPoint={43} endPoint={61} />
        </div>
        <div className={`flex-row-center ${styles.innerSection}`}>
          <div className={styles.type}>
            <h3>verse</h3>
          </div>
          <SectionButton section="verse" startPoint={43} endPoint={67} />
        </div>
        <div className={`flex-row-center ${styles.innerSection}`}>
          <div className={styles.type}>
            <h3>chorus</h3>
          </div>
          <SectionButton section="chorus" startPoint={121} endPoint={143} />
        </div>
        <div className={`flex-row-center ${styles.innerSection}`}>
          <div className={styles.type}>
            <h3>bridge</h3>
          </div>
          <SectionButton section="bridge" startPoint={152} endPoint={190} />
        </div>
      </div>
    </div>
  );
};

export default MusicSectionIndicator;
