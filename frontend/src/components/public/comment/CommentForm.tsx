import React, { useState } from "react";
import { FaComment } from "react-icons/fa";

import CommentInput from "./CommentInput";
import Comments from "./Comments";
import styles from "./CommentForm.module.css";
import { useAxios } from "../../../hooks/api/useAxios";

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

/**
 * song id를 props로 받아서 해당 음악에 대한 댓글 목록을 가져와야함
 */
const CommentForm = () => {
  const [trigger, setTrigger] = useState<number>(1);

  const {
    response,
    isLoading,
    handleLoad: reloadComment,
  } = useAxios({
    method: "GET",
    url: "/song/comment/{id}",
  });

  return (
    <div className={styles.container}>
      <header>
        <h1>
          댓글&nbsp;
          <FaComment size={24} />
        </h1>
      </header>
      <CommentInput reloadComment={reloadComment} />
      {DUMMY.map((element) => {
        return <Comments artist={element.artist} comment={element.comment} />;
      })}
    </div>
  );
};

export default CommentForm;
