// @ts-ignore
/* eslint-disable */
import { request } from 'umi';

/** log out of the system POST /account/logout */
export async function logout(options?: { [key: string]: any }) {
  return request<Record<string, any>>('/api/account/logout', {
    method: 'POST',
    ...(options || {}),
  });
}

/** log into the system POST /account/login */
export async function login(body: API.LoginParams, options?: { [key: string]: any }) {
  return request<API.LoginResult>('/api/account/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
