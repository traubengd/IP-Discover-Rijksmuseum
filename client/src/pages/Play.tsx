import { useEffect, useState } from 'react'
import { getQuestionFullImage, updateUserScore, getUserScore, getFullQuestion } from '../services/api'
import { useFullQuestion } from '../contexts/FullQuestionContext';
import { Alert } from '../components/Alert';
import { isFullQuestion, isUser } from '../types';
import classNames from 'classnames';
import { useUser } from '../contexts/UserContext';

export const Play = () => {
  const { fullQuestion, setFullQuestion } = useFullQuestion();
  const { user, setUser } = useUser();
  const [imageSize, setImageSize] = useState("object-none w-[20%]");
  const [questionImageUrl, setQuestionImageUrl] = useState(URL.createObjectURL(fullQuestion!.image));
  const [alert, setAlert] = useState<string | null>(null);
  const [answerColour, setAnswerColour] = useState("bg-black/60");

  const buttonLayout = "rounded border-2 p-2 m-5 bg-slate-200 hover:brightness-125"

  const nextQuestionClick = async () => {
    const result = await getFullQuestion();

    if (isFullQuestion(result)) {
      setFullQuestion(result);
      setAnswerColour("bg-black/60");
      setImageSize("object-none w-[20%]");
    } else {
      await nextQuestionClick();
    }
  }

  useEffect(() => {
    setQuestionImageUrl(URL.createObjectURL(fullQuestion!.image));
  }, [fullQuestion]);


  const getQuestionFullImageFromServer = async () => {
    const imageResult = await getQuestionFullImage(fullQuestion!.question.correctObjectCode);
    setQuestionImageUrl(URL.createObjectURL(imageResult));
  }

  const updateUserScoreInDatabase = async (scoreIncrease: number) => {
    await updateUserScore(user!.username, scoreIncrease);
    const userResult = await getUserScore(user!.username);
    if (isUser(userResult)) {
      setUser(userResult);
    } else {
      setAlert(`${userResult.statusCode} ${userResult.statusText}`);
    }
  }

  const revealAnswers = (index: number) => {
    if (fullQuestion!.question.answers[index].correctAnswer) {
      setAnswerColour("bg-green-700/60");
      updateUserScoreInDatabase(fullQuestion!.question.pointsAvailable);
    } else {
      setAnswerColour("bg-red-600/60");
    }
    getQuestionFullImageFromServer();
    fullQuestion!.questionAnswered = true;
    setImageSize("");
  }

  const getHint = () => {
    switch (fullQuestion!.question.hintsUsed) {
      case 0:
        setImageSize("object-none w-[35%]");
        fullQuestion!.question.hintsUsed++;
        fullQuestion!.question.pointsAvailable--;
        break;
      case 1:
        setImageSize("object-none w-[50%]");
        fullQuestion!.question.hintsUsed++;
        fullQuestion!.question.pointsAvailable--;
        break;
      case 2:
        setImageSize("object-none w-[65%]");
        fullQuestion!.question.hintsUsed++;
        fullQuestion!.question.pointsAvailable--;
        break;
      case 3:
        setImageSize("object-none w-[80%]");
        fullQuestion!.question.hintsUsed++;
        fullQuestion!.question.pointsAvailable--;
        break;
      default:
        break;
    }
  }

  return (
    <>
      <div className="`relative h-full w-screen bg-cover bg-center bg-no-repeat p-12 bg-rijksmuseum">
        <div className={classNames("absolute2 bottom-0 left-0 right-0 top-0 h-full w-full overflow-hidden bg-fixed bg-black/60", answerColour)}>
          <h1 className="text-5xl font-semibold text-center m-5 text-white">Discover the Rijksmuseum Collection</h1>
          <h1 className="text-l font-semibold m-2 ml-5 text-white">Current player: {user!.username}</h1>
          <h1 className="text-l font-semibold m-2 ml-5 text-white">Current score: {user!.userscore.toString()}</h1>
          <div className="flex justify-center">
            {fullQuestion!.questionAnswered ?
              <button className="rounded border-2 p-2 m-5 bg-white" onClick={() => nextQuestionClick()}>
                Next Question
              </button>
              :
              <div />
            }
          </div>
          {alert && <Alert text={alert} onClick={() => setAlert(null)} />}
          <div className="flex justify-center">
            <div className="flex justify-center w-[500px]">
              <img src={questionImageUrl} className={classNames("m-5", imageSize)} />
            </div>
          </div>
          {fullQuestion!.questionAnswered ?
            <>
              <h1 className="text-2xl font-semibold text-center text-white my-4">{fullQuestion!.question.correctAnswer}</h1>
              <span className="text-white flex text-justify m-6 mt-0">
                {fullQuestion!.question.plaqueDescription}
              </span>
            </>
            :
            <>
              <h1 className="text-2xl font-semibold text-center text-white my-4">Identify the painting and painter</h1>
              <h1 className="text-xl font-semibold text-center text-white my-4">You can still earn {fullQuestion!.question.pointsAvailable} points</h1>
              <div className={classNames("flex justify-center")}>
                <button
                  className={classNames(buttonLayout, { "hover:brightness-125": !fullQuestion!.questionAnswered })}
                  onClick={() => revealAnswers(0)}
                  disabled={fullQuestion!.questionAnswered}
                >
                  {fullQuestion!.question.answers[0].longTitle}
                </button>
                <button
                  className={classNames(buttonLayout)}
                  onClick={() => revealAnswers(1)}
                  disabled={fullQuestion!.questionAnswered}
                >
                  {fullQuestion!.question.answers[1].longTitle}
                </button>
                <button
                  className={classNames(buttonLayout)}
                  onClick={() => revealAnswers(2)}
                  disabled={fullQuestion!.questionAnswered}
                >
                  {fullQuestion!.question.answers[2].longTitle}
                </button>
                <button
                  className={classNames(buttonLayout)}
                  onClick={() => revealAnswers(3)}
                  disabled={fullQuestion!.questionAnswered}
                >
                  {fullQuestion!.question.answers[3].longTitle}
                </button>
              </div>
              <div className={classNames("flex justify-center")}>
                <button
                  className={classNames(buttonLayout, "disabled:bg-slate-300 disabled:text-gray-500 disabled:hover:brightness-100")}
                  onClick={() => getHint()}
                  disabled={fullQuestion!.questionAnswered || fullQuestion!.question.hintsUsed >= 4}
                >
                  Get Hint
                </button>
              </div>
            </>
          }
        </div>
      </div>
    </>
  )
}
