import React from "react";

import styles from "./CompareWithAnotherSite.module.css";
import ChartWithMelonBillboard from "./ChartWithMelonBillboard";

/**
 * 멜론차트와 빌보드차트를 보여줄 컴포넌트입니다
 */
const CompareWithAnotherSite = () => {
  return (
    <div className={`w-100 ${styles.container}`}>
      <div
        className={`flex-row-center w-100 bg-box b-15 shadow-box ${styles.content}`}
      >
        <div className={`flex-row-center ${styles.billboard}`}>
          <ChartWithMelonBillboard type="billboard" />
        </div>
        <div className={`flex-row-center ${styles.melon}`}>
          <ChartWithMelonBillboard type="melon" />
        </div>
      </div>
    </div>
  );
};

export default CompareWithAnotherSite;
