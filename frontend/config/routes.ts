export default [
  {
    path: '/users',
    layout: false,
    routes: [
      {
        path: '/users',
        routes: [
          {
            name: 'login',
            path: '/users/accountLogin',
            component: './user/Login',
          },
        ],
      },
    ],
  },
  {
    path: '/welcome',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
  },
  {
    name: 'list.reservations',
    icon: 'Schedule',
    access: 'canUser',
    path: '/myreservations',
    component: './Reservations',
  },
  {
    name: 'list.user-queue',
    icon: 'OrderedList',
    access: 'canUser',
    path: '/myqueue',
    component: './QueueManagement',
  },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    name: 'Settings',
    icon: 'setting',
    access: 'canUser',
    path: '/myaccountsettings',
    component: './AccountSettings',
  },
  {
    name: 'Schedules',
    icon: 'Schedule',
    access: 'canAdmin',
    path: '/manageSchedules',
    component: './Schedules',
  },
  {
    component: './404',
  },
];
