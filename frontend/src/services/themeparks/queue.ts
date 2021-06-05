// @ts-ignore
/* eslint-disable */
import { request } from 'umi';

/** 此处后端没有提供注释 GET /queue */
export async function getCurrentQueue(
  params: {
    // query
    userId?: string;
  },
  options?: { [key: string]: any },
) {
  return request<API.Queue>('/api/queue', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** join a queue POST /queue/join */
export async function joinNewQueue(body: API.JoinQueueRequest, options?: { [key: string]: any }) {
  return request<API.Queue>('/api/queue/join', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** quit from a queue DELETE /queue/quit */
export async function quitQueue(
  params: {
    // query
    /** user ID who wants to quit his/her queue. */
    user?: string;
  },
  options?: { [key: string]: any },
) {
  return request<API.Queue>('/api/queue/quit', {
    method: 'DELETE',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}


/** 此处后端没有提供注释 GET /queue */
export async function getCurrentQueueSize(
  params: {
    // query
    attractionId?: string;
  },
  options?: { [key: string]: any },
) {
  return request<API.Queue>('/api/queue/count', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
