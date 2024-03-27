import React from "react";
import img from "./../../../sources/testimg/sectionImg.png";
import styles from "./MusicSectionIndicator.module.css";
import SectionButton from "./SectionButton";

/**
 * 구간분석용 지표가 나오는 컴포넌트.
 * 곡상세 페이지 및, 분석결과 페이지에서 사용할 예정
 *
 * memo : 잘려진 구간들을 반복문을 사용해서(아직 적용 안함) 반복 출력해야함
 * 데이터 넣어줘야하는건 구간에대한 타입과 start,end point
 */

const MusicSectionIndicator = () => {
  // songSpectrum

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
