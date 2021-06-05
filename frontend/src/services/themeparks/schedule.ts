// @ts-ignore
/* eslint-disable */
import { request } from 'umi';

/** 此处后端没有提供注释 GET /api/schedules */
export async function getSchedules(
  params: {
    // query
    attractionId?: string;
  },
  options?: { [key: string]: any },
) {
  return request<API.Schedules>('/api/schedules', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** update a schedule PUT /api/schedules */
export async function updateSchedule(body: API.Schedule, options?: { [key: string]: any }) {
  return request<API.Schedules>('/api/schedules', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** create a new schedule POST /api/schedules */
export async function createSchedule(body: API.Schedule, options?: { [key: string]: any }) {
  return request<API.Schedules>('/api/schedules', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** delete a schedule DELETE /api/schedules */
export async function deleteSchedule(
  params: {
    // query
    scheduleId?: string;
  },
  options?: { [key: string]: any },
) {
  return request<API.Schedules>('/api/schedules', {
    method: 'DELETE',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 GET /api/schedules/upcomingSchedule */
export async function getUpcomingSchedule(
  params: {
    // query
    attractionId?: string;
  },
  options?: { [key: string]: any },
) {
  return request<API.UpcomingSchedule>('/api/schedules/upcomingSchedule', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** 此处后端没有提供注释 PUT /api/schedules/upcomingSchedule */
export async function updateUpcomingSchedule(
  body: API.UpcomingSchedule,
  options?: { [key: string]: any },
) {
  return request<API.UpcomingSchedule>('/api/schedules/upcomingSchedule', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}
