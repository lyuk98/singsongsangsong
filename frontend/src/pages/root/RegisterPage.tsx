import React, { useState, ChangeEvent } from 'react';
import { Form, Link, ActionFunction, redirect } from 'react-router-dom';

import styles from './RegisterPage.module.css'

import AuthInput from '../../components/auth/AuthInput';
import Button from '../../components/buttons/Button';
import { useInput } from '../../hooks/useInput';
import {
    idValidator,
    passwordValidator,
} from '../../utils/validator';

const AGES = [
    { data: 10, text: '10대' },
    { data: 20, text: '20대' },
    { data: 30, text: '30대' },
    { data: 40, text: '40대' },
    { data: 50, text: '50대' },
    { data: 6, text: '50대 이상' },
]

const RegisterPage = () => {
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

    const {
        value: passwordConfirmValue,
        handleInputChange: handlePasswordConfirmChange,
        valueIsValid: passwordConfirmIsValid
    } = useInput('', (value) => value === passwordValue)

    const [gender, setGender] = useState<undefined | string>(undefined)
    const [age, setAge] = useState<undefined | number>(undefined)

    const handleGenderChange = (event: ChangeEvent<HTMLSelectElement>): void => {
        setGender(event.target.value)
    }

    const handleAgeChange = (event: ChangeEvent<HTMLSelectElement>): void => {
        setAge(+event.target.value)
    }

    return (
        <div className={styles.container}>
            <Form method='post'>
                <AuthInput
                    id='id'
                    label='input your id'
                    name='id'
                    hasError={!idIsValid && '아이디는 4-16글자 사이입니다'}
                    type='text'
                    placeholder='input your id'
                    value={idValue}
                    onChange={handleIdChange}
                    required
                />
                <AuthInput
                    id='password'
                    label='create password'
                    name='password'
                    hasError={!passwordIsValid && '비밀번호는 4-16글자 사이입니다'}
                    type='password'
                    placeholder='input your password'
                    value={passwordValue}
                    onChange={handlePasswordChange}
                    required
                />
                <AuthInput
                    id='passwordConfirm'
                    label='confirm password'
                    name='passwordConfirm'
                    hasError={!passwordConfirmIsValid && '비밀번호가 일치하지 않습니다'}
                    type='password'
                    placeholder='confirm your password'
                    value={passwordConfirmValue}
                    onChange={handlePasswordConfirmChange}
                    required
                />
                <div className={styles.selectBox}>
                    <label>Gender</label>
                    <select
                        name='gender'
                        value={gender}
                        onChange={handleGenderChange}
                        required
                    >
                        <option
                            key='none'
                            value={""}
                        >성별을 선택해주세요</option>
                        <option key='male' value="male">남성</option>
                        <option key='femail' value="female">여성</option>
                    </select>

                    <label>Age Range</label>
                    <select
                        name='age'
                        value={age}
                        onChange={handleAgeChange}
                        required
                    >
                        <option
                            value={""}
                        >연령대를 설정해주세요</option>
                        {
                            AGES.map((element) => {
                                return (
                                    <option key={element.data} value={element.data}>
                                        {element.text}
                                    </option>
                                )
                            })
                        }
                    </select>
                </div>
                <div className={styles.bottomBox}>
                    <Button>Register</Button>
                    <p>You hava account?<Link to='/login'>Login</Link> </p>
                </div>
            </Form>
        </div>
    )
}

export default RegisterPage;

export const action: ActionFunction = async ({ request, params }) => {
    console.log('submit')
    const data = await request.formData()
    const userData = {
        id: data.get('id'),
        password: data.get('password'),
        gender: data.get('gender'),
        age: data.get('age'),
    }
    console.log(userData)
    return redirect('/')

}