import React from "react";
import styles from "./index.less";
import { message, Card, Input, Modal, Button, Row, Col, Select, Form } from "antd";

import type { Dispatch } from 'umi';
import { createUser } from '@/services/themeparks/user';
import { FormattedMessage, formatMessage } from 'umi';

const { Option } = Select;

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      loading: true,
    };
  }

  showModal = () => {
    this.setState({
      visible: true,
    });
  };

  handleCancel = e => {
    this.setState({
      visible: false,
    });
  };

  handleFinish = async (values) => {
    var userToSubmit = values;
    var success = false;
    await createUser(userToSubmit)
      .then(function(response) {
        message.success("User registration succeeded!", 3);
        success = true;
      })
      .catch(function(error) {
        if (error) {
          message.error("Failed to register user: " + error.message, 10);
        }
      });
      if (success)  {
        this.setState({
          visible: false
        });
      }
  };

  render() {
    return (
      <div>
        <a
          style={{
            float: 'right',
          }}
          onClick={this.showModal}
        >
          <FormattedMessage id="pages.login.forgotPassword" defaultMessage="Register" />
        </a>
        <Modal
          title="Create New Guest"
          visible={this.state.visible}
          onCancel={this.handleCancel}
          width="800px"
          footer={null}
        >
          <Card>
            <Form
              layout="vertical"
              onFinish={this.handleFinish}
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
                name="password"
                label="Password"
                rules={[
                  {
                    required: true,
                    message: "password must not be empty",
                  },
                ]}
              >
                <Input.Password />
              </Form.Item>

              <Form.Item
                name="firstName"
                label="First Name"
                rules={[
                  {
                    required: true,
                    message: "First name must not be empty",
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
                    message: "Last name must not be empty",
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
              <Row>
                <Col  span={4}>
                  <Form.Item>
                    <Button htmlType="submit" type="primary">
                      <FormattedMessage
                        id="accountsettings.basic.register"
                        defaultMessage="Register"
                      />
                    </Button>
                  </Form.Item>
                </Col>
                <Col  span={4}>
                  <Button onClick={this.handleCancel} >
                    Cancel
                  </Button>
                </Col>
              </Row>
            </Form>
          </Card>

        </Modal>
      </div>
    );
  }
}

export default () => (
  <div className={styles.container}>
    <div>
      <App />
    </div>
  </div>
);
