// @ts-ignore
import { Request, Response } from 'express';

export default {
  'GET /attractions': (req: Request, res: Response) => {
    res.status(200).send([
      {
        id: 1,
        name: "Roller Coaster",
        location: "East park"
      },
      {
        id: 2,
        name: "Water Park",
        location: "West park"
      }
    ]);
  },
};
