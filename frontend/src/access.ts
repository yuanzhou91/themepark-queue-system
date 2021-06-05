/**
 * @see https://umijs.org/zh-CN/plugins/plugin-access
 * */
export default function access(initialState: { currentUser?: API.CurrentUser | undefined }) {
  const { currentUser } = initialState || {};
  console.log("current user for access is ", currentUser);
  return {
    canAdmin: currentUser && currentUser.access === 'admin',
    canUser: currentUser && currentUser.access === 'user',
  };
}
