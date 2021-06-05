// @ts-ignore
import { Request, Response } from 'express';

export default {
  'GET /api/rule': (req: Request, res: Response) => {
    res.status(200).send({
      data: [
        {
          key: 86,
          disabled: false,
          href: 'https://umijs.org/',
          avatar: '',
          name: '龙杰',
          owner: 'Hall',
          desc: '内段清明四看形始色影将东美相华口究定。',
          callNo: 92,
          status: 76,
          updatedAt: 'IkWs1a',
          createdAt: 'P7u0',
          progress: 80,
        },
        {
          key: 89,
          disabled: true,
          href: 'https://umijs.org/',
          avatar: 'https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png',
          name: '邓杰',
          owner: 'White',
          desc: '照议人术争酸式六图代品其其单十价八。',
          callNo: 64,
          status: 79,
          updatedAt: 'wq[a',
          createdAt: '(KRH&m',
          progress: 66,
        },
        {
          key: 93,
          disabled: false,
          href: 'https://umijs.org/',
          avatar: 'https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png',
          name: '高秀兰',
          owner: 'White',
          desc: '识选队叫于验族风教影面联技。',
          callNo: 92,
          status: 60,
          updatedAt: ')Bpw',
          createdAt: 'QXuD',
          progress: 63,
        },
      ],
      total: 75,
      success: false,
    });
  },
};
