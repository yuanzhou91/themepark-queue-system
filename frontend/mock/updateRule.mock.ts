// @ts-ignore
import { Request, Response } from 'express';

export default {
  'PUT /api/rule': (req: Request, res: Response) => {
    res
      .status(200)
      .send({
        key: 76,
        disabled: false,
        href: 'https://preview.pro.ant.design/dashboard/analysis',
        avatar: 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png',
        name: '曹勇',
        owner: 'Gonzalez',
        desc: '温决二共强期用形料养飞代它。',
        callNo: 72,
        status: 67,
        updatedAt: 'JgTiqS',
        createdAt: 'ncg@dud',
        progress: 65,
      });
  },
};
