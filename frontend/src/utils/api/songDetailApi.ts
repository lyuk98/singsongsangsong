import axios from "axios";
import { AxiosInstance } from "axios";
import { axiosInstance } from "../../hooks/api";

/**
 * 곡의 감정 표현을 올려줄 api
 * @param songId
 * @param emotion
 */
export const updateEmotion = (songId: number, emotion: string) => {
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

export const getAnalyzeResult = (songId: number) => {
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

export const getSongSimilarity = (songId: number) => {
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

export const getSongComment = (songId: number) => {
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
