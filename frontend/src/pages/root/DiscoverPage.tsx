import React, { ChangeEvent, useEffect, useState } from "react";
import styles from "./DiscoverPage.module.css";
import Button from "../../components/buttons/Button";
import { useNavigate } from "react-router-dom";

const SEARCH_OPTION = [
  { type: "장르", option: ["발라드", "락", "힙합"] },
  { type: "테마", option: ["신나는", "슬픈"] },
  { type: "BPM", option: [40, 60, 80, 100, 120, 140, 160, 180, 200] },
  { type: "정렬", option: ["최신순", "오래된 순"] },
];

const DiscoverPage = () => {
  const [searchKeyword, setSearchKeyword] = useState<string>("");

  const navigate = useNavigate();

  const [option, setOption] = useState({
    genre: "",
    thema: "",
    bpm: "",
    sort: "",
  });

  const handleKeywordChange = (event: ChangeEvent<HTMLInputElement>) => {
    setSearchKeyword(event.target.value);
  };

  const handleSearchOption = (
    event: ChangeEvent<HTMLSelectElement>,
    type: string | number
  ) => {
    setOption((prev) => {
      switch (type) {
        case "장르":
          type = "genre";
          break;
        case "테마":
          type = "thema";
          break;
        case "BPM":
          type = "bpm";
          break;
        case "정렬":
          type = "sort";
          break;
      }
      return {
        ...prev,
        [type]: event.target.value,
      };
    });
  };

  useEffect(() => {
    console.log(option);
  }, [option]);

  const handleSubmit = () => {
    navigate(
      `result/?keyword=${searchKeyword}&?genre=${option.genre}&bpm=${option.bpm}&atomposhpere=${option.thema}&sort=${option.sort}`
    );
  };

  return (
    <div className={`px-main my-main w-100 flex-col `}>
      <h1 style={{ borderBottom: "2px solid black", paddingBottom: "10px" }}>
        둘러보기
      </h1>
      <div
        id="search"
        className={`w-100 flex-col py-15 gap-15 ${styles.searchBox}`}
      >
        <input
          type="text"
          placeholder="검색할 아티스트 / 노래 제목을 입력해주세요"
          value={searchKeyword}
          onChange={handleKeywordChange}
          className={`w-100 p-15 border-box ${styles.searchBar}`}
        />
        <div className={`flex-row gap-15 ${styles.optionBox}`}>
          {SEARCH_OPTION.map((element) => (
            <select
              key={element.type}
              className={`${styles.selector}`}
              onChange={(event) => handleSearchOption(event, element.type)}
            >
              <option value="">{element.type}</option>
              {element.option.map((options) => (
                <option key={options} value={options}>
                  {options}
                </option>
              ))}
            </select>
          ))}

          <Button id="search" onClick={handleSubmit}>
            검색
          </Button>
        </div>
      </div>
      <div className={`w-100 py-15 flex-col gap-15`}>
        <h1>인기 플레이 리스트</h1>
        <div className={`flex-row gap-30 ${styles.musicSection}`}>
          <div className={`${styles.musicBox}`}></div>
          <div className={`${styles.musicBox}`}></div>
        </div>
      </div>
      <div className={`w-100 py-15 flex-col gap-15`}>
        <h1>인기 아티스트</h1>
        <div className={`flex-row gap-30 ${styles.musicSection}`}>
          <div className={`${styles.musicBox}`}></div>
          <div className={`${styles.musicBox}`}></div>
        </div>
      </div>
    </div>
  );
};

export default DiscoverPage;
