import React from "react";
import styles from "./CommentInput.module.css";

/**
 * 로그인 안했을때 disalbe 상태 추가
 * @returns
 */

const CommentInput = (): JSX.Element => {
  return (
    <div className={`flex-row-center ${styles.container}`}>
      <textarea
        placeholder="댓글로 의견을 남겨주세요"
        name=""
        id=""
        maxLength={100}
        className={styles.textArea}
      ></textarea>
      <button className={`flex-col-center ${styles.submitButton}`}>
        댓글등록
      </button>
    </div>
  );
};

export default CommentInput;
