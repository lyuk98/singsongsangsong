import React from "react";
import styles from "./Comments.module.css";
import Profile from "../Profile";
import { commentType } from "../../../utils/types";

type CommentType = {
  artist: string;
  comment: string;
};

const Comments = ({ artist, comment }: CommentType) => {
  return (
    <div className={`flex-row-center ${styles.container}`}>
      <div className={`flex-col-center ${styles.user}`}>
        <div className={styles.profileImg}>
          <Profile />
        </div>
        <div className={`${styles.userName}`}>
          <p>{artist}</p>
        </div>
      </div>
      <div className={`${styles.textArea}`}>
        <p>{comment}</p>
      </div>
    </div>
  );
};

export default Comments;
