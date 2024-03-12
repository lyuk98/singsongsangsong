import { createBrowserRouter } from "react-router-dom";
import LandingPage from "../pages/root/LandingPage";

export const router = createBrowserRouter([
    {
        path: '/',
        element: <LandingPage />,
    }
])