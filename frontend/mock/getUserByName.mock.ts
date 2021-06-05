// @ts-ignore
import { Request, Response } from 'express';

export default {
  'GET /user': (req: Request, res: Response) => {
    res
      .status(200)
      .send({
        id: 84,
        firstName: 'Miller',
        lastName: 'Anderson',
        email: 'r.jgleq@njdiq.wf',
        password: 'string(16)',
        phone: '11219719343',
        address: '澳门特别行政区 离岛 -',
        notifyCount: 64,
        unreadCount: 69,
        country: '美国',
        access: 'admin',
        geographic: { province: { label: '湖北省', key: 28 }, city: { label: '合肥市', key: 29 } },
      });
  },
};
