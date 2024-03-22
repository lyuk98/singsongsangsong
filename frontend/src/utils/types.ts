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
