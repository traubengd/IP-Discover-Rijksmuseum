import { createContext, useContext, useState } from "react";
import { FullQuestion } from "../types";

type ContextType = {
    fullQuestion: FullQuestion | undefined,
    setFullQuestion: (fullQuestion: FullQuestion) => void;
}

const FullQuestionContext = createContext<ContextType>({
    fullQuestion: undefined,
    setFullQuestion() { },
});

type Props = React.PropsWithChildren;

export const FullQuestionProvider = (props: Props) => {
    const { children } = props;

    const [fullQuestion, setFullQuestion] = useState<FullQuestion | undefined>(undefined);

    return <FullQuestionContext.Provider value={{
        fullQuestion: fullQuestion,
        setFullQuestion: setFullQuestion
    }}>{children}</FullQuestionContext.Provider>
}

export const useFullQuestion = () => {
    const context = useContext(FullQuestionContext);

    if (context === undefined) {
        throw new Error('useFullQuestion must be used within a FullQuestionProvider');
    }

    return context;
}
