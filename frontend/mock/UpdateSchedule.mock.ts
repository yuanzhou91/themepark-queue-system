// @ts-ignore
import { Request, Response } from 'express';

export default {
  'PUT /schedules': (req: Request, res: Response) => {
    res.status(200).send([
      {
        id: 1,
        attraction: 1,
        totalSeats: 120,
        availableSeats: 20,
        startTime: "12:00pm",
        endTime: "12:50pm",
        status: 'checking-in'
      },
      {
        id: 2,
        attraction: 1,
        totalSeats: 120,
        availableSeats: 20,
        startTime: "1:00pm",
        endTime: "1:50pm",
        status: 'upcoming'
      },
    ]);
  },
};
