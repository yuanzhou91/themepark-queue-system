import type { Effect, Reducer } from 'umi';
import { getUserGroup, addUserToGroup, deletUserFromGroup } from '@/services/themeparks/user';

const Model: ModelType = {
  namespace: 'group',

  state: {
    group: [],
  },

  effects: {
    *getUserGroup({payload}, { call, put }) {
      console.log("request payload for group is ", payload);
      const response = yield call(getUserGroup, payload);
      yield put({
        type: 'saveGroup',
        payload: response,
      });
    },
    *addUserToGroup({payload}, { call, put }) {
      console.log("request payload is ", payload);
      const response = yield call(addUserToGroup, payload);
      yield put({
        type: 'saveGroup',
        payload: response,
      });
    },
    *deletUserFromGroup({payload}, { call, put }) {
      console.log("request payload for deleting group is ", payload);
      yield call(deletUserFromGroup, payload);
      const response = yield call(getUserGroup, {userId: payload.userId});
      yield put({
        type: 'saveGroup',
        payload: response,
      });
    },
  },
  reducers: {
    saveGroup(state, action) {
      return {
        ...state,
        group: action.payload || [],
      };
    },
  },
};

export default Model;
