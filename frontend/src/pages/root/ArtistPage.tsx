import React from "react";
import { useParams } from "react-router";

import styles from "./ArtistPage.module.css";

const ArtistPage = () => {
  const { artistId } = useParams();

  return (
    <>
      <div>아티스트 {artistId}상세 페이지입니다</div>
    </>
  );
};

export default ArtistPage;
