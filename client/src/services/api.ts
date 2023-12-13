import { FullQuestion, QuestionAnswerData, User, QuestionType, ArtworkOfTheDayData, ArtworkOfTheDayType } from "../types";

export async function getFullQuestion() {
    const questionAndAnswers = await getQuestionObjectAndAnswers();
    const randomSelector = Math.random();
    if (randomSelector < 0.2) { //Randomly choose question type
        const questionImage = await getQuestionColourScheme(questionAndAnswers.correctObjectCode);
        if (questionImage != null) {
            const fullQuestion: FullQuestion = {
                questionAnswerData: questionAndAnswers,
                image: questionImage,
                hintsUsed: 0,
                pointsAvailable: 10,
                questionType: QuestionType.ColourScheme
            };
            return fullQuestion as FullQuestion;
        }
        else {
            return getFullQuestion();
        }
    } else if (randomSelector < 0.4) {
        const questionImage = await getQuestionFullImage(questionAndAnswers.correctObjectCode);
        if (questionImage != null) {
            const fullQuestion: FullQuestion = {
                questionAnswerData: questionAndAnswers,
                image: questionImage,
                hintsUsed: 0,
                pointsAvailable: 10,
                questionType: QuestionType.Timeline
            };
            return fullQuestion as FullQuestion;
        }
        else {
            return getFullQuestion();
        }
    } else {
        const questionImage = await getQuestionSubImage(questionAndAnswers.correctObjectCode);
        if (questionImage != null) {
            const fullQuestion: FullQuestion = {
                questionAnswerData: questionAndAnswers,
                image: questionImage,
                hintsUsed: 0,
                pointsAvailable: 5,
                questionType: QuestionType.ImageFragment
            };
            return fullQuestion as FullQuestion;
        }
        else {
            return getFullQuestion();
        }
    }
}

export async function getQuestionObjectAndAnswers() {
    const response = await fetch("rijksmuseumquiz/getquestion")
    const result = await response.json();
    return result as QuestionAnswerData;
}

export async function getQuestionSubImage(objectCode: string) {
    const response = await fetch("rijksmuseumquiz/getquestionsubimage", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ objectCode: objectCode })
    });

    if (response.ok) {
        const result = await response.blob();
        return result;
    }
    else {
        return null;
    }
}

export async function getQuestionColourScheme(objectCode: string) {
    const response = await fetch("rijksmuseumquiz/getquestioncolourscheme", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ objectCode: objectCode })
    });

    if (response.ok) {
        const result = await response.blob();
        return result;
    }
    else {
        return null;
    }
}

export async function getQuestionFullImage(objectCode: string) {
    const response = await fetch("rijksmuseumquiz/getquestionfullimage", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ objectCode: objectCode })
    });

    const result = await response.blob();
    return result;

}

export async function logInUser(username: string) {
    const response = await fetch("rijksmuseumquiz/loginuser", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ username: username })
    });
    if (response.ok) {
        const result = await response.json();
        return result as User;
    } else {
        return {
            statusCode: response.status,
            statusText: response.statusText
        };
    }
}

export async function getUserScore(username: string) {
    const response = await fetch("rijksmuseumquiz/getuserscore", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ username: username })
    });
    if (response.ok) {
        const result = await response.json();
        return result as User;
    } else {
        return {
            statusCode: response.status,
            statusText: response.statusText
        };
    }
}

export async function updateUserScore(username: string, scoreIncrease: number) {
    const response = await fetch("rijksmuseumquiz/updateuserscore", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ username: username, scoreIncrease: scoreIncrease })
    });
    if (response.ok) {
        return response;
    } else {
        return {
            statusCode: response.status,
            statusText: response.statusText
        };
    }
}

export async function getLeaderboard() {
    const response = await fetch("rijksmuseumquiz/getleaderboard")
    const result = await response.json();
    return result as User[];
}

export async function setSearchParameters(searchParameters: string) {
    const response = await fetch("rijksmuseumquiz/setsearchparameters", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ searchParameters: searchParameters})
    });
    if (response.ok) {
        return response;
    } else {
        return {
            statusCode: response.status,
            statusText: response.statusText
        };
    }
}

export async function getArtworkOfTheDay() {
    const response = await fetch("rijksmuseumquiz/getartworkoftheday");
    const result = await response.json();
    const artworkData = result as ArtworkOfTheDayData;
    const artworkImage = await getQuestionFullImage(artworkData.objectCode);
    const artworkOfTheDay: ArtworkOfTheDayType = {
        artworkData: artworkData,
        image: artworkImage
    }
    return artworkOfTheDay as ArtworkOfTheDayType;
}