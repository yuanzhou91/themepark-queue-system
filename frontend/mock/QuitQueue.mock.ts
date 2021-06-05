// @ts-ignore
import { Request, Response } from 'express';

export default {
  'DELETE /queue/quit': (req: Request, res: Response) => {
    res.status(200).send([]);
  },
};
