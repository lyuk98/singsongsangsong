import React from "react";
import { FaHeart } from "react-icons/fa";
import { LiaHeadsetSolid } from "react-icons/lia";
import { MdOutlineFileDownload } from "react-icons/md";

import styles from "./RankedSongAndArtist.module.css";
import Album from "../../public/Album";
import Profile from "../../public/Profile";

type PropsType = {
  type: string;
};

/**
 * 장르 / 분위기별 랭킹에 사용될 컴포넌트.
 * 타입으로 song이랑 author를 받아와서 각각 다르게 표현해줘야함
 * @todo 곡 / 작곡가 클릭 시 상세 페이지로 이동하는 로직 작성
 * @todo 데이터 들어오면 info쪽 수정
 */
const RankedSongAndArtist = ({ type }: PropsType) => {
  return (
    <div className={`flex-row-center ${styles.container}`}>
      <div className={`flex-col-center ${styles.album}`}>
        {type === "song" && <Album />}
        {type === "author" && <Profile />}
      </div>
      <div className={`flex-col-center ${styles.info}`}>
        {type === "song" && (
          <>
            <h1>무슨노래</h1>
            <h3>김작곡</h3>
            <p>태그1 태그2 태그3</p>
          </>
        )}
        {type === "author" && (
          <>
            <h1>김작곡</h1>
            <p>태그1 태그2 태그3</p>
          </>
        )}
      </div>
      <div className={`flex-col-center ${styles.indicator}`}>
        <p>
          <MdOutlineFileDownload size={"28px"} />
          24
        </p>
        <p>
          <LiaHeadsetSolid size={"28px"} />
          24
        </p>
        <p>
          <FaHeart size={"24px"} />
          24
        </p>
      </div>
    </div>
  );
};

export default RankedSongAndArtist;
