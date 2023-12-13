import classNames from "classnames";
import { useEffect, useState } from "react";
import { ArtworkOfTheDayType, isArtworkOfTheDay } from "../types";
import { getArtworkOfTheDay } from "../services/api";
import loading from "./assets/loading.gif";

export const ArtworkOfTheDay = () => {

    const [artwork, setArtwork] = useState<ArtworkOfTheDayType | undefined>(undefined);
    const [imageUrl, setImageUrl] = useState<string>("");

    const updateArtworkOfTheDay = async () => {
        const result = await getArtworkOfTheDay();
        if (isArtworkOfTheDay(result)) {
            setArtwork(result);
            setImageUrl(URL.createObjectURL(result.image));
        }
    }

    useEffect(() => {
        updateArtworkOfTheDay();
    }, [])

    return <div className="flex h-full items-center justify-center bg-transparent">
        <div className="flex justify-center">
            <article className="prose h-min w-full justify-center text-center text-2xl">
                {artwork?.image !== undefined ?
                    <>
                        <h1 className="mt-10">{artwork?.artworkData.title} ({artwork?.artworkData.year}), by {artwork?.artworkData.artist}</h1>
                        <img src={imageUrl} className={classNames("m-5")} />
                        <p>
                            {artwork?.artworkData.artworkDescription}
                        </p>
                        <a
                            href={artwork?.artworkData.rijksmuseumUrl}
                        >
                            Learn more
                        </a>
                        <div className="mb-10"/>
                    </>
                    :
                    <>
                        <img src={loading} className="w-[100px] h-[100px]" />
                    </>
                }
            </article>
        </div>
    </div>
}
