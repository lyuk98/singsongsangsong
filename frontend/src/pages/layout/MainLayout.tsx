import React from "react";
import { Outlet } from "react-router-dom";

import styles from "./MainLayout.module.css";
import Sidebar from "../../components/sidebar/Sidebar";
import MusicPlayer from "../../components/musicPlayer/MusicPlayer";
import ScrollToTop from "../../utils/ScrollToTop";

const MainLayout = () => {
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
