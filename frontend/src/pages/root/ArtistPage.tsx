import React from "react";
import { useParams } from "react-router";

import ArtistHeader from "../../components/pageComponents/artistpageComponent/ArtistHeader";
import styles from "./ArtistPage.module.css";

const ArtistPage = () => {
  const { artistId } = useParams();

  return (
    <div className={styles.container}>
      <div className={styles.headerSection}>
        <ArtistHeader />
      </div>
      <div className={styles.content}>
        <p>내부 패딩 6rem 이다 흔오야</p>
      </div>
    </div>
  );
};

export default ArtistPage;
