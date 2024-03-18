import React, { useEffect, useState } from 'react';

import styles from './TrendPage.module.css'
import Modal from '../../components/modal/Modal';
import Header from '../../components/pageComponents/trendpageComponent/Header';
import Button from '../../components/buttons/Button';
import ModalCalendar from '../../components/pageComponents/trendpageComponent/ModalCalendar';
import { getToday, getWeekNumber } from './../../utils/dateUtils'
import { DateType } from '../../utils/types';
import WeeklySingsongChart from '../../components/pageComponents/trendpageComponent/WeeklySingsongChart';


const TrendPage = () => {
    const { year, month, day } = getToday();
    const [selectedDate, setSelectedDate] = useState<DateType>({
        year: year,
        month: month,
        day: day,
    })
    const [weekNumber, setWeekNumber] = useState<number>(1)
    const [isModalOpen, setIsModalOpen] = useState<boolean>(false)

    useEffect(() => {
        setWeekNumber(getWeekNumber(selectedDate))
    }, [selectedDate])

    const handleCalendarOpen = (): void => {
        setIsModalOpen(true)
    }

    const handleCalendarClose = (): void => {
        setIsModalOpen(false)
    }

    const handleDateChange = (newDate: any) => {
        setSelectedDate(() => {
            const newYear: number = newDate.getFullYear()
            const newMonth: number = newDate.getMonth() + 1
            const newDay: number = newDate.getDate()
            return {
                year: newYear,
                month: newMonth,
                day: newDay
            }
        })
        setIsModalOpen(false)
    }

    return (
        <>
            <Modal
                open={isModalOpen}
                onClose={handleCalendarClose}
            >
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
        </>
    );
};

export default TrendPage;