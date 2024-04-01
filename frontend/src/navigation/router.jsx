import { Navigate, createBrowserRouter } from "react-router-dom";
import MainLayout from "../pages/layout/MainLayout";

import TrendPage from "../pages/root/TrendPage";
import AnalysisPage from "../pages/root/AnalysisPage";
import DiscoverPage from "../pages/root/DiscoverPage";
import RegisterPage from "../pages/root/RegisterPage";
import LoginPage from "../pages/root/LoginPage";
import AuthLayout from "../pages/layout/AuthLayout";
import ArtistPage from "../pages/root/ArtistPage";
import SongDetailPage from "../pages/root/SongDetailPage";
import UploadPage from "../pages/root/UploadPage";
import PostPage from "../pages/root/PostPage";
import ErrorPage from "../pages/Error/ErrorPage";
import SearchResultPage from "../pages/root/SearchResultPage";
import TopRankSongPage from "../pages/root/TopRankSongPage";
import TopRankArtistPage from "../pages/root/TopRankArtistPage";
import FavoriteArtistPage from "../pages/root/FavoriteArtistPage";
import FavoriteSongPage from "../pages/root/FavoriteSongPage";
import RegisterSelectPage from "../pages/root/RegisterSelectPage";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <MainLayout />,
    errorElement: <ErrorPage />,
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
        path: "discover/:search",
        element: <SearchResultPage />,
      },
      {
        path: "discover/playlist/:type/:keyword",
        element: <TopRankSongPage />,
      },
      {
        path: "discover/artist/:type",
        element: <TopRankArtistPage />,
      },
      {
        path: "artist/:artistId",
        element: <ArtistPage />,
      },
      {
        path: "song/:songId",
        element: <SongDetailPage />,
      },
      {
        path: "favorite/artist",
        element: <FavoriteArtistPage />,
      },
      {
        path: "favorite/song",
        element: <FavoriteSongPage />,
      },
    ],
  },
  {
    element: <AuthLayout />,
    children: [
      {
        path: "/sign-up",
        element: <RegisterPage />,
      },
      {
        path: `/select-sign-up`,
        element: <RegisterSelectPage />,
      },
      {
        path: "/login",
        element: <LoginPage />,
      },
    ],
  },
]);
