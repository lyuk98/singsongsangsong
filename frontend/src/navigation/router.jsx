import { Navigate, createBrowserRouter } from "react-router-dom";
import MainLayout from "../pages/layout/MainLayout";

import TrendPage from "../pages/root/TrendPage";
import AnalysisPage from "../pages/root/AnalysisPage";
import DiscoverPage from "../pages/root/DiscoverPage";
import RegisterPage, {
  action as RegisterAction,
} from "../pages/root/RegisterPage";
import LoginPage from "../pages/root/LoginPage";
import AuthLayout from "../pages/layout/AuthLayout";
import ArtistPage from "../pages/root/ArtistPage";
import SongDetailPage from "../pages/root/SongDetailPage";
import UploadPage from "../pages/root/UploadPage";
import PostPage from "../pages/root/PostPage";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <MainLayout />,
    children: [
      { index: true, element: <Navigate to="/trend" replace /> },
      {
        path: "trend",
        element: <TrendPage />,
      },
      {
        path: "upload",
        element: <UploadPage />,
      },
      {
        path: "upload/:songName",
        element: <AnalysisPage />,
      },
      {
        path: "upload/:songName/post",
        element: <PostPage />,
      },
      {
        path: "discover",
        element: <DiscoverPage />,
      },
      {
        path: "artist/:artistId",
        element: <ArtistPage />,
      },
      {
        path: "song/:songId",
        element: <SongDetailPage />,
      },
    ],
  },
  {
    element: <AuthLayout />,
    children: [
      {
        path: "/register",
        element: <RegisterPage />,
        action: RegisterAction,
      },
      {
        path: "/login",
        element: <LoginPage />,
      },
    ],
  },
]);
