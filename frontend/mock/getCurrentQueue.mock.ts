// @ts-ignore
import { Request, Response } from 'express';

export default {
  'GET /queue': (req: Request, res: Response) => {
    res.status(200).send([
      {
        id: "1",
        attraction: 1,
        numberInQueue: 32,
        location: "West Park",
        nextNumberToCall: 25,
        eta: "12:15 pm",
        status: "waiting",
        user: 1,
      },
    ]);
  },
};
