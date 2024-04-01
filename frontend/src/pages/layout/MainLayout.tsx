import React, { useEffect } from "react";
import { Outlet, useLocation } from "react-router-dom";

import styles from "./MainLayout.module.css";
import Sidebar from "../../components/sidebar/Sidebar";
import MusicPlayer from "../../components/musicPlayer/MusicPlayer";
import ScrollToTop from "../../utils/ScrollToTop";
import { setCookie } from "../../utils/cookie";

const MainLayout = () => {
  const location = useLocation();
  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    const accessToken = searchParams.get("accessToken");
    if (accessToken) {
      setCookie("accessToken", accessToken, {
        path: "/",
        secure: true,
        sameSite: "none",
      });
    }
  }, []);
  return (
    <>
      <ScrollToTop />
      <Sidebar />
      <main className={styles.container}>
        <Outlet />
        <MusicPlayer />
      </main>
    </>
  );
};

export default MainLayout;
