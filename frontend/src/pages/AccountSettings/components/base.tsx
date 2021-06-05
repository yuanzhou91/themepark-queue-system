import { UploadOutlined } from '@ant-design/icons';
import { Button, Input, Select, Upload, Form, message } from 'antd';
import { connect, FormattedMessage, formatMessage } from 'umi';
import React, { Component } from 'react';
import type { CurrentUser } from '@/services/themeparks/typings.d';
import { updateUser } from '@/services/themeparks/user';
import PhoneView from './PhoneView';
import styles from './BaseView.less';

const { Option } = Select;

type SelectItem = {
  label: string;
  key: string;
};

type BaseViewProps = {
  currentUser?: CurrentUser;
};

class BaseView extends Component<BaseViewProps> {
  view: HTMLDivElement | undefined = undefined;

  getViewDom = (ref: HTMLDivElement) => {
    this.view = ref;
  };

  handleFinish = async (values) => {
    const { dispatch, currentUser } = this.props;
    var userToSubmit = values;
    userToSubmit.id = currentUser.id;
    await updateUser({'userId': currentUser.id}, userToSubmit)
      .then(function(response) {
        message.success("User information updated", 3);
        dispatch({
          type: 'accountSettings/fetchCurrent',
          payload: {'userId': currentUser.id},
        });
      })
      .catch(function(error) {
        if (error) {
          console.log(error);
          message.error("User information failed to update: " + error.message, 10);
        }
      });
    this.setState({
      visible: false
    });
  };

  render() {
    const { currentUser } = this.props;

    return (
      <div className={styles.baseView} ref={this.getViewDom}>
        <div className={styles.left}>
          <Form
            layout="vertical"
            onFinish={this.handleFinish}
            initialValues={currentUser}
            hideRequiredMark
          >
            <Form.Item
              name="email"
              label={formatMessage({ id: 'accountsettings.basic.email' })}
              rules={[
                {
                  required: true,
                  message: formatMessage({ id: 'accountsettings.basic.email-message' }, {}),
                },
              ]}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="firstName"
              label="First Name"
              rules={[
                {
                  required: true,
                  message: "First name must not be null",
                },
              ]}
            >
              <Input />
            </Form.Item>
            <Form.Item
                name="lastName"
              label="Last Name"
              rules={[
                {
                  required: true,
                  message: "Last name must not be null",
                },
              ]}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="phone"
              label={formatMessage({ id: 'accountsettings.basic.phone' })}
              rules={[
                {
                  required: true,
                  message: formatMessage({ id: 'accountsettings.basic.phone-message' }, {}),
                },
              ]}
            >
              <Input />
            </Form.Item>
            <Form.Item>
              <Button htmlType="submit" type="primary">
                <FormattedMessage
                  id="accountsettings.basic.update"
                  defaultMessage="Update Information"
                />
              </Button>
            </Form.Item>
          </Form>
        </div>
      </div>
    );
  }
}

export default connect(
  ({ accountSettings }: { accountSettings: { currentUser: CurrentUser } }) => ({
    currentUser: accountSettings.currentUser,
  }),
)(BaseView);
