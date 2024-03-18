import React, { useState } from 'react';
import Calendar from 'react-calendar';
import dayjs from 'dayjs';
import { styled } from 'styled-components'

import "react-calendar/dist/Calendar.css"
import { DateType } from '../../../utils/types';

const StyledCalendarWrapper = styled.div`
    width: 100%;
    display: flex;
    justify-content: center;
    position: relative;
    .react-calendar {
        width: 80%;
        border: none;
        border-radius: 0.5rem;
        box-shadow: 4px 2px 10px 0px rgba(0, 0, 0, 0.13);
        padding: 3% 5%;
        background-color: white;
    }
    .react-calendar,
    .react-calendar *,
    .react-calendar *:before,
    .react-calendar *:after {
      -moz-box-sizing: border-box;
      -webkit-box-sizing: border-box;
      box-sizing: border-box;
    }
    .react-calendar__navigation {
        justify-content: center;
    }
    .react-calendar__month-view__weekdays abbr {
        text-decoration: none;
    }
    .react-calendar__navigation__label {
        flex-grow: 0 !important;
    }
    .react-calendar__navigation button:focus  {
        background-color: white;
        color: ${(props) => props.theme.darkBlack};
    }
    .react-calendar__navigation button:disabled {
        background-color: white;
        color: ${(props) => props.theme.darkBlack};
    }
    .react-calendar__tile {
        border-radius: 5px;
        position: relative;
        text-align: center;
    }
    .react-calendar__tile--now {
        background: #C5E7FF;
        border-radius: 5px;
        color: #71C3FF;
    }
    .react-calendar__tile--now:hover {
        background: #C5E7FF;
        border-radius: 5px;
        color: #71C3FF;
    }
    .react-calendar__tile--active {
        background: #C5E7FF;
        border-radius: 5px;
        color: black;
        font-weight: bolder;
    }
`

const StyledCalendar = styled(Calendar)``
interface CalendarType {
    selectedDate: DateType
    handleDateChange: (newDate: any) => void
}

const ModalCalendar = ({ selectedDate, handleDateChange }: CalendarType) => {
    const date = new Date(`${selectedDate.year}-${selectedDate.month}-${selectedDate.day}`)
    return (
        <StyledCalendarWrapper>
            <StyledCalendar
                value={date}
                onChange={handleDateChange}
                formatDay={(locale, date) => dayjs(date).format('DD')}
                showNeighboringMonth={false}
                maxDate={new Date()}
                next2Label={null}
                prev2Label={null}
            />
        </StyledCalendarWrapper>
    );
};

export default ModalCalendar;