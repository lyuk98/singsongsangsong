import axios from "axios";
import { useAxios } from "../../hooks/api/useAxios";
import { axiosInstance } from "../../hooks/api";

export const getAlbumImg = async (imgName: string) => {
  try {
    const response = await axiosInstance({
      method: "GET",
      url: `download/image/${imgName}`,
    });
    return response;
  } catch (error) {
    console.log("error at getAlbum");
  }
};

export const getMp3File = async (fileName: string) => {
  try {
    const response = await axiosInstance({
      method: "GET",
      url: `download/audio/${fileName}`,
    });
    return response.data;
  } catch (error) {
    console.log("error at getMp3File");
  }
};
