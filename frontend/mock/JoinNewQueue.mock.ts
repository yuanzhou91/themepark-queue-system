// @ts-ignore
import { Request, Response } from 'express';

export default {
  'POST /queue/join': (req: Request, res: Response) => {
    res.status(200).send([]);
  },
};
