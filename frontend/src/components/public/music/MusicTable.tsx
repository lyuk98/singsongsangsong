import React, { Fragment } from "react";
import { FaStar, FaRegStar } from "react-icons/fa";

import styles from "./MusicTable.module.css";
import Album from "../Album";

const DUMMY = [
  {
    album: "1234",
    title: "제목1",
    genre: "발라드",
    mood: "신나는",
    artist: "김작곡",
    length: "3:23",
  },
];

const MusicTable = () => {
  return (
    <div className={`w-100 flex-col-center`}>
      <thead className={`${styles.head}`}>
        <th>&nbsp;</th>
        <th>제목</th>
        <th>장르</th>
        <th>분위기</th>
        <th>아티스트</th>
        <th>길이</th>
        <th>&nbsp;</th>
      </thead>
      <tbody className={`${styles.musicBody}`}>
        {DUMMY.map((element, index) => {
          return (
            <Fragment key={element.artist}>
              <td>
                <div style={{ width: "50px", height: "50px", margin: "auto" }}>
                  <Album />
                </div>
              </td>
              <td>{element.title}</td>
              <td>{element.genre}</td>
              <td>{element.mood}</td>
              <td>{element.artist}</td>
              <td>{element.length}</td>
              <td style={{ cursor: "pointer" }}>
                <FaRegStar />
              </td>
            </Fragment>
          );
        })}
      </tbody>
    </div>
  );
};

export default MusicTable;
