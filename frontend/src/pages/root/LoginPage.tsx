import React from 'react';
import { useInput } from '../../hooks/useInput';

import AuthInput from '../../components/auth/AuthInput';
import styles from './LoginPage.module.css'

const LoginPage = () => {
    // 나중에 validation 함수를 추가해서 error state를 표현
    const {
        value: idValue,
        handleInputChange: handleIdChange,
    } = useInput('')

    const {
        value: passwordValue,
        handleInputChange: handlePasswordChange,
    } = useInput('')

    return (
        <div className={styles.conatiner}>
            <AuthInput
                id='id'
                label='id'
                type='text'
                value={idValue}
                onChange={handleIdChange}
            />
            <AuthInput
                id='password'
                label='password'
                type='password'
                value={passwordValue}
                onChange={handlePasswordChange}
            />
        </div>
    );
};

export default LoginPage;