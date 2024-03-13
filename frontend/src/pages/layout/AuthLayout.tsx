import { Link, Outlet, useLocation } from "react-router-dom";
import styles from './AuthLayout.module.css'
import Notice from "../../components/auth/Notice";

const AuthLayout = () => {
    const { pathname } = useLocation();

    console.log(pathname)
    return (
        <>
            <div className={styles.container}>
                <div className={styles.title}>
                    <Link to='/'>싱송생송</Link>
                </div>
                <Notice pathname={pathname} />
                <Outlet />
            </div>
        </>
    );
};

export default AuthLayout;