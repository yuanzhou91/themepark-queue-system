import { connect, FormattedMessage, formatMessage } from 'umi';
import React, { Component } from 'react';
import { updateUser } from '@/services/themeparks/user';
import { List } from 'antd';
import { message, Modal, Button, Input, Form } from "antd";

type Unpacked<T> = T extends (infer U)[] ? U : T;

const passwordStrength = {
  strong: (
    <span className="strong">
      <FormattedMessage id="accountsettings.security.strong" defaultMessage="Strong" />
    </span>
  ),
  medium: (
    <span className="medium">
      <FormattedMessage id="accountsettings.security.medium" defaultMessage="Medium" />
    </span>
  ),
  weak: (
    <span className="weak">
      <FormattedMessage id="accountsettings.security.weak" defaultMessage="Weak" />
      Weak
    </span>
  ),
};

class SecurityView extends Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      password: this.props.currentUser.password,
      confirmPassword: this.props.currentUser.password,
    };
  }

  showModal = () => {
    this.setState({
      visible: true,
      password: "",
      confirmPassword: "",
    });
  };

  handleOk = async (e) => {
    const { dispatch, currentUser } = this.props;
    if (this.state.password == "" || this.state.password != this.state.confirmPassword) {
      message.error("You must input valid password and confirm password.");
    } else {
      var userToSubmit = {id: currentUser.id, password: this.state.password};
      await updateUser({'userId': currentUser.id}, userToSubmit)
        .then(function(response) {
          message.success("User password updated", 3);
          dispatch({
            type: 'accountSettings/fetchCurrent',
            payload: {'userId': currentUser.id},
          });
        })
        .catch(function(error) {
          if (error) {
            message.error("User password failed to update: " + error.message, 10);
          }
        });
      this.setState({
        visible: false
      });
    }
  };

  handlePasswordChange = (e) => {
    this.state.password=e.target.value;
  }

  handleConfirmPasswordChange = (e) => {
    this.state.confirmPassword=e.target.value;
  }

  handleCancel = e => {
    this.setState({
      visible: false
    });
  };


  render() {
    const data = [
      {
        title: formatMessage({ id: 'accountsettings.security.password' }, {}),
        description: (
          <>
            {formatMessage({ id: 'accountsettings.security.password-description' })}：
            {passwordStrength.strong}
          </>
        ),
        actions: [
          <a key="Modify" onClick={this.showModal}>
            <FormattedMessage id="accountsettings.security.modify" defaultMessage="Modify" />
          </a>,
        ],
      },
    ];
    return (
      <div>
        <Modal
          title="Updte Password"
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
          <Form
            layout="vertical"
            onFinish={this.handleOk}
            hideRequiredMark
          >
            <Form.Item
              name="password"
              label="password"
              rules={[
                {
                  required: true,
                  message: "password must not be null",
                },
              ]}
            >
              <Input.Password value={this.state.password || ''} visibilityToggle="true" onChange={this.handlePasswordChange}/>
            </Form.Item>
            <Form.Item
              name="confirmPassword"
              label="confirm password"
              rules={[
                {
                  required: true,
                  message: "confirm password must not be null",
                },
              ]}
            >
              <Input.Password value={this.state.confirmPassword || ''} visibilityToggle="true" onChange={this.handleConfirmPasswordChange}/>
            </Form.Item>
          </Form>
        </Modal>

        <List<Unpacked<typeof data>>
          itemLayout="horizontal"
          dataSource={data}
          renderItem={(item) => (
            <List.Item actions={item.actions}>
              <List.Item.Meta title={item.title} description={item.description} />
            </List.Item>
          )}
        />
      </div>
    );
  }
}

export default connect(
  ({ accountSettings }: { accountSettings: { currentUser: CurrentUser } }) => ({
    currentUser: accountSettings.currentUser,
  }),
)(SecurityView);
