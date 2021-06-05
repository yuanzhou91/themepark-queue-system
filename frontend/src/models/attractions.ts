import type { Effect, Reducer } from 'umi';
import { getAttractions } from '@/services/themeparks/attraction';

const Model: ModelType = {
  namespace: 'attractions',

  state: {
    attractions: [],
  },

  effects: {
    *getAttractions({}, { call, put }) {
      const response = yield call(getAttractions);
      yield put({
        type: 'saveAttractions',
        payload: response,
      });
    },
  },
  reducers: {
    saveAttractions(state, action) {
      console.log("attractions are ", action.payload);
      return {
        ...state,
        attractions: action.payload || [],
      };
    },
  },
};

export default Model;
