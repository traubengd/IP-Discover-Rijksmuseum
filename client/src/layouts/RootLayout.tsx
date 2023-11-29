import { Outlet, useLocation } from "react-router-dom";
import { NavButton } from "../components/NavButton";

export const RootLayout = () => {
    const { pathname } = useLocation();

    return <div className="flex flex-col h-screen ">
        <header className="bg-amber-100 shadow-lg flex flex-row p-4">
            <nav className="pt-4 flex-1 flex flex-row justify-center gap-2">
                <NavButton to="/" text="Play the quiz" isActive={pathname === "/"} />
                <NavButton to="/leaderboard" text="Leaderboard" isActive={pathname === "/leaderboard"} />
                <NavButton to="/about" text="About" isActive={pathname === "/about"} />
            </nav>
        </header>
        <main className="flex-1">
            <Outlet />
        </main>
    </div>
};
