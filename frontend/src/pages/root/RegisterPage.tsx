import axios from "axios";
import React, { useState, ChangeEvent, useRef, FormEvent } from "react";
import {
  Form,
  Link,
  ActionFunction,
  redirect,
  useLocation,
} from "react-router-dom";
import { GrPowerReset } from "react-icons/gr";

import styles from "./RegisterPage.module.css";
import AuthInput from "../../components/auth/AuthInput";
import Button from "../../components/buttons/Button";
import { useInput } from "../../hooks/useInput";
import {
  passwordValidator,
  descValidator,
  nicknameValidator,
} from "../../utils/validator";
import { axiosInstance } from "../../hooks/api";

const AGES = [
  { data: 10, text: "10대" },
  { data: 20, text: "20대" },
  { data: 30, text: "30대" },
  { data: 40, text: "40대" },
  { data: 50, text: "50대" },
  { data: 6, text: "50대 이상" },
];

const RegisterPage = () => {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const accessToken = searchParams.get("accessToken");
  console.log(accessToken);
  const {
    value: nicknameValue,
    handleInputChange: handleNicknameChange,
    valueIsValid: nicknameIsValid,
  } = useInput("", nicknameValidator);

  const {
    value: descValue,
    handleInputChange: handleDescChange,
    valueIsValid: descIsValid,
  } = useInput("", descValidator);

  // 회원가입 관련 state들
  const fileInputRef = useRef<HTMLInputElement>(null);
  const [profileImage, setProfileImage] = useState<File | null>(null);
  const [profileUrl, setProfileUrl] = useState<string>("");
  const [gender, setGender] = useState<undefined | string>(undefined);
  const [age, setAge] = useState<undefined | number>(undefined);
  // 프로필 사진 설정 시 호버했을 때 리셋 버튼을 보여줄 boolean값
  const [isHover, setIsHover] = useState<boolean>(false);

  const handleGenderChange = (event: ChangeEvent<HTMLSelectElement>): void => {
    setGender(event.target.value);
  };

  const handleAgeChange = (event: ChangeEvent<HTMLSelectElement>): void => {
    setAge(+event.target.value);
  };

  // 프로필 이미지를 등록
  const handleFileUpload = (event: ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;
    console.log(files);
    console.log(new Date());
    if (files && files[0] && files[0].type.startsWith("image/")) {
      setProfileUrl(URL.createObjectURL(files[0]));
      setProfileImage(files[0]);
    }
  };

  // 프로필 이미지를 리셋
  const handleImageReset = () => {
    setProfileImage(null);
  };

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
      const response = await axios({
        method: "POST",
        url: `${process.env.REACT_APP_API_URL}artist/join`,
        data: {
          nickname: nicknameValue,
          profileImage: profileImage,
          age,
          sex: gender,
          introduction: descValue,
        },
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className={styles.container}>
      <form onSubmit={handleSubmit}>
        <div className={`w-100 p-15 flex-col-center`}>
          <>
            {profileImage && (
              <div
                onMouseOver={() => setIsHover(true)}
                onMouseLeave={() => setIsHover(false)}
                className={`${styles.imageContainer}`}
              >
                <img src={profileUrl} className={`${styles.profileImage}`} />
                <div
                  className={` flex-col-center ${styles.resetBtn} ${
                    isHover ? styles.active : ""
                  }`}
                >
                  <GrPowerReset
                    onClick={handleImageReset}
                    style={{ cursor: "pointer" }}
                    size={42}
                  />
                </div>
              </div>
            )}
            {!profileImage && (
              <label className={`flex-col-center p-15 ${styles.inputForm}`}>
                <input
                  type="file"
                  id="albumImg"
                  className={`${styles.imgInput}`}
                  accept="image/jpeg, image/png, image/jpg"
                  ref={fileInputRef}
                  onChange={handleFileUpload}
                  required
                />
                <h2>프로필 사진을 업로드 해주세요</h2>
              </label>
            )}
          </>
        </div>
        <AuthInput
          id="nickname"
          label="닉네임"
          name="nickname"
          type="text"
          placeholder="닉네임을 입력해주세요"
          value={nicknameValue}
          onChange={handleNicknameChange}
          required
        />
        <AuthInput
          id="intro"
          label="소개"
          name="intro"
          type="textarea"
          placeholder="자기소개를 입력해주세요"
          value={descValue}
          onChange={handleDescChange}
          required
        />

        <div className={styles.selectBox}>
          <label>성별</label>
          <select
            name="gender"
            value={gender}
            onChange={handleGenderChange}
            required
          >
            <option key="none" value={""}>
              성별을 선택해주세요
            </option>
            <option key="male" value="male">
              남성
            </option>
            <option key="female" value="female">
              여성
            </option>
          </select>

          <label>연령</label>
          <select name="age" value={age} onChange={handleAgeChange} required>
            <option value={""}>연령대를 설정해주세요</option>
            {AGES.map((element) => {
              return (
                <option key={element.data} value={element.data}>
                  {element.text}
                </option>
              );
            })}
          </select>
        </div>
        <div className={styles.bottomBox}>
          <Button>Register</Button>
          <p>
            You hava account?<Link to="/login">Login</Link>{" "}
          </p>
        </div>
      </form>
    </div>
  );
};

export default RegisterPage;
