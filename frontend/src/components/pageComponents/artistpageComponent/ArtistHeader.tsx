import React from "react";
import { FaRegHeart, FaShareAlt, FaHeart } from "react-icons/fa";
import { BsBookmarkPlusFill, BsFillBookmarkDashFill } from "react-icons/bs";

import img from "./../../../sources/testimg/artistProfile.jpg";
import styles from "./ArtistHeader.module.css";

/**
 * 아티스트 페이지에 사용할 헤더 컴포넌트
 * @todo 팔로우기능 hover 활성화, like기능 hover 활성화, 팔로우 / 좋아요 state는 위에서 받아와야할듯
 * @todo 조건따라서 상단 import 받아온, 이미지 사용해서 팔로우 / 언팔로우, 라이크 / 언라이크 상태 변경
 */
const ArtistHeader = () => {
  return (
    <div className={`flex-row-center ${styles.container}`}>
      <div className={`${styles.background}`}>
        <img src={img} alt="" />
      </div>
      <div className={`mx-auto flex-row-center ${styles.content}`}>
        <img src={img} alt="" className={styles.artistProfile} />
        <div className={`my-auto flex-col-center ${styles.artistInfo}`}>
          <h1>김작곡</h1>
          <div className={styles.artistIntroduce}>
            <p>
              Lorem ipsum dolor, sit amet consectetur adipisicing elit.
              Accusantium, iste odio nemo voluptas pariatur ipsum similique! Nam
            </p>
            <div className={styles.contactButton}>
              <p>
                <BsBookmarkPlusFill /> Follow
              </p>
              <p>
                <FaRegHeart /> Like
              </p>
              <p>
                <FaShareAlt /> Share
              </p>
            </div>
          </div>
        </div>
        <div
          className={`mb-auto ml-auto flex-row-center ${styles.numberIndicator}`}
        >
          <span
            className={`flex-col-center`}
            style={{ borderRight: "1px solid white", paddingRight: "10px" }}
          >
            <span style={{ fontSize: "32px" }}>{"720"}</span>
            <span style={{ fontSize: "14px" }}>FOLLOWER</span>
          </span>
          <span className={`flex-col-center`}>
            <span style={{ fontSize: "32px" }}>{"25"}</span>
            <span style={{ fontSize: "14px" }}>TRACKS</span>
          </span>
        </div>
      </div>
    </div>
  );
};

export default ArtistHeader;
