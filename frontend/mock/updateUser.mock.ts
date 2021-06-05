// @ts-ignore
import { Request, Response } from 'express';

export default {
  'PUT /user': (req: Request, res: Response) => {
    res
      .status(200)
      .send({
        id: 65,
        firstName: 'Lopez',
        lastName: 'Garcia',
        email: 'h.jkfhqq@pmcrrhuv.mc',
        password: 'string(16)',
        phone: '11441455913',
      });
  },
};
