import React from "react";
import { FaPlay } from "react-icons/fa";

import styles from "./Album.module.css";
import { url } from "inspector";

/** 앨범 이미지를 받아와서 해당 앨범을 hover하면 재생 버튼이 보이고
 * 클릭시 음악을 재생시켜줄 컴포넌트
 */
const Album = () => {
  return (
    <div
      style={{
        backgroundImage: `url(
          "https://images.saramingig.co.kr/product/3/q/W/3qWug03QOQaNqMY.jpeg/o2j/resize/900"
        )`,
      }}
      className={`flex-col-center ${styles.container}`}
    >
      <div className={styles.overlay}>
        <button>
          <FaPlay size={"24px"} />
        </button>
      </div>
    </div>
  );
};

export default Album;
