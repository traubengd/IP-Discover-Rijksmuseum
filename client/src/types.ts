export type FullQuestion = {
    questionAnswerData: QuestionAnswerData;
    image: Blob;
    hintsUsed: number;
    pointsAvailable: number;
    questionType: QuestionType;
}

export type QuestionAnswerData = {
    correctObjectCode: string;
    answers: [Answer, Answer, Answer, Answer];
    correctAnswer: string;
    artworkDate: number;
    plaqueDescription: string;
}

export type Answer = {
    longTitle: string;
    correctAnswer: boolean;
}

export type User = {
    username: string;
    userscore: number;
}

export enum QuestionType {
    ImageFragment,
    ColourScheme,
    Timeline,
    Answered
}

export function isFullQuestion(fullQuestion: unknown): fullQuestion is FullQuestion{
    return (fullQuestion as FullQuestion).questionAnswerData !== undefined && (fullQuestion as FullQuestion).image !== undefined && (fullQuestion as FullQuestion).questionType !== undefined;
}

export function isUser(user: unknown): user is User{
    return (user as User).username !== null;
}