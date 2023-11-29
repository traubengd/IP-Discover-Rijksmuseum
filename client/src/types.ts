export type FullQuestion = {
    question: Question;
    image: Blob;
    questionAnswered: boolean;
}

export type Question = {
    correctObjectCode: string;
    answers: [Answer, Answer, Answer, Answer];
    correctAnswer: string;
    plaqueDescription: string;
    hintsUsed: number;
    pointsAvailable: number;
}

export type Answer = {
    longTitle: string;
    correctAnswer: boolean;
}

export type User = {
    username: string;
    userscore: number;
}

export function isFullQuestion(fullQuestion: unknown): fullQuestion is FullQuestion{
    return (fullQuestion as FullQuestion).question !== undefined && (fullQuestion as FullQuestion).image !== undefined;
}

export function isUser(user: unknown): user is User{
    return (user as User).username !== null;
}