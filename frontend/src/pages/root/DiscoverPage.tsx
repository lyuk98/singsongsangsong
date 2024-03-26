import React, { ChangeEvent, useState } from "react";
import styles from "./DiscoverPage.module.css";
import Button from "../../components/buttons/Button";

const SEARCH_OPTION = [
  { type: "장르", option: ["발라드", "락", "힙합"] },
  { type: "테마", option: ["신나는", "슬픈"] },
  { type: "BPM", option: [40, 60, 80, 100, 120, 140, 160, 180, 200] },
  { type: "정렬", option: ["최신순", "오래된 순"] },
];

const DiscoverPage = () => {
  const [searchKeyword, setSearchKeyword] = useState<string>("");

  const handleKeywordChange = (event: ChangeEvent<HTMLInputElement>) => {
    setSearchKeyword(event.target.value);
  };

  const handleSubmit = () => {};

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
          {SEARCH_OPTION.map((options) => (
            <select
              key={options.type}
              name={options.type}
              id={options.type}
              className={`${styles.selector}`}
            >
              <option value="">{options.type}</option>
              {options.option.map((value) => (
                <option key={value} value={value}>
                  {value}
                </option>
              ))}
            </select>
          ))}
          <Button id="search" onClick={handleSubmit}>
            검색
          </Button>
        </div>
      </div>
      <div className={`w-100 py-15 flex-col gap-15 `}>
        <h1>인기 플레이 리스트</h1>
        <div className={`flex-row gap-30 ${styles.musicSection}`}>
          <div className={`${styles.musicBox}`}></div>
          <div className={`${styles.musicBox}`}></div>
        </div>
      </div>
      <div className={`w-100 py-15 flex-col gap-15 `}>
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
