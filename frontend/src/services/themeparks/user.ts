// @ts-ignore
/* eslint-disable */
import { request } from 'umi';

/** Get user by user name GET /api/user */
export async function getUserByName(
  params: {
    // query
    /** The name that needs to be fetched. Use user1 for testing.  */
    userId: string;
  },
  options?: { [key: string]: any },
) {
  if (params.userId == undefined) {
    console.log("User id is not found")
    params = {userId: localStorage.getItem('userId')}
  }
  return request<API.CurrentUser>('/api/user', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** Update user This can only be done by the logged in user. PUT /api/user */
export async function updateUser(
  params: {
    // query
    /** name that need to be updated */
    userId: string;
  },
  body: API.User,
  options?: { [key: string]: any },
) {
  return request<API.User>('/api/user', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    params: {
      ...params,
    },
    data: body,
    ...(options || {}),
  });
}

/** Update user This can only be done by the logged in user. PUT /api/user */
export async function createUser(
  body: API.User,
  options?: { [key: string]: any },
) {
  return request<API.User>('/api/user', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    data: body,
    ...(options || {}),
  });
}

/** Delete user This can only be done by the logged in user. DELETE /api/user */
export async function deleteUser(
  params: {
    // query
    /** The name that needs to be deleted */
    userId: string;
  },
  options?: { [key: string]: any },
) {
  return request<any>('/api/user', {
    method: 'DELETE',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** Get group of the current user GET /api/user/group */
export async function getUserGroup(
  params: {
    // query
    /** user account ID that owns the group */
    userId: string;
  },
  options?: { [key: string]: any },
) {
  if (params.userId == undefined) {
    console.log("User id is not found")
    params = {userId: localStorage.getItem('userId')}
  }
  return request<API.Users>('/api/user/group', {
    method: 'GET',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** AddUserToGroup This can only be done by the logged in user. PUT /api/user/group */
export async function addUserToGroup(
  params: {
    // query
    /** user account ID that owns the group */
    userId: string;
  },
  body: API.User,
  options?: { [key: string]: any },
) {
  return request<API.Users>('/api/user/group', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    params: {
      ...params,
    },
    data: body,
    ...(options || {}),
  });
}

/** Delete user This can only be done by the logged in user. DELETE /api/user/group */
export async function deletUserFromGroup(
  params: {
    // query
    /** user account ID that owns the group */
    userId: string;
    /** The user that needs to be deleted from the group */
    userToDelete: string;
  },
  options?: { [key: string]: any },
) {
  return request<any>('/api/user/group', {
    method: 'DELETE',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
