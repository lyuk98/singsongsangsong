import React, { useEffect, useState } from "react";

import styles from "./TrendPage.module.css";
import Modal from "../../components/modal/Modal";
import Header from "../../components/pageComponents/trendpageComponent/Header";
import Button from "../../components/buttons/Button";
import ModalCalendar from "../../components/pageComponents/trendpageComponent/ModalCalendar";
import { getLastSunday, getToday, getWeekNumber } from "./../../utils/dateUtils";
import { DateType } from "../../utils/types";
import WeeklySingsongChart from "../../components/pageComponents/trendpageComponent/WeeklySingsongChart";
import TrendWithOptions from "../../components/pageComponents/trendpageComponent/TrendWithOptions";
import RankWithOption from "../../components/pageComponents/trendpageComponent/RankWithOption";
import CompareWithAnotherSite from "../../components/pageComponents/trendpageComponent/CompareWithAnotherSite";
import SongWithEmotion from "../../components/pageComponents/trendpageComponent/SongWithEmotion";
import SongWithBPM from "../../components/pageComponents/trendpageComponent/SongWithBPM";

const TrendPage = () => {
  const { year, month, day } = getLastSunday();
  const [selectedDate, setSelectedDate] = useState<DateType>({
    year: year,
    month: month,
    day: day,
  });
  const [weekNumber, setWeekNumber] = useState<number>(1);
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

  useEffect(() => {
    setWeekNumber(getWeekNumber(selectedDate));
  }, [selectedDate]);

  const handleCalendarOpen = (): void => {
    setIsModalOpen(true);
  };

  const handleCalendarClose = (): void => {
    setIsModalOpen(false);
  };

  const handleDateChange = (newDate: any) => {
    setSelectedDate(() => {
      const newYear: number = newDate.getFullYear();
      const newMonth: number = newDate.getMonth() + 1;
      const newDay: number = newDate.getDate();
      return {
        year: newYear,
        month: newMonth,
        day: newDay,
      };
    });
    setIsModalOpen(false);
  };

  return (
    <div className={`flex-col w-100 gap-15`}>
      <Modal open={isModalOpen} onClose={handleCalendarClose}>
        <div className={styles.calanderContent}>
          <ModalCalendar
            selectedDate={selectedDate}
            handleDateChange={handleDateChange}
          />
          <Button onClick={handleCalendarClose}>close</Button>
        </div>
      </Modal>
      <Header
        selectedDate={selectedDate}
        selectedWeek={weekNumber}
        onOpen={handleCalendarOpen}
      />
      <WeeklySingsongChart />
      <TrendWithOptions />
      <RankWithOption />
      <CompareWithAnotherSite />
      <SongWithEmotion />
      <SongWithBPM />
    </div>
  );
};

export default TrendPage;
