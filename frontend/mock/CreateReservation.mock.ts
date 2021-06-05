// @ts-ignore
import { Request, Response } from 'express';

export default {
  'POST /reservations': (req: Request, res: Response) => {
    res.status(200).send([
      {
        id: 1,
        attraction: 1,
        user: 123,
        schedule: 20,
        startTime: "12:00pm",
        endTime: "12:50pm",
        status: 'upcoming'
      },
    ]);
  },
};
