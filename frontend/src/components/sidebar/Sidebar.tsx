import { NavLink } from "react-router-dom";

import { FaChartLine, FaMusic, FaRegQuestionCircle } from "react-icons/fa";
import { TbReportAnalytics } from "react-icons/tb";
import { RxPerson } from "react-icons/rx";
import { MdOutlineLogin, MdOutlineLogout } from "react-icons/md";
import { GiPoisonGas } from "react-icons/gi";

import styles from "./Sidebar.module.css";

const Sidebar = () => {
  const isLogin: boolean = false;

  return (
    <div className={styles.mainContainer}>
      <div className={styles.title}>
        <GiPoisonGas size="20px" className={styles.titleIcon} />
        <span>싱송생송</span>
      </div>
      <div className={styles.sidebarLinksSection}>
        <label htmlFor="sidebarLinks">Menu</label>
        <NavLink
          to="/trend"
          className={({ isActive }) => (isActive ? styles.active : undefined)}
        >
          <FaChartLine size="20px" /> <span>트렌드 확인</span>
        </NavLink>
        <NavLink
          to="/analysis"
          className={({ isActive }) => (isActive ? styles.active : undefined)}
        >
          <TbReportAnalytics size="20px" /> <span>분석하기</span>
        </NavLink>
        <NavLink
          to="/discover"
          className={({ isActive }) => (isActive ? styles.active : undefined)}
        >
          <FaMusic size="20px" /> <span>둘러보기</span>
        </NavLink>
      </div>
      <div className={styles.accountSection}>
        <label htmlFor="user">Account</label>
        {isLogin && (
          <>
            <NavLink
              to="/"
              className={({ isActive }) =>
                isActive ? styles.active : undefined
              }
            >
              <RxPerson size="20px" /> <span>Profile</span>
            </NavLink>
            <NavLink to="/discover">
              <MdOutlineLogout size="20px" /> <span>Logout</span>
            </NavLink>
          </>
        )}
        {!isLogin && (
          <>
            <NavLink
              to="/register"
              className={({ isActive }) =>
                isActive ? styles.active : undefined
              }
            >
              <RxPerson size="20px" /> <span>Sign Up</span>
            </NavLink>
            <NavLink
              to="/login"
              className={({ isActive }) =>
                isActive ? styles.active : undefined
              }
            >
              <MdOutlineLogin size="20px" /> <span>Login</span>
            </NavLink>
          </>
        )}
      </div>
      <div className={styles.aboutSection}>
        <label htmlFor="aboutSection">About</label>
        <NavLink to="/about">
          <FaRegQuestionCircle size="20px" /> <span>about</span>
        </NavLink>
      </div>
    </div>
  );
};

export default Sidebar;
