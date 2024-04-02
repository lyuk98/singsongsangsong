import React, { useEffect, useState } from "react";

import styles from "./SongHeader.module.css";
import coverimg from "./../../../sources/testimg/cover.png";
import Album from "../../public/Album";
import { useAxios } from "../../../hooks/api/useAxios";
import axios from "axios";

type PropsType = {
  songtitle: string;
  artist: any;
  likeCount: any;
  playCount: any;
  downloadCount: any;
  songFileName: any;
  albumImageFileName: any;
};

const SongHeader = ({
  songtitle,
  artist,
  likeCount,
  playCount,
  downloadCount,
  songFileName,
  albumImageFileName,
}: PropsType) => {
  const [imgFile, setImgFile] = useState<any>();

  // const { response: imgFile, isLoading: imgLoading } = useAxios({
  //   method: "GET",
  //   url: `/download/image/${albumImageFileName}`,
  // });

  // const { response: audioFile, isLoading: audioLoading } = useAxios({
  //   method: "GET",
  //   url: `/download/image/${songFileName}`,
  // });

  useEffect(() => {
    const getAlbum = async () => {
      try {
        const response = await axios({
          method: "GET",
          url: `/download/image/${albumImageFileName}`,
          headers: {
            Authorization: `Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBY2Nlc3NUb2tlbiIsImV4cCI6MTcxMjAzODQ4NiwiZW1haWwiOiJHT09HTEVfMTAxNzU1MDkxNDQ0MzEzODA3MDcyIn0.TIZbya6B0w_wPKu_2ApOADfxNs8sOvu5GYhDyOMyegs`,
          },
        });
        if (response?.data?.data) {
          setImgFile(response.data.data);
        }
      } catch (error) {
        console.log(error);
      }
    };
    getAlbum();
  }, []);

  return (
    <div className={`flex-col-center ${styles.container}`}>
      <div className={styles.background}>
        <img src={imgFile} alt="" />
      </div>
      <div className={`flex-row ${styles.content}`}>
        <div className={`${styles.musicAlbum}`}>
          <Album />
        </div>
        <div className={`flex-col gap-15`}>
          <h1>{songtitle}</h1>
          <h2>{artist.username}</h2>
        </div>
      </div>
    </div>
  );
};

export default SongHeader;
