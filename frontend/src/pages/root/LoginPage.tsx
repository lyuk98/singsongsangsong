import React from 'react';
import { Link } from 'react-router-dom';

import styles from './LoginPage.module.css'

import AuthInput from '../../components/auth/AuthInput';
import Button from '../../components/buttons/Button';
import { useInput } from '../../hooks/useInput';
import { idValidator, passwordValidator } from '../../utils/validator';

const LoginPage = () => {
    // 나중에 validation 함수를 추가해서 error state를 표현
    const {
        value: idValue,
        handleInputChange: handleIdChange,
        valueIsValid: idIsValid
    } = useInput('', idValidator)

    const {
        value: passwordValue,
        handleInputChange: handlePasswordChange,
        valueIsValid: passwordIsValid
    } = useInput('', passwordValidator)

    return (
        <>
            <div className={styles.conatiner}>
                <AuthInput
                    id='id'
                    label='id'
                    name='id'
                    type='text'
                    placeholder='id'
                    value={idValue}
                    onChange={handleIdChange}
                />
                <AuthInput
                    id='password'
                    label='password'
                    name='password'
                    type='password'
                    placeholder='password'
                    value={passwordValue}
                    onChange={handlePasswordChange}
                />
                <div className={styles.buttonBox}>
                    <Button>
                        Login
                    </Button>
                </div>
                <div className={styles.linkBox}>
                    <p>계정이없나요? <Link to="/register">sign up</Link></p>
                </div>
            </div>
        </>
    );
};

export default LoginPage;