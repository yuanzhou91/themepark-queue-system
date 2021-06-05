// @ts-ignore
import { Request, Response } from 'express';

export default {
  'GET /api/notices': (req: Request, res: Response) => {
    res.status(200).send({
      data: [
        {
          id: '8352ae7b-b477-Ea9f-8fdd-59858BF6c97f',
          extra: 'H!G',
          key: 22,
          read: true,
          avatar: 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png',
          title: '平意白风得老林由手存据很打间。',
          status: 'error',
          datetime: '2018-04-29',
          description: '这具总给系热质地海写影几而料。',
          type: 'notification',
        },
        {
          id: 'FdBe6134-6B1A-F103-4F9b-1c68Cdc3bdCb',
          extra: 'p9dDb!Y',
          key: 23,
          read: false,
          avatar: 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png',
          title: '象两期准始而易电从车走由于快看。',
          status: 'processing',
          datetime: '1989-08-14',
          description: '斗专车气空这质全书非状与得离青。',
          type: 'notification',
        },
        {
          id: '19B1A236-5Cea-9F4E-5784-6f989A2247f7',
          extra: 'kr2lr$',
          key: 24,
          read: false,
          avatar: 'https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png',
          title: '一打场老第离看界国么管如于。',
          status: 'error',
          datetime: '1970-03-11',
          description: '它明空论向际运走小进铁解织。',
          type: 'notification',
        },
        {
          id: 'be06eb26-5cfD-a574-8A7A-88c053631d8b',
          extra: 'Lh*]',
          key: 25,
          read: true,
          avatar: 'https://gw.alipayobjects.com/zos/rmsportal/udxAbMEhpwthVVcjLXik.png',
          title: '到可了极边部后西写图且运过所林治。',
          status: 'default',
          datetime: '1984-02-03',
          description: '是热消已看指水见传响矿查。',
          type: 'notification',
        },
        {
          id: '348AFf73-7fD9-845f-99eA-B425BbFB63E6',
          extra: 'Wz%X@L',
          key: 26,
          read: false,
          avatar: 'https://gw.alipayobjects.com/zos/rmsportal/udxAbMEhpwthVVcjLXik.png',
          title: '区好信情价细小王众者工已造。',
          status: 'error',
          datetime: '1980-09-10',
          description: '程可少么等处张和始况标较带素中。',
          type: 'notification',
        },
      ],
      total: 96,
      success: true,
    });
  },
};
