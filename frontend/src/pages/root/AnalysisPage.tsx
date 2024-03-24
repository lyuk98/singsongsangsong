import React from "react";
import { FaQuestionCircle } from "react-icons/fa";

import styles from "./AnalysisPage.module.css";
import testimg from "./../../sources/testimg/image 42.png";
import MusicSectionIndicator from "../../components/public/analysis/MusicSectionIndicator";
import MoodAndGenre from "../../components/public/analysis/MoodAndGenre";
import SimilarSong from "../../components/public/analysis/SimilarSong";
import Button from "../../components/buttons/Button";

const AnalysisPage = () => {
  const handleUpload = () => {
    console.log("업로드 되나요");
  };

  return (
    <div className={`w-100 py-main ${styles.container}`}>
      <div className={`${styles.header}`}>
        <h1>분석 결과</h1>
      </div>
      <div className={`flex-row-center border-box py-15`}>
        <h2>{"mp3"}</h2>
      </div>
      <div className={`flex-col gap-15}`}>
        <h2 className={`flex-row gap-15`}>
          MFCC 결과 <FaQuestionCircle />
        </h2>
        <div className={`border-box bg-box flex-col-center ${styles.imgBox}`}>
          <img src={testimg} alt="mfccimg" />
        </div>
      </div>
      <div className={`flex-col gap-15`}>
        <h2>구간 분석 결과</h2>
        <div className={`flex-row gap-15`}>
          <div
            className={`border-box bg-box flex-col-center gap-15 ${styles.imgBox}`}
          >
            <MusicSectionIndicator />
          </div>
        </div>
      </div>
      <MoodAndGenre />
      <div className={"border-box bg-box py-15"}>
        <SimilarSong />
      </div>
      <div className={"border-box bg-box py-15 flex-col-center"}>
        <h2>곡을 게시하고, 트랜드에 참여하세요!</h2>
        <Button onClick={handleUpload}>게시하기</Button>
      </div>
    </div>
  );
};

export default AnalysisPage;
