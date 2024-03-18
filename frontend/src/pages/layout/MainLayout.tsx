import React from 'react';
import { Outlet } from 'react-router-dom';

import styles from './MainLayout.module.css'
import Sidebar from '../../components/sidebar/Sidebar';

const MainLayout = () => {
    return (
        <>
            <Sidebar />
            <main className={styles.container}>
                <Outlet />
            </main>
        </>
    );
};

export default MainLayout;