import axios from "axios";
import { AxiosInstance } from "axios";
import { axiosInstance } from "../../hooks/api";

/**
 * 곡의 감정 표현을 올려줄 api
 * @param songId
 * @param emotion
 */
export const updateEmotion = (songId: string | undefined, emotion: string) => {
  try {
    const response = axiosInstance.request({
      method: "PUT",
      url: `/song/${songId}/${emotion}`,
      data: {
        songId: songId,
        emotion: emotion,
      },
    });
    console.log(response);
  } catch (error) {
    console.log(error);
  }
};

/**
 * @param songId 댓글을 달 노래의 id
 * @param contents 댓글 내용
 */
export const postComment = (songId: string | undefined, contents: string) => {
  if (!songId) {
    console.log("존재하지 않는 id입니다");
    return;
  }
  try {
    const response = axiosInstance.request({
      method: "POST",
      url: "/song/comments",
      data: {
        songId: songId,
        contents: contents,
      },
    });
  } catch (error) {
    console.log(error);
  }
};

/**
 * 해당 장르에 해당하는 곡과 가장 높은 연관성을 가진 분위기와 장르를 가져옴
 * @param songId
 * @returns
 */
export const getAnalyzeResult = (songId: string | undefined) => {
  try {
    const response = axiosInstance.request({
      method: "GET",
      url: `/song/analyze/${songId}?size=5`,
    });
    return response;
  } catch (error) {
    console.log(error);
  }
};

export const getSongSimilarity = (songId: string | undefined) => {
  try {
    const response = axiosInstance.request({
      method: "GET",
      url: `/song/similarity/${songId}`,
    });
    return response;
  } catch (error) {
    console.log(error);
  }
};

/**
 * 해당 노래에 대한 댓글들을 가져오는 함수
 * @param songId
 * @returns
 */
export const getSongComment = (songId: string | undefined) => {
  try {
    const response = axiosInstance.request({
      method: "GET",
      url: `/song/comment/${songId}`,
    });
    return response;
  } catch (error) {
    console.log(error);
  }
};

/**
 * 해당 곡에 대한 다운로드 수를 증가 시키는 함수
 * @param songId
 */
export const downloadSong = (songId: string | undefined) => {
  try {
    const response = axiosInstance.request({
      method: "POST",
      url: `/song/download/${songId}`,
    });
  } catch (error) {
    console.log(error);
  }
};

/**
 * 해당 곡에 대한 재생 수를 증가시키는 함수
 * @param songId
 */
export const playSong = (songId: string | undefined) => {
  try {
    const response = axiosInstance.request({
      method: "POST",
      url: `/song/play/${songId}`,
    });
  } catch (error) {
    console.log(error);
  }
};
