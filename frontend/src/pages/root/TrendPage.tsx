import React, { useState } from 'react';
import Modal from '../../components/modal/Modal';

const TrendPage = () => {
    const [isModalOpen, setIsModalOpen] = useState<boolean>(true)

    const handleModalClose = (): void => {
        setIsModalOpen(false)
    }
    
    return (
        <>
            <Modal
                open={isModalOpen}
                onClose={handleModalClose}
            >
                {/* 달력 컴포넌트 제작 예정 */}
                <p>hello</p>
                <button onClick={handleModalClose}>close</button>
            </Modal>
            <div style={{ height: '2000px' }}>

            </div>
        </>
    );
};

export default TrendPage;