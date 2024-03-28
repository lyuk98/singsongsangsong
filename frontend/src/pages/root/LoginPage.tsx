import React from "react";
import { Link } from "react-router-dom";

import styles from "./LoginPage.module.css";

import googleIcon from "./../../sources/imgs/auth/googleIcon.png";
import naverIcon from "./../../sources/imgs/auth/naverIcon.png";
import kakoIcon from "./../../sources/imgs/auth/kakaoIcon.png";

import AuthInput from "../../components/auth/AuthInput";
import Button from "../../components/buttons/Button";
import { useInput } from "../../hooks/useInput";
import { idValidator, passwordValidator } from "../../utils/validator";
import { axiosInstance } from "../../hooks/api";

const LoginPage = () => {
  const handleLogin = async (props: string) => {
    console.log("실행은되니?");
    try {
      const response = await axiosInstance.request({
        method: "GET",
        url: `/oauth2/authorization/${props}`,
      });
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <div className={styles.conatiner}>
        <div className={`flex-col-center gap-15 w-100 ${styles.socialBox}`}>
          <button
            className={`${styles.socialButton}`}
            onClick={() => handleLogin("kakao")}
          >
            <img src={kakoIcon} alt="" />
          </button>
          <button
            className={`${styles.socialButton}`}
            onClick={() => handleLogin("google")}
          >
            <img src={googleIcon} alt="" />
          </button>
          <button
            className={`${styles.socialButton}`}
            disabled={true}
            onClick={() => handleLogin("naver")}
          >
            <img src={naverIcon} alt="" />
          </button>
        </div>

        {/* <AuthInput
          id="id"
          label="id"
          name="id"
          type="text"
          placeholder="id"
          value={idValue}
          onChange={handleIdChange}
        />
        <AuthInput
          id="password"
          label="password"
          name="password"
          type="password"
          placeholder="password"
          value={passwordValue}
          onChange={handlePasswordChange}
        />
        <div className={styles.buttonBox}>
          <Button>Login</Button>
        </div>
        <div className={`flex-col-center gap-15 ${styles.linkBox}`}>
          <p>
            계정이없나요? <Link to="/register">sign up</Link>
          </p>
          <h2>소셜 로그인</h2>
          <div className={`flex-col-center gap-15 w-100 ${styles.socialBox}`}>
            <button className={`${styles.socialButton}`}>
              <img src={kakoIcon} alt="" />
            </button>
            <button className={`${styles.socialButton}`}>
              <img src={googleIcon} alt="" />
            </button>
            <button className={`${styles.socialButton}`}>
              <img src={naverIcon} alt="" />
            </button>
          </div>
        </div> */}
      </div>
    </>
  );
};

export default LoginPage;
