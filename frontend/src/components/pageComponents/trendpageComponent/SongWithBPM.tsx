import React, { useState } from "react";
import ReactSlider from "react-slider";
import styles from "./SongWithBPM.module.css";
import AlbumWithInfo from "../../public/AlbumWithInfo";
import RankedSongAndArtist from "./RankedSongAndArtist";

const SongWithBPM = () => {
  const [bpm, setBpm] = useState<number>(120);

  const handleBpmChange = (value: number) => {
    console.log(bpm);
    setBpm(value);
  };
  return (
    <div className={`flex-col-center ${styles.container}`}>
      <div className={`flex-col-center ${styles.content}`}>
        <ReactSlider
          className={`flex-row-center ${styles.slider}`}
          thumbClassName={styles.sliderHead}
          trackClassName={styles.track}
          marks={[120, 130, 140, 150, 160, 170, 180, 190, 200]}
          markClassName={styles.dots}
          onChange={(e) => handleBpmChange(e)}
          value={bpm}
          min={120}
          max={200}
          step={10}
          renderThumb={(props, state) => <div {...props}>{state.valueNow}</div>}
        />
        <div className={`flex-col-center ${styles.numberOfSong}`}>
          <p>
            이번주에는 <span style={{ fontSize: "34px" }}>{"162"}</span> 개의
            노래가
          </p>
          <p>
            <span>{bpm}</span>
            <span>&nbsp;~&nbsp;</span>
            <span>{bpm + 10}</span>
            BPM으로 탄생했습니다!
          </p>
        </div>
        <div className={`flex-row-center ${styles.songs}`}>
          <div className={`flex-col-center ${styles.song}`}>
            <h2>bpm</h2>
            <RankedSongAndArtist type="song" showIndicator={false} />
          </div>
          <div className={`flex-col-center ${styles.song}`}>
            <h2>bpm</h2>
            <RankedSongAndArtist type="song" showIndicator={false} />
          </div>
          <div className={`flex-col-center ${styles.song}`}>
            <h2>bpm</h2>
            <RankedSongAndArtist type="song" showIndicator={false} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default SongWithBPM;
