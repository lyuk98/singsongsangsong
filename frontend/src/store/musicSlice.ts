import { createSlice } from "@reduxjs/toolkit";
import { isoParse } from "d3-time-format";

interface MusicState {
  isPlaying: boolean;
  musicList: any[];
}

const musicSlice = createSlice({
  name: "music",
  initialState: {
    isPlaying: false,
    musicList: [],
  } as MusicState,
  reducers: {
    changeState: (state) => {
      state.isPlaying = !state.isPlaying;
    },
    addMusicList: (state, action) => {
      state.musicList.push(action.payload);
    },
  },
});

export const musicAction = musicSlice.actions;
export default musicSlice;
