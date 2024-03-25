import { createSlice } from "@reduxjs/toolkit";

const musicSlice = createSlice({
  name: "music",
  initialState: {
    isPlaying: false,
    currentPlayMusic: null,
  },
  reducers: {},
});

export const musicAction = musicSlice.actions;
export default musicSlice;
