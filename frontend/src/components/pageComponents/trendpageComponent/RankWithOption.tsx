import React, { ChangeEvent, useEffect, useState } from "react";
import Marquee from "react-fast-marquee";
import styles from "./RankWithOption.module.css";
import RankedSongAndArtist from "./RankedSongAndArtist";

/** 장르별 랭킹 / 분위기별 랭킹 컴포넌트
 * @todo 장르 / 분위기 검색할 태그 확정되면 배열만들어서 관리해야함
 * @todo marquu 안의 태그들도 관리해야해서 기억해둬야함
 */
const RankWithOption = () => {
  const [headerOption, setHeaderOption] = useState<string>("genre");
  const [contentOption, setContentOption] = useState<string>("발라드");

  const handleContentOption = (event: ChangeEvent<HTMLSelectElement>): void => {
    setContentOption(event.target.value);
  };

  // 기본적으로 최상단의 태그옵션으로 변경해주고 다시 데이터를 요청해야함
  const handleHeaderOption = (option: string): void => {
    setHeaderOption(option);
    if (option === "genre") {
      setContentOption("발라드");
    } else if (option === "mood") {
      setContentOption("신나는");
    }
  };

  useEffect(() => {
    console.log(contentOption);
  }, [contentOption]);

  return (
    <div className={`flex-col-center ${styles.container}`}>
      <div className={styles.header}>
        <button
          className={styles.headerButton}
          disabled={headerOption === "genre"}
          onClick={() => handleHeaderOption("genre")}
        >
          <h1>장르별 랭킹</h1>
        </button>
        <h1>&nbsp;/&nbsp;</h1>
        <button
          className={styles.headerButton}
          disabled={headerOption === "mood"}
          onClick={() => handleHeaderOption("mood")}
        >
          <h1>분위기별 랭킹</h1>
        </button>
      </div>
      <div className={styles.content}>
        <Marquee pauseOnHover={true} autoFill={true} className={styles.marquee}>
          <p>슬픔 분위기의 곡이 많이 나왔어요.</p>
          <p>180-190BPM 의 곡이 많이 발매되었어요.</p>
          <p>24개의 곡이 발매되었어요.</p>
          <p>7,845번의 재생이 발생했어요.</p>
          <p>C#으로 작성된 곡이 많아요.</p>
        </Marquee>
        {/* 장르 확정되면 태그들 넣어서 바꿔둬야함 */}
        <div className={`flex-row-center ${styles.optionSelectBox}`}>
          {headerOption === "genre" && (
            <select
              className={styles.optionSelector}
              name="contentOption"
              value={contentOption}
              onChange={handleContentOption}
            >
              <option key="발라드" value="발라드">
                발라드
              </option>
            </select>
          )}
          {headerOption === "mood" && (
            <select
              className={styles.optionSelector}
              name="contentOption"
              value={contentOption}
              onChange={handleContentOption}
            >
              <option key="신나는" value="신나는">
                신나는
              </option>
              <option key="슬픈" value="슬픈">
                슬픈
              </option>
            </select>
          )}
        </div>
        <div className={`flex-row-center ${styles.rankSection}`}>
          <div className={`flex-col-center ${styles.songSection}`}>
            <RankedSongAndArtist type={"song"} />
            <RankedSongAndArtist type={"song"} />
            <RankedSongAndArtist type={"song"} />
          </div>
          <div className={`flex-col-center ${styles.authorSection}`}>
            <RankedSongAndArtist type={"author"} />
            <RankedSongAndArtist type={"author"} />
            <RankedSongAndArtist type={"author"} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default RankWithOption;
