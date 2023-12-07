import { useState } from "react";
import { useFullQuestion } from "../contexts/FullQuestionContext"
import { getFullQuestion, logInUser } from "../services/api";
import { isFullQuestion, isUser } from "../types";
import classNames from "classnames";
import { Alert } from "../components/Alert";
import { FloatingInput } from "../components/FloatingInput";
import { useUser } from "../contexts/UserContext";

export const Start = () => {
    const { setFullQuestion } = useFullQuestion();
    const { setUser } = useUser();

    const [usernameEntry, setUsernameEntry] = useState("");
    const [alert, setAlert] = useState<string | null>(null);
    const validLogIn = usernameEntry !== "" && usernameEntry.length <= 25;

    const onClickStart = async () => {
        await logInUserInDatabase();
        await getFullQuestionFromServer();
    }

    const logInUserInDatabase = async () => {
        const result = await logInUser(usernameEntry);
        if (isUser(result)) {
            setUser(result);
        } else {
            setAlert(`${result.statusCode} ${result.statusText}`);
        }
    }

    const getFullQuestionFromServer = async () => {
        const result = await getFullQuestion();
        if (isFullQuestion(result)) {
            setFullQuestion(result);
        } else {
            await getFullQuestionFromServer();
        }
    }

    return (
        <div className="`relative h-full w-screen bg-cover bg-center bg-no-repeat p-12 text-center bg-rijksmuseum">
            <div className="absolute2 bottom-0 left-0 right-0 top-0 h-full w-full overflow-hidden bg-fixed bg-black/60">
                <div className="flex h-full items-center justify-center">
                    <div className="text-white">
                        <h2 className="mb-4 text-4xl font-semibold">Welcome to the Rijksmuseum</h2>
                        <h4 className="mb-6 text-xl font-semibold">Enter your name to start</h4>

                        {alert && <Alert text={alert} onClick={() => setAlert(null)} />}
                        <div>
                            <ol>
                                <li className="mt-4">
                                    <FloatingInput
                                        id="player"
                                        label="Name of player"
                                        value={usernameEntry}
                                        onChange={e => setUsernameEntry(e.target.value)}
                                        hasError={usernameEntry == ""}
                                        
                                    />
                                </li>
                                <li className="mt-4">
                                    <button
                                        type="button"
                                        className={classNames(
                                            "rounded border-2",
                                            "border-neutral-50",
                                            "px-7",
                                            "pb-[8px]",
                                            "pt-[10px]",
                                            "text-sm",
                                            "font-medium",
                                            "uppercase",
                                            "leading-normal",
                                            "text-neutral-50",
                                            "transition duration-150",
                                            "ease-in-out",
                                            "hover:border-neutral-100",
                                            "hover:text-neutral-100",
                                            "focus:border-neutral-100",
                                            "focus:text-neutral-100",
                                            "focus:outline-none",
                                            "focus:ring-0",
                                            "active:border-neutral-200",
                                            "active:text-neutral-200",
                                            "hover:bg-neutral-100",
                                            "hover:bg-opacity-10",
                                            { "cursor-not-allowed opacity-30": !validLogIn }
                                        )}
                                        data-te-ripple-init
                                        data-te-ripple-color="light"
                                        disabled={!validLogIn}
                                        onClick={() => onClickStart()}
                                    >
                                        Start Game
                                    </button>
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}