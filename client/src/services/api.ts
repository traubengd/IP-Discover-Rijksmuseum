import { FullQuestion, Question, User } from "../types";

export async function getFullQuestion() {
    const questionAndAnswers = await getQuestionObjectAndAnswers();
    const questionImage = await getQuestionSubImage(questionAndAnswers.correctObjectCode);
    const fullQuestion: FullQuestion = {
        question: questionAndAnswers,
        image: questionImage,
        questionAnswered: false
    };
    return fullQuestion as FullQuestion;
}

export async function getQuestionObjectAndAnswers() {
    const response = await fetch("rijksmuseumquiz/getquestion")
    const result = await response.json();
    return result as Question;
}

export async function getQuestionSubImage(objectCode: String) {
    const response = await fetch("rijksmuseumquiz/getquestionsubimage", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ objectCode: objectCode })
    });

    const result = await response.blob();
    return result;
}

export async function getQuestionFullImage(objectCode: String) {
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

export async function logInUser(username: String) {
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

export async function getUserScore(username: String) {
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

export async function updateUserScore(username: String, scoreIncrease: number) {
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