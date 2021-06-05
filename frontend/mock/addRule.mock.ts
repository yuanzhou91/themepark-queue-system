// @ts-ignore
import { Request, Response } from 'express';

export default {
  'POST /api/rule': (req: Request, res: Response) => {
    res
      .status(200)
      .send({
        key: 82,
        disabled: true,
        href: 'https://procomponents.ant.design/',
        avatar: 'https://avatars1.githubusercontent.com/u/8186664?s=40&v=4',
        name: '刘明',
        owner: 'Johnson',
        desc: '下张素我石六应院验规断段多五太。',
        callNo: 66,
        status: 81,
        updatedAt: 'xosTB',
        createdAt: 'UfF3Usy',
        progress: 72,
      });
  },
};
