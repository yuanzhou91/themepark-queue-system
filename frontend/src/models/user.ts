import type { Effect, Reducer } from 'umi';
import type { CurrentUser } from '@/services/themeparks/typings.d';
import { getUserByName as queryCurrent } from '@/services/themeparks/user';

export type ModalState = {
  currentUser?: Partial<CurrentUser>;
  isLoading?: boolean;
};

export type ModelType = {
  namespace: string;
  state: ModalState;
  effects: {
    fetchCurrent: Effect;
    fetch: Effect;
    fetchProvince: Effect;
    fetchCity: Effect;
  };
  reducers: {
    saveCurrentUser: Reducer<ModalState>;
    changeLoading: Reducer<ModalState>;
  };
};

const Model: ModelType = {
  namespace: 'accountSettings',

  state: {
    currentUser: {},
    isLoading: false,
  },

  effects: {
    *fetchCurrent({payload}, { call, put }) {
      if (payload.userId == null) {
        payload = {userId: localStorage.getItem('userId')}
      }
      const response = yield call(queryCurrent, payload);
      yield put({
        type: 'saveCurrentUser',
        payload: response,
      });
    },
  },

  reducers: {
    saveCurrentUser(state, action) {
      return {
        ...state,
        currentUser: action.payload || {},
      };
    },
    changeLoading(state, action) {
      return {
        ...state,
        isLoading: action.payload,
      };
    },
  },
};

export default Model;
