// @ts-ignore
import { Request, Response } from 'express';

export default {
  'GET /api/currentUser': (req: Request, res: Response) => {
    res.status(200).send({
      name: '邹勇',
      avatar: 'https://gw.alipayobjects.com/zos/rmsportal/OKJXDXrmkNshAMvwtvhu.png',
      userid: '86CAd6cB-2ABf-bbc5-BAfe-f1a1Da2DbC49',
      email: 'b.baiej@fcrppzkx.bh',
      signature: '求南统带度王何几红路式压马人着老织消。',
      title: '来应数单候存常局须点量便小为认已林。',
      group: '前端 6 组',
      tags: [
        { key: 1, label: 'IT 互联网' },
        { key: 2, label: '算法工程师' },
        { key: 3, label: '专注设计' },
        { key: 4, label: '算法工程师' },
        { key: 5, label: '爱好广泛' },
        { key: 6, label: '名望程序员' },
        { key: 7, label: '程序员' },
        { key: 8, label: '专注设计' },
        { key: 9, label: '川妹子' },
        { key: 10, label: '爱好广泛' },
        { key: 11, label: '海纳百川' },
        { key: 12, label: '海纳百川' },
        { key: 13, label: 'IT 互联网' },
        { key: 14, label: '海纳百川' },
        { key: 15, label: '健身达人' },
        { key: 16, label: '阳光少年' },
        { key: 17, label: '川妹子' },
        { key: 18, label: '爱好广泛' },
      ],
      notifyCount: 74,
      unreadCount: 87,
      country: '中国',
      access: '例相些许书上容因命身共它报比。',
      geographic: { province: { label: '上海', key: 19 }, city: { label: '苏州市', key: 20 } },
      address: '西藏自治区 拉萨市 林周县',
      phone: '11236915257',
    });
  },
};
