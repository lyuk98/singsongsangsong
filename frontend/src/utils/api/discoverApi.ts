import axios from "axios";
import { AxiosInstance } from "axios";
import { useAxios } from "../../hooks/api/useAxios";
import { axiosInstance } from "../../hooks/api";

export const getLikeSong = async (
  artistId: number,
  offset: number,
  limit: number
) => {
  try {
    const response = await axiosInstance.request({
      method: "GET",
      url: "",
      data: {
        artistId: artistId,
        offset: offset,
        limit: limit,
      },
    });
  } catch (error) {
    console.log(error);
  }
};

export const getFollowerNumber = async (artistId: string) => {
  try {
    const response = await axiosInstance.request({
      method: "GET",
      url: `/artist/followers/${artistId}/count`,
    });
    return response;
  } catch (error) {
    console.log(error);
  }
};
