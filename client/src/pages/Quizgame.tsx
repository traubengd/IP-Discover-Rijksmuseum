import { useFullQuestion } from "../contexts/FullQuestionContext";
import { useUser } from "../contexts/UserContext";
import { Play } from "./Play";
import { Start } from "./Start";

export const Quizgame = () => {
    const { fullQuestion } = useFullQuestion();
    const { user } = useUser();
    return fullQuestion && user ? <Play /> : <Start />;
};