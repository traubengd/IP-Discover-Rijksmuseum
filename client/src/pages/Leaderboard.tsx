import { useEffect, useState } from "react";
import { User } from "../types";
import { getLeaderboard } from "../services/api";

export const Leaderboard = () => {

    const [leaderboard, setLeaderboard] = useState<User[]>([]);

    const initialiseLeaderboard = async () => {
        const result = await getLeaderboard();
        setLeaderboard(result);
    }

    useEffect(() => {
        initialiseLeaderboard();
    }, []);

    return <div className="flex h-full items-center justify-center bg-transparent">
        <article className="prose h-min w-full text-center text-2xl">
            <h1>Leaderboard</h1>
            <div className="mx-44">
            {leaderboard!.map(entry =>
                <li className="flex my-0 p-2 justify-center odd:bg-slate-200" key={entry.username}>
                    <span className="mr-8 flex justify-end w-1/2">
                        {entry.username}
                    </span>
                    <span className="ml-8 flex justify-start w-1/2">
                        {entry.userscore}
                    </span>
                </li>
            )}
            </div>
        </article>
    </div>
}
