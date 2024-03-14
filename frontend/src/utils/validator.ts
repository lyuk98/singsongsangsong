export const idValidator = (id: string): boolean => {
    if (id.length < 4 || id.length > 16) {
        return false
    }
    return true
}

export const passwordValidator = (password: string): boolean => {
    if (password.length < 4 || password.length > 16) {
        return false
    }
    return true
}
