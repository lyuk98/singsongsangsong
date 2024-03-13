import React from 'react';
import { Form } from 'react-router-dom';
import styles from './AuthInput.module.css'

type AuthInputType = {
    id: string,
    label: string,
    [key: string]: any
}

const AuthInput: React.FC<AuthInputType> = ({ id, label, ...props }) => {
    return (
        <div className={styles.contaienr}>
            <label htmlFor={id}>{label}</label>
            <input
                id={id}
                {...props}
            />
        </div>
    );
};

export default AuthInput;