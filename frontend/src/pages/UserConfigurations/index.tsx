import { InfoCircleOutlined } from '@ant-design/icons';
import { Button, Card, DatePicker, Input, Form, InputNumber, Radio, Select, Tooltip } from 'antd';
import type { Dispatch } from 'umi';
import { connect, FormattedMessage, formatMessage } from 'umi';
import type { FC } from 'react';
import React from 'react';
import { PageContainer } from '@ant-design/pro-layout';
import styles from './style.less';

const FormItem = Form.Item;
const { Option } = Select;
const { RangePicker } = DatePicker;
const { TextArea } = Input;

type UserConfigurationsProps = {
  submitting: boolean;
  dispatch: Dispatch;
};

const UserConfigurations: FC<UserConfigurationsProps> = (props) => {
  const { submitting } = props;
  const [form] = Form.useForm();
  const [showPublicUsers, setShowPublicUsers] = React.useState(false);
  const formItemLayout = {
    labelCol: {
      xs: { span: 24 },
      sm: { span: 7 },
    },
    wrapperCol: {
      xs: { span: 24 },
      sm: { span: 12 },
      md: { span: 10 },
    },
  };

  const submitFormLayout = {
    wrapperCol: {
      xs: { span: 24, offset: 0 },
      sm: { span: 10, offset: 7 },
    },
  };

  const onFinish = (values: Record<string, any>) => {
    const { dispatch } = props;
    dispatch({
      type: 'userConfigurations/submitRegularForm',
      payload: values,
    });
  };

  const onFinishFailed = (errorInfo: any) => {
    // eslint-disable-next-line no-console
    console.log('Failed:', errorInfo);
  };

  const onValuesChange = (changedValues: Record<string, any>) => {
    const { publicType } = changedValues;
    if (publicType) setShowPublicUsers(publicType === '2');
  };

  return (
    <PageContainer content={<FormattedMessage id="userconfigurations.basic.description" />}>
      <Card bordered={false}>
        <Form
          hideRequiredMark
          style={{ marginTop: 8 }}
          form={form}
          name="basic"
          initialValues={{ public: '1' }}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
          onValuesChange={onValuesChange}
        >
          <FormItem
            {...formItemLayout}
            label={<FormattedMessage id="userconfigurations.title.label" />}
            name="title"
            rules={[
              {
                required: true,
                message: formatMessage({ id: 'userconfigurations.title.required' }),
              },
            ]}
          >
            <Input placeholder={formatMessage({ id: 'userconfigurations.title.placeholder' })} />
          </FormItem>
          <FormItem
            {...formItemLayout}
            label={<FormattedMessage id="userconfigurations.date.label" />}
            name="date"
            rules={[
              {
                required: true,
                message: formatMessage({ id: 'userconfigurations.date.required' }),
              },
            ]}
          >
            <RangePicker
              style={{ width: '100%' }}
              placeholder={[
                formatMessage({ id: 'userconfigurations.placeholder.start' }),
                formatMessage({ id: 'userconfigurations.placeholder.end' }),
              ]}
            />
          </FormItem>
          <FormItem
            {...formItemLayout}
            label={<FormattedMessage id="userconfigurations.goal.label" />}
            name="goal"
            rules={[
              {
                required: true,
                message: formatMessage({ id: 'userconfigurations.goal.required' }),
              },
            ]}
          >
            <TextArea
              style={{ minHeight: 32 }}
              placeholder={formatMessage({ id: 'userconfigurations.goal.placeholder' })}
              rows={4}
            />
          </FormItem>
          <FormItem
            {...formItemLayout}
            label={<FormattedMessage id="userconfigurations.standard.label" />}
            name="standard"
            rules={[
              {
                required: true,
                message: formatMessage({ id: 'userconfigurations.standard.required' }),
              },
            ]}
          >
            <TextArea
              style={{ minHeight: 32 }}
              placeholder={formatMessage({ id: 'userconfigurations.standard.placeholder' })}
              rows={4}
            />
          </FormItem>
          <FormItem
            {...formItemLayout}
            label={
              <span>
                <FormattedMessage id="userconfigurations.client.label" />
                <em className={styles.optional}>
                  <FormattedMessage id="userconfigurations.form.optional" />
                  <Tooltip title={<FormattedMessage id="userconfigurations.label.tooltip" />}>
                    <InfoCircleOutlined style={{ marginRight: 4 }} />
                  </Tooltip>
                </em>
              </span>
            }
            name="client"
          >
            <Input placeholder={formatMessage({ id: 'userconfigurations.client.placeholder' })} />
          </FormItem>
          <FormItem
            {...formItemLayout}
            label={
              <span>
                <FormattedMessage id="userconfigurations.invites.label" />
                <em className={styles.optional}>
                  <FormattedMessage id="userconfigurations.form.optional" />
                </em>
              </span>
            }
            name="invites"
          >
            <Input placeholder={formatMessage({ id: 'userconfigurations.invites.placeholder' })} />
          </FormItem>
          <FormItem
            {...formItemLayout}
            label={
              <span>
                <FormattedMessage id="userconfigurations.weight.label" />
                <em className={styles.optional}>
                  <FormattedMessage id="userconfigurations.form.optional" />
                </em>
              </span>
            }
            name="weight"
          >
            <InputNumber
              placeholder={formatMessage({ id: 'userconfigurations.weight.placeholder' })}
              min={0}
              max={100}
            />
            <span className="ant-form-text">%</span>
          </FormItem>
          <FormItem
            {...formItemLayout}
            label={<FormattedMessage id="userconfigurations.public.label" />}
            help={<FormattedMessage id="userconfigurations.label.help" />}
            name="publicType"
          >
            <div>
              <Radio.Group>
                <Radio value="1">
                  <FormattedMessage id="userconfigurations.radio.public" />
                </Radio>
                <Radio value="2">
                  <FormattedMessage id="userconfigurations.radio.partially-public" />
                </Radio>
                <Radio value="3">
                  <FormattedMessage id="userconfigurations.radio.private" />
                </Radio>
              </Radio.Group>
              <FormItem style={{ marginBottom: 0 }} name="publicUsers">
                <Select
                  mode="multiple"
                  placeholder={formatMessage({ id: 'userconfigurations.publicUsers.placeholder' })}
                  style={{
                    margin: '8px 0',
                    display: showPublicUsers ? 'block' : 'none',
                  }}
                >
                  <Option value="1">
                    <FormattedMessage id="userconfigurations.option.A" />
                  </Option>
                  <Option value="2">
                    <FormattedMessage id="userconfigurations.option.B" />
                  </Option>
                  <Option value="3">
                    <FormattedMessage id="userconfigurations.option.C" />
                  </Option>
                </Select>
              </FormItem>
            </div>
          </FormItem>
          <FormItem {...submitFormLayout} style={{ marginTop: 32 }}>
            <Button type="primary" htmlType="submit" loading={submitting}>
              <FormattedMessage id="userconfigurations.form.submit" />
            </Button>
            <Button style={{ marginLeft: 8 }}>
              <FormattedMessage id="userconfigurations.form.save" />
            </Button>
          </FormItem>
        </Form>
      </Card>
    </PageContainer>
  );
};

export default connect(({ loading }: { loading: { effects: Record<string, boolean> } }) => ({
  submitting: loading.effects['userConfigurations/submitRegularForm'],
}))(UserConfigurations);
