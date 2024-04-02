import React, { useEffect, useState } from "react";
import styles from "./SongDetails.module.css";
import RaderChart from "../../public/chart/raderChart/RaderChart";
import MusicSectionIndicator from "../../public/analysis/MusicSectionIndicator";
import SimilarSong from "../../public/analysis/SimilarSong";
import { getAnalyzeResult } from "../../../utils/api/songDetailApi";
import { useParams } from "react-router-dom";
import { useAxios } from "../../../hooks/api/useAxios";

const TAB_CONTENT = ["가사", "분위기 / 장르", "유사곡", "구간분석"];

/**
 * 곡의 세부정보를 표현할 탭 컴포넌트
 * @todo 곡 가사 정보 / 비교한 곡에대한 정보들 (래더차트에 데이터값 넣어줘야함) props로 받아야함
 * 결과값은 받아온 데이터중 가장 높은값을 변수로 사용해서 와 결과다 위치에 넣어줘야함
 */

type PropsType = {
  lyrics: string;
};
const SongDetails = ({ lyrics }: PropsType) => {
  const [focused, setFocused] = useState<string>("가사");
  const { songId } = useParams();
  const handleSelectButton = (content: string): void => {
    setFocused(content);
  };

  const { response, isLoading } = useAxios({
    method: "GET",
    url: `/song/analyze/${songId}`,
  });

  console.log(response);

  useEffect(() => {
    const result = getAnalyzeResult(songId);
    console.log(result);
  }, []);

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
          <p>{lyrics ? lyrics : <p>제공되는 가사가 없습니다.</p>}</p>
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
        {focused === "유사곡" && <SimilarSong />}
        {focused === "구간분석" && <MusicSectionIndicator />}
      </div>
    </div>
  );
};

export default SongDetails;
