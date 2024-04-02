import React, { useState } from "react";
import { FaQuestionCircle } from "react-icons/fa";

import styles from "./AnalysisPage.module.css";
import testimg from "./../../sources/testimg/image 42.png";
import MusicSectionIndicator from "../../components/public/analysis/MusicSectionIndicator";
import MoodAndGenre from "../../components/public/analysis/MoodAndGenre";
import SimilarSong from "../../components/public/analysis/SimilarSong";
import Button from "../../components/buttons/Button";
import { useAxios } from "../../hooks/api/useAxios";
import axios from "axios";

import { AnalyzedResultType } from "../../utils/types";
import { useNavigate } from "react-router";

const AnalysisPage = () => {
  const navigate = useNavigate();

  const { response, isLoading, error } = useAxios({
    url: `/anayze/${`songId`}`,
    method: "GET",
  });

  const [result, setResult] = useState<AnalyzedResultType | null>(null);

  const handleUpload = async () => {
    try {
      const response = await axios({
        method: "PUT",
        url: `${process.env.REACT_APP_API_URL}analyze/publish/${"songid"}`,
      });
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  const navigatePostPage = () => {
    navigate("post");
  };

  return (
    <div className={`w-100 px-main ${styles.container}`}>
      <div className={`${styles.header}`}>
        <h1>분석 결과</h1>
      </div>
      <div className={`flex-row-center border-box py-15`}>
        <h2>{`songTitle`}</h2>
      </div>
      <div className={`flex-col gap-15}`}>
        <h2 className={`flex-row gap-15`}>
          MFCC 결과 <FaQuestionCircle />
        </h2>
        {/* songMfcc */}
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
      {/* <MoodAndGenre /> */}
      <div className={"border-box bg-box py-15"}>
        <SimilarSong />
      </div>
      <div className={"border-box bg-box py-15 gap-15 flex-col-center"}>
        <h2>곡을 게시하고, 트랜드에 참여하세요!</h2>
        <Button onClick={navigatePostPage}>게시하기</Button>
      </div>
    </div>
  );
};

export default AnalysisPage;
