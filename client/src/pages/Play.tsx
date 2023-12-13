import { useEffect, useState } from 'react'
import { getQuestionFullImage, updateUserScore, getUserScore, getFullQuestion } from '../services/api'
import { useFullQuestion } from '../contexts/FullQuestionContext';
import { Alert } from '../components/Alert';
import { FullQuestion, QuestionType, isFullQuestion, isUser } from '../types';
import classNames from 'classnames';
import { useUser } from '../contexts/UserContext';
import { Slider } from 'rsuite';
import 'rsuite/dist/rsuite-no-reset.min.css';

export const Play = () => {
  const { fullQuestion, setFullQuestion } = useFullQuestion();
  const { user, setUser } = useUser();
  const [imageSize, setImageSize] = useState("w-[40%]");
  const [questionImageUrl, setQuestionImageUrl] = useState(URL.createObjectURL(fullQuestion!.image));
  const [alert, setAlert] = useState<string | null>(null);
  const [answerColour, setAnswerColour] = useState("bg-black/60");
  const [selectedYear, setSelectedYear] = useState(1500);
  const [nextQuestion, setNextQuestion] = useState<FullQuestion | undefined>(undefined);

  const buttonLayout = "rounded border-2 p-2 m-5 bg-slate-200 hover:brightness-125 disabled:bg-slate-300 disabled:text-gray-500 disabled:hover:brightness-100";

  const nextQuestionClick = async () => {
    if (nextQuestion) {
      setFullQuestion(nextQuestion);
    } else {
      const result = await getFullQuestion();
      if (isFullQuestion(result)) {
        setFullQuestion(result);
      } else {
        await nextQuestionClick();
      }
    }
    setAnswerColour("bg-black/60");
    setImageSize("w-[40%]");
  }

  const addQuestionToBacklog = async () => {
    const result = await getFullQuestion();
    if (isFullQuestion(result)) {
      setNextQuestion(result);
    } else {
      await addQuestionToBacklog();
    }
  }

  useEffect(() => {
    setQuestionImageUrl(URL.createObjectURL(fullQuestion!.image));
    addQuestionToBacklog();
  }, [fullQuestion]);


  const getQuestionFullImageFromServer = async () => {
    const imageResult = await getQuestionFullImage(fullQuestion!.questionAnswerData.correctObjectCode);
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

  const handleQuestionAnswered = async () => {
    await getQuestionFullImageFromServer();
    fullQuestion!.questionType = QuestionType.Answered;
  }

  const checkMultipleChoiceAnswer = (index: number) => {
    if (fullQuestion!.questionAnswerData.answers[index].correctAnswer) {
      setAnswerColour("bg-green-700/60");
      updateUserScoreInDatabase(fullQuestion!.pointsAvailable);
    } else {
      setAnswerColour("bg-red-600/60");
    }
    handleQuestionAnswered();
  }

  const checkTimelineAnswer = (timelineAnswer: number) => {
    const proximityToCorrectYear = Math.abs(fullQuestion!.questionAnswerData.artworkDate - timelineAnswer);
    const pointsAwarded = Math.max(0, Math.ceil(10 - (proximityToCorrectYear / 3)));
    if (pointsAwarded > 0) {
      setAnswerColour("bg-green-700/60");
      updateUserScoreInDatabase(pointsAwarded);
    } else {
      setAnswerColour("bg-red-600/60");
    }
    handleQuestionAnswered();
  }

  const getHint = () => {
    switch (fullQuestion!.questionType) {
      case QuestionType.ImageFragment:
        getHintForImageFragment();
        break;
      case QuestionType.ColourScheme:
        getHintForColourScheme();
        break;
      default:
        break;
    }
  }

  const getHintForImageFragment = () => {
    switch (fullQuestion!.hintsUsed) {
      case 0:
        setImageSize("w-[55%]");
        break;
      case 1:
        setImageSize("w-[70%]");
        break;
      case 2:
        setImageSize("w-[85%]");
        break;
      case 3:
        setImageSize("w-[100%]");
        break;
      default:
        break;
    }
    fullQuestion!.hintsUsed ++;
    fullQuestion!.pointsAvailable --;
  }

  const getHintForColourScheme = () => {
    fullQuestion!.hintsUsed += 2;
    fullQuestion!.pointsAvailable -= 3;
    disableWrongAnswer();
    setQuestionImageUrl(URL.createObjectURL(fullQuestion!.image));
  }

  const disableWrongAnswer = () => {
    const randomAnswerIndex = Math.floor(Math.random() * 3);
    if (!document.getElementById('answerbutton' + randomAnswerIndex)?.getAttribute('disabled') && !fullQuestion!.questionAnswerData.answers[randomAnswerIndex].correctAnswer) {
      document.getElementById('answerbutton' + randomAnswerIndex)?.setAttribute('disabled', 'true');
    } else {
      disableWrongAnswer();
    }
  }

  const questionComponents = () => {
    switch (fullQuestion!.questionType) {
      case QuestionType.ImageFragment:
        return (
          <>
            <div className="flex justify-center">
              <div className="flex justify-center w-[500px]">
                <img src={questionImageUrl} className={classNames("m-5 object-none", imageSize)} />
              </div>
            </div>
            <h1 className="text-2xl font-semibold text-center text-white my-4">Identify the painting in the above fragment</h1>
            {multipleChoiceComponents()}
          </>
        )
      case QuestionType.ColourScheme:
        return (
          <>
            <div className="flex justify-center">
              <div className="flex justify-center w-[500px]">
                <img src={questionImageUrl} className={classNames("m-5 object-fill w-[500px]")} />
              </div>
            </div>
            <h1 className="text-2xl font-semibold text-center text-white my-4">Identify the painting that the above colour scheme belongs to</h1>
            {multipleChoiceComponents()}
          </>
        )
      case QuestionType.Timeline:
        return (
          <>
            <div className="flex justify-center">
              <div className="flex justify-center w-[500px]">
                <img src={questionImageUrl} className={classNames("m-5 object-fill w-[500px]")} />
              </div>
            </div>
            <h1 className="text-2xl font-semibold text-center text-white my-4">Earn up to {fullQuestion!.pointsAvailable} points by identifying when this
              painting was produced (at the earliest). Lose 1 point for every 3 years you are off by.</h1>
            <div>
              <div className='flex justify-center mt-5'>
                <label className='text-white font-semibold ml-7'>
                  1000
                </label>
                <>
                  <Slider
                    className='w-full m-3'
                    progress
                    min={1000}
                    max={2023}
                    defaultValue={1500}
                    onChange={value => {
                      setSelectedYear(value);
                    }}
                  />
                </>
                <label className='text-white font-semibold mr-7'>
                  2023
                </label>
              </div>
              <div className='flex justify-center'>
                <button
                  className={buttonLayout}
                  onClick={() => checkTimelineAnswer(selectedYear)}
                >
                  Submit answer
                </button>
              </div>
            </div>
          </>
        )
      case QuestionType.Answered:
        return (
          <>
            <div className="flex justify-center">
              <button className="rounded border-2 p-2 m-5 bg-white" onClick={() => nextQuestionClick()}>
                Next Question
              </button>
            </div>
            <div className="flex justify-center">
              <div className="flex justify-center w-[500px]">
                <img src={questionImageUrl} className={classNames("m-5 object-fill w-[500px]")} />
              </div>
            </div>
            <div>
              <h1 className="text-2xl font-semibold text-center text-white my-4">{fullQuestion!.questionAnswerData.correctAnswer}</h1>
              <span className="text-white flex text-justify m-6 mt-0">
                {fullQuestion!.questionAnswerData.plaqueDescription}
              </span>
            </div>
          </>
        )
    }
  }

  const multipleChoiceComponents = () => {
    return (
      <div>
        <h1 className="text-xl font-semibold text-center text-white my-4">You can still earn {fullQuestion!.pointsAvailable} points</h1>
        <div className={classNames("flex justify-center")}>
          <button
            id='answerbutton0'
            className={classNames(buttonLayout)}
            onClick={() => checkMultipleChoiceAnswer(0)}
          >
            {fullQuestion!.questionAnswerData.answers[0].longTitle}
          </button>
          <button
            id='answerbutton1'
            className={classNames(buttonLayout)}
            onClick={() => checkMultipleChoiceAnswer(1)}
          >
            {fullQuestion!.questionAnswerData.answers[1].longTitle}
          </button>
          <button
            id='answerbutton2'
            className={classNames(buttonLayout)}
            onClick={() => checkMultipleChoiceAnswer(2)}
          >
            {fullQuestion!.questionAnswerData.answers[2].longTitle}
          </button>
          <button
            id='answerbutton3'
            className={classNames(buttonLayout)}
            onClick={() => checkMultipleChoiceAnswer(3)}
          >
            {fullQuestion!.questionAnswerData.answers[3].longTitle}
          </button>
        </div>
        <div className={classNames("flex justify-center")}>
          <button
            className={classNames(buttonLayout)}
            onClick={() => getHint()}
            disabled={fullQuestion!.hintsUsed >= 4}
          >
            Get Hint
          </button>
        </div>
      </div>
    )
  }

  //Actual page contents are rendered here
  return (
    <>
      <div className="`relative h-full w-screen bg-cover bg-center bg-no-repeat p-12 bg-rijksmuseum">
        <div className={classNames("absolute2 bottom-0 left-0 right-0 top-0 h-full w-full overflow-hidden bg-fixed bg-black/60", answerColour)}>
          <h1 className="text-5xl font-semibold text-center m-5 text-white">Discover the Rijksmuseum Collection</h1>
          <h1 className="text-l font-semibold m-2 ml-5 text-white">Current player: {user!.username}</h1>
          <h1 className="text-l font-semibold m-2 ml-5 text-white">Current score: {user!.userscore.toString()}</h1>
          {alert && <Alert text={alert} onClick={() => setAlert(null)} />}
          {questionComponents()}
        </div>
      </div>
    </>
  )
}
