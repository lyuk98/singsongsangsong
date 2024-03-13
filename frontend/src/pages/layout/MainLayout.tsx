import React from 'react';
import { Outlet } from 'react-router-dom';
import Sidebar from '../../components/sidebar/Sidebar';
const MainLayout = () => {
    return (
        <>
            <Sidebar />
            <main>
                <Outlet />
            </main>
        </>
    );
};

export default MainLayout;