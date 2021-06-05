// @ts-ignore
/* eslint-disable */

declare namespace API {
  type Users = User[];

  type CurrentUser = {
    id?: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    password?: string;
    phone?: string;
    address?: string;
    notifyCount?: number;
    unreadCount?: number;
    country?: string;
    access?: string;
    geographic?: {
      province?: { label?: string; key?: string };
      city?: { label?: string; key?: string };
    };
  };

  type User = {
    id?: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    password?: string;
    phone?: string;
  };

  type Attractions = Attraction[];

  type Attraction = {
    id?: string;
    name?: string;
    location?: string;
  };

  type Reservations = Reservation[];

  type Reservation = {
    id?: string;
    attraction?: string;
    user?: string;
    schedule?: string;
    startTime?: string;
    endTime?: string;
    location?: string;
    status?: 'waiting' | 'passed' | 'cancelled' | 'confirmed' | 'checking-in' | 'completed';
  };

  type Queue = {
    id?: string;
    numberInQueue?: number;
    nextNumberToCall?: number;
    attraction?: string;
    user?: string;
    location?: string;
    eta?: string;
    status?: 'waiting' | 'passed' | 'called' | 'confirmed';
  };

  type JoinQueueRequest = {
    attraction?: string;
    users?: string[];
  };

  type ErrorResponse = {
    /** error code */
    errorCode: string;
    /** error message */
    errorMessage?: string;
    /** success or not */
    success?: boolean;
  };

  type Schedule = {
    id?: string;
    attraction?: string;
    totalSeats?: number;
    availableSeats?: number;
    reservedForQueue?: number;
    startTime?: string;
    endTime?: string;
    status?: 'upcoming' | 'checking-in' | 'post-checkin' | 'completed' | 'cancelled';
  };

  type Schedules = Schedule[];

  type UpcomingSchedule = {
    id?: string;
    attraction?: string;
    totalSeats?: number;
    availableSeats?: number;
    startTime?: string;
    endTime?: string;
    nextNumberToCall?: number;
    status?: 'upcoming' | 'checking-in' | 'post-checkin' | 'completed' | 'cancelled';
  };

  type LoginResult = {
    status?: string;
    type?: string;
    currentAuthority?: string;
  };

  type LoginParams = {
    username?: string;
    password?: string;
    autoLogin?: boolean;
    type?: string;
  };
}
