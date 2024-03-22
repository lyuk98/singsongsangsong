import React, { useState } from "react";
import { FaComment } from "react-icons/fa";

import CommentInput from "./CommentInput";
import Comments from "./Comments";
import styles from "./CommentForm.module.css";

const DUMMY = [
  {
    artist: "name2",
    comment: "gdgdgd",
  },
  {
    artist: "namasdasdasde2",
    comment: "gdgdgddasdasdasdd",
  },
];

const CommentForm = () => {
  return (
    <div className={styles.container}>
      <header>
        <h1>
          댓글&nbsp;
          <FaComment size={24} />
        </h1>
      </header>
      <CommentInput />
      {DUMMY.map((element) => {
        return <Comments artist={element.artist} comment={element.comment} />;
      })}
    </div>
  );
};

export default CommentForm;
