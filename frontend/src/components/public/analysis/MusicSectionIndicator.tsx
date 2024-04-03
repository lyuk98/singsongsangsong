import React, { useEffect, useState } from "react";
import img from "./../../../sources/testimg/sectionImg.png";
import styles from "./MusicSectionIndicator.module.css";
import SectionButton from "./SectionButton";
import { useParams } from "react-router-dom";
import { useAxios } from "../../../hooks/api/useAxios";

/**
 * 구간분석용 지표가 나오는 컴포넌트.
 * 곡상세 페이지 및, 분석결과 페이지에서 사용할 예정
 *
 * memo : 잘려진 구간들을 반복문을 사용해서(아직 적용 안함) 반복 출력해야함
 * 데이터 넣어줘야하는건 구간에대한 타입과 start,end point
 */

const MusicSectionIndicator = () => {
  const { songId } = useParams();
  // const [intro, setIntro] = useState();
  // const [transition, setTransition] = useState();
  // const [chorus, setChorus] = useState();
  // const [verse, setVerse] = useState();
  // const [outro, setOutro] = useState();

  const [section, setSection] = useState({
    intro: [],
    transition: [],
    chorus: [],
    verse: [],
    outro: [],
  });

  const { response, isLoading } = useAxios({
    method: "GET",
    url: `/song/section/${songId}`,
  });

  // console.log(response);

  if (isLoading) {
    return <p>데이터를 로드중입니다</p>;
  }
  if (!response) {
    return <p>분석 데이터가 존재하지 않습니다</p>;
  }
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
          {response
            .filter((element: any) => element.label === "intro")
            .map((element: any) => {
              return (
                <SectionButton
                  section="intro"
                  startPoint={element.start}
                  endPoint={element.end}
                />
              );
            })}
        </div>
        <div className={`flex-row-center ${styles.innerSection}`}>
          <div className={styles.type}>
            <h3>verse</h3>
          </div>
          {response
            .filter((element: any) => element.label === "verse")
            .map((element: any) => {
              return (
                <SectionButton
                  section="verse"
                  startPoint={element.start}
                  endPoint={element.end}
                />
              );
            })}
        </div>
        <div className={`flex-row-center ${styles.innerSection}`}>
          <div className={styles.type}>
            <h3>chorus</h3>
          </div>
          {response
            .filter((element: any) => element.label === "chorus")
            .map((element: any) => {
              return (
                <SectionButton
                  section="chorus"
                  startPoint={element.start}
                  endPoint={element.end}
                />
              );
            })}
        </div>
        <div className={`flex-row-center ${styles.innerSection}`}>
          <div className={styles.type}>
            <h3>bridge</h3>
          </div>
          {response
            .filter((element: any) => element.label === "bridge")
            .map((element: any) => {
              return (
                <SectionButton
                  section="bridge"
                  startPoint={element.start}
                  endPoint={element.end}
                />
              );
            })}
        </div>
        <div className={`flex-row-center ${styles.innerSection}`}>
          <div className={styles.type}>
            <h3>outro</h3>
          </div>
          {response
            .filter((element: any) => element.label === "outro")
            .map((element: any) => {
              return (
                <SectionButton
                  section="outro"
                  startPoint={element.start}
                  endPoint={element.end}
                />
              );
            })}
        </div>
      </div>
    </div>
  );
};

export default MusicSectionIndicator;
