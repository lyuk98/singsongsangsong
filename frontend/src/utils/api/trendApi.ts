import axios from "axios";
import { axiosInstance } from "../../hooks/api";

export const getGenreTrend = () => {
  try {
    const response = axiosInstance.request({
      method: "GET",
      url: `/trend/genre/`,
    });
  } catch (error) {
    console.log(error);
  }
};

export const getAtmosphereTrend = () => {
  try {
    const response = axiosInstance.request({
      method: "GET",
      url: `/trend/atmosphere`,
    });
    return response;
  } catch (error) {
    console.log(error);
  }
};

export const getBpmTrend = () => {
  try {
    const reponse = axiosInstance.request({
      method: "GET",
      url: `/trend/bpm`,
    });
    return reponse;
  } catch (error) {
    console.log(error);
  }
};
