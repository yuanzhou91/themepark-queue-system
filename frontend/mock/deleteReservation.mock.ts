// @ts-ignore
import { Request, Response } from 'express';

export default {
  'DELETE /reservations': (req: Request, res: Response) => {
    res.status(200).send({});
  },
};
