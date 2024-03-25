import axios from "axios";

import { axiosInstance } from "../../hooks/api";

export const handleStartAnalyze = async (inputFile: File) => {
  const formData = new FormData();
  console.log(inputFile);
  formData.append("file", inputFile);
  console.log(formData);

  try {
    const result = await axios({
      url: `${process.env.REACT_APP_API_URL}/upload/audio`,
      method: "POST",
      data: formData,
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  } catch (error) {
    return error;
  }
};
