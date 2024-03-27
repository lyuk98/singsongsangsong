import React from "react";
import { useParams } from "react-router";
import { json } from "react-router-dom";
import { RiMusic2Fill } from "react-icons/ri";
import { TbMoodNerd } from "react-icons/tb";

import ArtistHeader from "../../components/pageComponents/artistpageComponent/ArtistHeader";
import styles from "./ArtistPage.module.css";
import EmotionBox from "../../components/public/emotionBox/EmotionBox";
import BarChart from "../../components/public/chart/barChart/BarChart";
import MusicTable from "../../components/public/music/MusicTable";
import { useAxios } from "../../hooks/api/useAxios";

const ArtistPage = () => {
  const { artistId } = useParams();

  const { response, isLoading, handleLoad, error } = useAxios({
    url: `/artist/${artistId}`,
    method: "GET",
  });

  return (
    <div className={styles.container}>
      <div className={styles.headerSection}>
        <ArtistHeader
        // artistId={response.artistId}
        // nickName={response.nickname}
        // profileImg={response.profileImageUrl}
        // introduction={response.introduction}
        />
      </div>
      <div className={styles.content}>
        <div className={`flex-row-center w-100`}>
          <div className={"w-50"}>
            <h2 className={`flex-row`}>
              주요 장르&nbsp;
              <RiMusic2Fill />
            </h2>
            <div style={{ height: "300px" }} className={`w-100`}>
              <BarChart option="장르" />
            </div>
          </div>
          <div className={"w-50"}>
            <h2 className={`flex-row`}>
              주요 분위기&nbsp;
              <RiMusic2Fill />
            </h2>
            <div style={{ height: "300px" }} className={`w-100`}>
              <BarChart option="분위기" />
            </div>
          </div>
        </div>
        <div className={`flex-col-center bg-box py-15 ${styles.emotionBox}`}>
          <h1>사람들은 {artistId} 님에게서</h1>
          <h1>이런 느낌을 받았어요</h1>
          <EmotionBox />
        </div>
        <div className={`w-100 py-15`}>
          <MusicTable />
        </div>
      </div>
    </div>
  );
};

export default ArtistPage;
