// @ts-ignore
/* eslint-disable */
import { request } from 'umi';

/** 此处后端没有提供注释 GET /api/reservations */
export async function getReservations(
  params: {
    // query
    /** user account */
    userId?: string;
  },
  options?: { [key: string]: any },
) {
  return request<API.Reservations>('/api/reservations', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** create new reservations POST /api/reservations */
export async function createReservation(body: API.Reservations, options?: { [key: string]: any }) {
  return request<API.Reservations>('/api/reservations', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** delete reservation DELETE /api/reservations */
export async function deleteReservation(
  params: {
    // query
    /** id of reservation to delete */
    reservationId: string;
  },
  options?: { [key: string]: any },
) {
  console.log(params);
  return request<API.Reservations>('/api/reservations', {
    method: 'DELETE',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** confirm reservation POST /api/reservations/confirm */
export async function confirmReservation(
  params: {
    // query
    /** id of reservation to confirm */
    reservationId?: string;
  },
  options?: { [key: string]: any },
) {
  return request<API.Reservation>('/api/reservations/confirm', {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
