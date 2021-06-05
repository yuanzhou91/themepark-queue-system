import type { Effect, Reducer } from 'umi';
import { getReservations, deleteReservation, confirmReservation } from '@/services/themeparks/reservation';

const Model: ModelType = {
  namespace: 'reservations',

  state: {
    reservations: [],
    isLoading: false,
  },

  effects: {
    *getReservations({payload}, { call, put }) {
      console.log("request payload is ", payload);
      const response = yield call(getReservations, payload);
      yield put({
        type: 'saveReservations',
        payload: response,
      });
    },
    *cancelReservation({payload}, { call, put }) {
      console.log("request payload is ", payload);
      yield call(deleteReservation, payload);
      const response = yield call(getReservations, {userId: payload.userId});
      yield put({
        type: 'saveReservations',
        payload: response,
      });
    },
    *confirmReservation({payload}, { call, put }) {
      console.log("request payload is ", payload);
      const response = yield call(confirmReservation, payload);
      yield put({
        type: 'saveReservations',
        payload: response,
      });
    },
  },

  reducers: {
    saveReservations(state, action) {
      console.log("data is ", action.payload);
      return {
        ...state,
        reservations: action.payload || {},
        loading: false,
      };
    },
  },
};

export default Model;
