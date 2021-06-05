import React from 'react';
import { PageContainer } from '@ant-design/pro-layout';
import { Card, Alert, Typography } from 'antd';
import { useIntl, FormattedMessage } from 'umi';
import styles from './Welcome.less';
import FormValidateStatic from './FormValidateStatic';
import ReservationsTable from './ReservationsTable';
import ReservationModal from './ReservationModal';

const CodePreview: React.FC = ({ children }) => (
  <pre className={styles.pre}>
    <code>
      <Typography.Text copyable>{children}</Typography.Text>
    </code>
  </pre>
);

export default (): React.ReactNode => {
  const intl = useIntl();
  return (
    <PageContainer>
      <Card>
        <ReservationModal />
        <p/>
        <ReservationsTable />
      </Card>
    </PageContainer>
  );
};
