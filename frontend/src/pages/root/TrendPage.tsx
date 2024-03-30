import React, { useEffect, useState } from "react";
import { json } from "react-router-dom";
import axios from "axios";

import styles from "./TrendPage.module.css";
import Modal from "../../components/modal/Modal";
import Header from "../../components/pageComponents/trendpageComponent/Header";
import Button from "../../components/buttons/Button";
import ModalCalendar from "../../components/pageComponents/trendpageComponent/ModalCalendar";
import {
  addZero,
  getLastSunday,
  getToday,
  getWeekNumber,
} from "./../../utils/dateUtils";
import { DateType } from "../../utils/types";
import { useAxios } from "../../hooks/api/useAxios";
// import WeeklySingsongChart from "../../components/pageComponents/trendpageComponent/WeeklySingsongChart";
import TrendWithOptions from "../../components/pageComponents/trendpageComponent/TrendWithOptions";
import RankWithOption from "../../components/pageComponents/trendpageComponent/RankWithOption";
import CompareWithAnotherSite from "../../components/pageComponents/trendpageComponent/CompareWithAnotherSite";
import SongWithEmotion from "../../components/pageComponents/trendpageComponent/SongWithEmotion";
import SongWithBPM from "../../components/pageComponents/trendpageComponent/SongWithBPM";
import TestWeeklySingsongChart from "../../components/pageComponents/trendpageComponent/testComponent/TestWeeklySingsongChart";
import noneImg from "./../../sources/imgs/nodataimg.webp";
import { getCookie } from "../../utils/cookie";

const TrendPage = () => {
  const { year, month, day } = getLastSunday();
  const [selectedDate, setSelectedDate] = useState<DateType>({
    year: year,
    month: month,
    day: day,
  });
  const [weekNumber, setWeekNumber] = useState<number>(1);
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

  const { response, isLoading, refetch } = useAxios({
    method: "GET",
    url: "trend/all",
    params: {
      date: `${selectedDate.year}-${selectedDate.month}-${selectedDate.day}`,
    },
  });

  // console.log(response);

  useEffect(() => {
    setWeekNumber(getWeekNumber(selectedDate));
    refetch();
    console.log("selected date", selectedDate);
  }, [selectedDate]);

  // useEffect(() => {
  //   const getdata = async () => {
  //     try {
  //       const response = await axios({
  //         method: "GET",
  //         url: `${process.env.REACT_APP_API_URL}trend/all`,
  //         params: {
  //           date: `${selectedDate.year}-${"03"}-${"17"}`,
  //         },
  //       });
  //       console.log(response.data.data.emotions);
  //     } catch (error) {
  //       console.log(error);
  //     }
  //   };
  //   getdata();
  // }, []);

  useEffect(() => {
    const reDirectToken = getCookie("accessToken");
    console.log(reDirectToken);
  }, []);

  const handleCalendarOpen = (): void => {
    setIsModalOpen(true);
  };

  const handleCalendarClose = (): void => {
    setIsModalOpen(false);
  };

  const handleDateChange = (newDate: any) => {
    setSelectedDate(() => {
      const newYear: number = newDate.getFullYear();
      let newMonth: string = addZero(newDate.getMonth() + 1);
      const newDay: number = newDate.getDate();
      return {
        year: newYear,
        month: newMonth,
        day: newDay,
      };
    });
    setIsModalOpen(false);
  };

  if (isLoading) {
    return <p>loading</p>;
  }

  return (
    <div style={{ paddingBottom: "6rem" }} className={`flex-col w-100 gap-15`}>
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
      {!response && (
        <div className={`w-100 flex-col-center ${styles.nonePage}`}>
          <img src={noneImg} alt="" />
          <h1>해당 주차에 대한 데이터가 존재하지 않습니다</h1>
        </div>
      )}
      {response && (
        <>
          <TestWeeklySingsongChart weekly={response.weekly} />
          <TrendWithOptions />
          <RankWithOption />
          <CompareWithAnotherSite
            korean={response.korean}
            world={response.world}
          />
          <SongWithEmotion emotions={response.emotions} />
          <SongWithBPM />
        </>
      )}
    </div>
  );
};

export default TrendPage;
