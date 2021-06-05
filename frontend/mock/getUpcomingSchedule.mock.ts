// @ts-ignore
import { Request, Response } from 'express';

export default {
  'GET /schedules/upcomingSchedule': (req: Request, res: Response) => {
    res.status(200).send({
      id: 1,
      attraction: 1,
      totalSeats: 120,
      availableSeats: 20,
      startTime: "12:00pm",
      endTime: "12:50pm",
      status: 'post-checkin',
      nextNumberToCall: 20,
    });
  },
};
