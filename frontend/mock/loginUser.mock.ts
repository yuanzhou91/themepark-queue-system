// @ts-ignore
import { Request, Response } from 'express';

export default {
  'GET /user/login': (req: Request, res: Response) => {
    res
      .status(200)
      .send({
        id: 85,
        firstName: 'Thomas',
        lastName: 'Harris',
        email: 'f.ebfeqz@ngcngvt.中国互联.网络',
        password: 'string(16)',
        phone: '11193438585',
      });
  },
};
