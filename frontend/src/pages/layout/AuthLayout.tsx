import { Link, Outlet, useLocation } from "react-router-dom";

import titleImg from "./../../sources/imgs/title/logo_투명.png";
import styles from "./AuthLayout.module.css";
import Notice from "../../components/auth/Notice";

const AuthLayout = () => {
  const { pathname } = useLocation();

  return (
    <>
      <div className={styles.container}>
        <div className={styles.title}>
          <Link to="/">
            <img src={titleImg} alt="" />
          </Link>
        </div>
        <Notice pathname={pathname} />
        <Outlet />
      </div>
    </>
  );
};

export default AuthLayout;
