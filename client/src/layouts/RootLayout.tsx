import { Outlet, useLocation } from "react-router-dom";
import { NavButton } from "../components/NavButton";
import { Dropdown, Popover, IconButton, Whisper } from 'rsuite';
import { RefObject } from "react";
import ArrowDownIcon from '@rsuite/icons/ArrowDown';
import { setSearchParameters } from "../services/api";

export const RootLayout = () => {
    const { pathname } = useLocation();

    const renderMenu = ({ onClose, left, top, className }: any, ref: ((instance: HTMLDivElement | null) => void) | RefObject<HTMLDivElement> | null | undefined) => {
        const handleSelect = async (eventKey: any) => {
            onClose();
            await setSearchParameters(eventKey.toString());
        };
        return (
            <Popover ref={ref} className={className} style={{ left, top }} full>
                <Dropdown.Menu onSelect={handleSelect}>
                    <Dropdown.Item eventKey={"&toppieces=true"}>Standaard top pieces collectie</Dropdown.Item>
                    <Dropdown.Menu title="Kunstenaars">
                        <Dropdown.Item eventKey={"&involvedMaker=Jozef+Israëls"}>Jozef Israëls</Dropdown.Item>
                        <Dropdown.Item eventKey={"&involvedMaker=Isaac+Israels"}>Isaac Israels</Dropdown.Item>
                        <Dropdown.Item eventKey={"&involvedMaker=Rembrandt+van+Rijn"}>Rembrandt van Rijn</Dropdown.Item>
                        <Dropdown.Item eventKey={"&involvedMaker=Leo+Gestel"}>Leo Gestel</Dropdown.Item>
                        <Dropdown.Item eventKey={"&involvedMaker=Anton+Mauve"}>Anton Mauve</Dropdown.Item>
                        <Dropdown.Item eventKey={"&involvedMaker=Johannes+Vermeer"}>Johannes Vermeer</Dropdown.Item>
                        <Dropdown.Item eventKey={"&involvedMaker=Jan+Havicksz.+Steen"}>Jan Steen</Dropdown.Item>
                    </Dropdown.Menu>
                    <Dropdown.Menu title="Eeuw">
                        <Dropdown.Item eventKey={"&f.dating.period=15"}>15e Eeuw</Dropdown.Item>
                        <Dropdown.Item eventKey={"&f.dating.period=16"}>16e Eeuw</Dropdown.Item>
                        <Dropdown.Item eventKey={"&f.dating.period=17"}>17e Eeuw</Dropdown.Item>
                        <Dropdown.Item eventKey={"&f.dating.period=18"}>18e Eeuw</Dropdown.Item>
                        <Dropdown.Item eventKey={"&f.dating.period=19"}>19e Eeuw</Dropdown.Item>
                        <Dropdown.Item eventKey={"&f.dating.period=20"}>20e Eeuw</Dropdown.Item>
                    </Dropdown.Menu>
                </Dropdown.Menu>
            </Popover>
        );
    };

    return <div className="flex flex-col h-screen ">
        <header className="bg-amber-100 shadow-lg flex flex-row p-4">
            <nav className="pt-4 flex-1 flex flex-row justify-center gap-2">
                <NavButton to="/" text="Play the quiz" isActive={pathname === "/"} />
                <NavButton to="/leaderboard" text="Leaderboard" isActive={pathname === "/leaderboard"} />
                <NavButton to="/artworkoftheday" text="Artwork of the Day" isActive={pathname==="/artworkoftheday"} />
                <NavButton to="/about" text="About" isActive={pathname === "/about"} />
                <Whisper placement="bottomStart" trigger="click" speaker={renderMenu}>
                    <IconButton appearance="default" icon={<ArrowDownIcon />} placement="left">
                        Change collection
                    </IconButton>
                </Whisper>
            </nav>
        </header>
        <main className="flex-1">
            <Outlet />
        </main>
    </div>
};
