import { Cookies } from "react-cookie";
import { Cookie, CookieSetOptions } from "universal-cookie";

const cookies = new Cookies();

export const setCookie = (
  name: string,
  value: Cookies,
  options?: CookieSetOptions
) => {
  return cookies.set(name, value, { ...options });
};

export const getCookie = (name: string) => {
  try {
    return cookies.get(name);
  } catch (error) {
    console.log(error);
  }
};

export const removeCookie = (name: string, options?: CookieSetOptions) => {
  try {
    cookies.remove(name, { ...options });
  } catch (error) {
    console.log(error);
  }
};
