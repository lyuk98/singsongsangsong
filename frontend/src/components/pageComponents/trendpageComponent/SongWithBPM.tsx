import React, { useState } from "react";
import ReactSlider from "react-slider";
import styles from "./SongWithBPM.module.css";
import { HTMLProps } from "react";

const SongWithBPM = () => {
  const [bpm, setBpm] = useState<number>(120);

  const handleBpmChange = (value: number) => {
    console.log(bpm);
    setBpm(value);
  };
  return (
    <div className={`flex-col-center ${styles.container}`}>
      <div className={`${styles.content}`}>
        <ReactSlider
          className={styles.slider}
          thumbClassName={styles.sliderHead}
          marks={[120, 130, 140, 150, 160, 170, 180, 190, 200]}
          markClassName={styles.dots}
          pearling
          onChange={(e) => handleBpmChange(e)}
          value={bpm}
          min={120}
          max={200}
          step={10}
          renderThumb={(props, state) => <div {...props}>{state.valueNow}</div>}
          renderMark={(props: HTMLProps<HTMLSpanElement>) => {
            let val = props.value as number;
            return <span {...props}>{val}</span>;
          }}
        />
      </div>
    </div>
  );
};

export default SongWithBPM;
