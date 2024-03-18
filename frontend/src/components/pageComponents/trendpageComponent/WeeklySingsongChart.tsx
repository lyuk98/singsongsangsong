import React, { useState } from 'react';
import { FaArrowLeft, FaArrowRight } from "react-icons/fa";

import img from './../../../sources/testimg/cover.png'
import styles from './WeeklySingsongChart.module.css'

const DUMMY_DATA = [
    {
        title: '1',
        author: 'heun',
        tag: [1, 2, 3, 4],
        emotion: [1, 2, 3, 4, 5],
        coverImg: img
    },
    {
        title: '2',
        author: 'oh',
        tag: [1, 2, 3, 4],
        emotion: [1, 2, 3, 4, 5],
    },
    {
        title: '3',
        author: 'lee',
        tag: [1, 2, 3, 4],
        emotion: [1, 2, 3, 4, 5],
    },
]


const WeeklySingsongChart = () => {
    const [currentIndex, setCurrentIndex] = useState(0)

    const handleNextIndex = () => {
        setCurrentIndex((prev) => (prev + 1) % 3)
    }

    const handlePrevIndex = () => {
        setCurrentIndex((prev) => (prev + 2) % 3)
    }

    return (
        <div className={`flex-col-center ${styles.container}`}>
            <div className={styles.headerContainer}>
                <h2>금주의 싱송차트</h2>
            </div>
            <div className={styles.chartContainer}>
                {
                    DUMMY_DATA.map((element, index) => {
                        return (
                            index === currentIndex && <>
                                <div className={styles.leftArrow}>
                                    <span onClick={handlePrevIndex}>
                                        <FaArrowLeft />
                                    </span>
                                </div>
                                <div className={styles.leftBox}>
                                    <div className={styles.leftTopBox}>
                                        <div className={styles.albumCover}>
                                            <img src={img} alt="" />
                                        </div>
                                        <div className={`flex-col-center ${styles.musicBox}`}>
                                            <h2>{element.title}</h2>
                                            <h2>{element.author}</h2>
                                        </div>
                                        <div className={styles.topIndicator}>
                                            {/* 이곳에 다운로드 수 좋아요 수 표시 예정 */}
                                        </div>
                                    </div>
                                </div>
                                <div className={styles.rightBox}>
                                    오른쪽박스
                                </div>
                                <div className={styles.rightArrow}>
                                    <span onClick={handleNextIndex}>
                                        <FaArrowRight />
                                    </span>
                                </div>
                            </>
                        )
                    })
                }

            </div>
        </div>
    );
};

export default WeeklySingsongChart;