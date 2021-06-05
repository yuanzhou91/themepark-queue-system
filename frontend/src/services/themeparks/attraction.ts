// @ts-ignore
/* eslint-disable */
import { request } from 'umi';

/** 此处后端没有提供注释 GET /attractions */
export async function getAttractions(options?: { [key: string]: any }) {
  return request<API.Attractions>('/api/attractions', {
    method: 'GET',
    ...(options || {}),
  });
}
