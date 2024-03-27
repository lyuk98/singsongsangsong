import React from "react";
import { Outlet } from "react-router-dom";

import styles from "./MainLayout.module.css";
import Sidebar from "../../components/sidebar/Sidebar";
import MusicPlayer from "../../components/musicPlayer/MusicPlayer";

const MainLayout = () => {
  return (
    <>
      <Sidebar />
      <main className={styles.container}>
        <Outlet />
        <MusicPlayer />
      </main>
    </>
  );
};

export default MainLayout;
