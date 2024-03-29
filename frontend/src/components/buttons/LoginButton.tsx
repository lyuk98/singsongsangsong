import React from "react";
import styles from "./LoginButton.module.css";

type ProspsType = {
  name: string;
  url: string;
  img: string;
};

const LoginButton = ({ name, url, img }: ProspsType) => {
  const redirectUrl = `${process.env.REACT_APP_API_URL}oauth2/authorization/${url}`;
  
  const handleRedirect = () => {
    window.location.href = redirectUrl;
  };

  return (
    <div className={`${styles.loginBtn}`} onClick={handleRedirect}>
      <img src={img} alt={name} />
    </div>
  );
};

export default LoginButton;
