import React from "react";
import { useParams } from "react-router";

import testArtist from "./../../sources/testimg/artistProfile.jpg";
import Album from "../../components/public/Album";
import SongDetails from "../../components/pageComponents/songDetailpageComponent/SongDetails";
import SongInfo from "../../components/public/analysis/SongInfo";
import EmotionBox from "../../components/public/emotionBox/EmotionBox";
import styles from "./SongDetailPage.module.css";
import CommentForm from "../../components/public/comment/CommentForm";
import SongHeader from "../../components/pageComponents/songDetailpageComponent/SongHeader";

const SongDetailPage = () => {
  const { songId } = useParams();
  return (
    <div className={`w-100 flex-col-center gap-30 ${styles.container}`}>
      <SongHeader />
      <div className={`flex-col-center gap-30 ${styles.content}`}>
        <SongInfo />
        <SongDetails />
        <div className={`flex-col-center  ${styles.emotionBox}`}>
          <p>사람들은 이 곡에서</p>
          <p>이러한 느낌을 받았어요</p>
          <EmotionBox />
        </div>
        <CommentForm />
      </div>
    </div>
  );
};

export default SongDetailPage;
