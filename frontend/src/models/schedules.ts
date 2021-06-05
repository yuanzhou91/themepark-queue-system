import type { Effect, Reducer } from 'umi';
import { getSchedules, deleteSchedule, updateSchedule, getUpcomingSchedule, updateUpcomingSchedule } from '@/services/themeparks/schedule';

const Model: ModelType = {
  namespace: 'schedules',

  state: {
    schedules: [],
    upcomingSchedule: {},
  },

  effects: {
    *getSchedules({payload}, { call, put }) {
      const response = yield call(getSchedules, payload);
      yield put({
        type: 'saveSchedules',
        payload: response,
      });
    },
    *getUpcomingSchedule({payload}, { call, put }) {
      const response = yield call(getUpcomingSchedule, payload);
      yield put({
        type: 'saveUpcomingSchedule',
        payload: response,
      });
    },
    *updateUpcomingSchedule({payload}, { call, put }) {
      const response = yield call(updateUpcomingSchedule, payload);
      yield put({
        type: 'saveUpcomingSchedule',
        payload: response,
      });
    },
    *updateSchedule({payload}, { call, put }) {
      const response = yield call(updateSchedule, payload);
      yield put({
        type: 'saveSchedules',
        payload: response,
      });
    },
    *deleteSchedule({payload}, { call, put }) {
      const response = yield call(deleteSchedule, payload);
      yield put({
        type: 'saveSchedules',
        payload: response,
      });
    },
  },
  reducers: {
    saveSchedules(state, action) {
      console.log("Schedules are ", action.payload);
      return {
        ...state,
        schedules: action.payload || [],
      };
    },
    saveUpcomingSchedule(state, action) {
      console.log("upcomingSchedule is ", action.payload);
      return {
        ...state,
        upcomingSchedule: action.payload || {},
      };
    },
  },
};

export default Model;
