import { createSlice } from "@reduxjs/toolkit";

const userSlice = createSlice({
  name: "user",
  initialState: {
    isLogin: false,
    userId: "",
    userName: "",
    userNickName: "",
  },
  reducers: {
    setLogin(state, action) {
      state.isLogin = true;
      state.userId = action.payload.id;
      state.userName = action.payload.username;
      state.userNickName = action.payload.nickname;
    },
    setLogout(state, action) {
      state.isLogin = false;
      state.userId = "";
      state.userName = "";
      state.userNickName = "";
    },
  },
});

export const userAction = userSlice.actions;
export default userSlice;
