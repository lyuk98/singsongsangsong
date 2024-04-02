import React from "react";

/**
 * imgUrl을 props로 받음
 * 유저의 이미지를 표시할 컴포넌트 추후 상세 페이지로 이동 기능 추가 필요
 */

//"https://t3.ftcdn.net/jpg/03/53/11/00/360_F_353110097_nbpmfn9iHlxef4EDIhXB1tdTD0lcWhG9.jpg"
type PropsType = {
  imgUrl?: string;
};

const Profile = ({ imgUrl }: PropsType) => {
  return (
    <div
      style={{
        width: "100%",
        height: "100%",
        borderRadius: "50px",
        backgroundSize: "cover",
      }}
    >
      <img
        style={{
          width: "100%",
          height: "100%",
          borderRadius: "50px",
        }}
        src="https://t3.ftcdn.net/jpg/03/53/11/00/360_F_353110097_nbpmfn9iHlxef4EDIhXB1tdTD0lcWhG9.jpg"
        alt=""
      />
    </div>
  );
};

export default Profile;
