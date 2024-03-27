import React, { useEffect, useRef } from "react";

import AudioPlayer from "react-h5-audio-player";
import "react-h5-audio-player/lib/styles.css";

import styles from "./MusicPlayer.module.css";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../store";

const MusicPlayer = () => {
  const trackList = useSelector((state: RootState) => state.music);
  const player = useRef<any>();

  useEffect(() => {
    player.current.audio.current.addEventListener("ended", (event: any) => {
      console.log("ended");
    });
  }, []);

  return (
    <div className={`w-100 ${styles.container}`}>
      <AudioPlayer
        src={trackList.musicList[0]}
        ref={player}
        volume={0.5}
        autoPlay={false}
      />
    </div>
  );
};

export default MusicPlayer;
