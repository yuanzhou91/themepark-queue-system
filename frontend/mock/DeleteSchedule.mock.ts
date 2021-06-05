// @ts-ignore
import { Request, Response } from 'express';

export default {
  'DELETE /schedules': (req: Request, res: Response) => {
    res.status(200).send({});
  },
};
