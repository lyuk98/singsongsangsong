import { DateType } from "./types";

export const getToday = () => {
  const today = new Date();
  const year: number = today.getFullYear();
  const month: number = today.getMonth() + 1;
  const day: number = today.getDate();
  const date = {
    year,
    month,
    day,
  };
  return date;
};

export const getWeekNumber = (date: DateType) => {
  const inputDate = new Date(`${date.year}-${date.month}-${date.day}`);
  const currentDate = inputDate.getDate();
  const firstDay = new Date(inputDate.setDate(1)).getDay();

  return Math.ceil((currentDate + firstDay) / 7);
};
