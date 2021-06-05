// @ts-ignore
import { Request, Response } from 'express';

export default {
  'POST /reservations/confirm': (req: Request, res: Response) => {
    res.status(200).send({});
  },
};
