import { useState, ChangeEvent } from "react";

/**
 * 
 * @param {*} defaultValue 기본으로 주어지는 데이터 기본은 빈 문자열
 * @param {*} validationFn input value를 검사할 함수
 * @todo validationFn 만들어서 인자로 추가해 줘야함
 * @returns error상태와 value전달
 */
export function useInput(defaultValue: string) {
    const [enteredValue, setEnteredValue] = useState<string>(defaultValue)

    // const valueIsValid = validationFn(enteredValue)

    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        setEnteredValue(event.target.value)
    }

    return {
        value: enteredValue,
        handleInputChange,
    }
}