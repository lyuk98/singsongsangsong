import { Url } from "url";

export interface DateType {
  year: number;
  month: number;
  day: number;
}

type TempCommentType = {
  artist: string;
  comment: string;
};

export interface commentType {
  comments: TempCommentType[];
}

export type AnalyzedStateType = {
  title: string;
  process: string;
};

type GenreType = {
  type: string;
  correlation: number;
};

type AtmosphereType = {
  type: string;
  correlation: number;
};

type SimilaritiesType = {
  songId: number;
  startTime: number;
  endTime: number;
  similarityPercent: number;
  songFile: Url;
};

export type AnalyzedResultType = {
  songTitle: string;
  songFile: Url;
  songMfcc: Url;
  songSpectrum: Url;
  genre: GenreType[];
  atmosphere: AtmosphereType[];
  similarities: SimilaritiesType[];
};
