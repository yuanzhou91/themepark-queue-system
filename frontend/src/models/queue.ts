import type { Effect, Reducer } from 'umi';
import { getCurrentQueue, joinNewQueue, quitQueue } from '@/services/themeparks/queue';

const Model: ModelType = {
  namespace: 'queue',

  state: {
    queue: [],
    isLoading: false,
  },

  effects: {
    *getCurrentQueue({payload}, { call, put }) {
      console.log("request payload is ", payload);
      const response = yield call(getCurrentQueue, payload);
      yield put({
        type: 'saveQueue',
        payload: [response],
      });
    },
    *joinNewQueue({payload}, { call, put }) {
      console.log("request payload is ", payload);
      const response = yield call(joinNewQueue, payload);
      yield put({
        type: 'saveQueue',
        payload: [response],
      });
    },
    *quitQueue({payload}, { call, put }) {
      console.log("request payload is ", payload);
      const response = yield call(quitQueue, payload);
      yield put({
        type: 'saveQueue',
        payload: null,
      });
    },
  },

  reducers: {
    saveQueue(state, action) {
      console.log("data is ", action);
      return {
        ...state,
        queue: (action.payload == null || action.payload[0] == "") ? [] : action.payload,
        loading: false,
      };
    },
  },
};

export default Model;
