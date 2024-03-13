import { Navigate, createBrowserRouter } from "react-router-dom";
import MainLayout from "../pages/layout/MainLayout";

import TrendPage from "../pages/root/TrendPage";
import AnalysisPage from "../pages/root/AnalysisPage";
import DiscoverPage from "../pages/root/DiscoverPage";
import RegisterPage from "../pages/root/RegisterPage";
import LoginPage from "../pages/root/LoginPage";

export const router = createBrowserRouter([
    {
        path: '/',
        element: <MainLayout />,
        children: [
            { index: true, element: <Navigate to="/trend" replace /> },
            {
                path: "trend",
                element: <TrendPage />,
            },
            {
                path: "analysis",
                element: <AnalysisPage />,
            },
            {
                path: "discover",
                element: <DiscoverPage />,
            },
        ]
    },
    {
        path: '/register',
        element: <RegisterPage />
    },
    {
        path: '/login',
        element: <LoginPage />
    },
])