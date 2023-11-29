import { createBrowserRouter } from "react-router-dom";
import { RootLayout } from "./layouts/RootLayout";
import { ErrorPage } from "./pages/ErrorPage";
import { About } from "./pages/About";
import { Quizgame } from "./pages/Quizgame";
import { Leaderboard } from "./pages/Leaderboard";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <RootLayout />,
        errorElement: <ErrorPage />,
        children: [
            {
                index: true,
                element: <Quizgame />
            },
            {
                path: "leaderboard",
                element: <Leaderboard />
            },
            {
                path: "about",
                element: <About />
            }
        ]
    }
]);
