import { FormattedMessage, formatMessage, connect } from 'umi';
import { AlipayOutlined, DingdingOutlined, TaobaoOutlined, UserAddOutlined } from '@ant-design/icons';
import { Avatar, List, message, Modal, Button, Input, Form } from 'antd';
import React, { Component, Fragment } from 'react';
import { addUserToGroup } from '@/services/themeparks/user';

@connect(
  ({group, accountSettings}) => ({
    currentUser: accountSettings.currentUser,
    group: group,
  }),
)
class GroupView extends Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      email: "",
    };
  }

  stringToColor(string) {
    let hash = 0;
    let i;

    /* eslint-disable no-bitwise */
    for (i = 0; i < string.length; i += 1) {
      hash = string.charCodeAt(i) + ((hash << 5) - hash);
    }

    let color = '#';

    for (i = 0; i < 3; i += 1) {
      const value = (hash >> (i * 8)) & 0xff;
      color += `00${value.toString(16)}`.substr(-2);
    }
    /* eslint-enable no-bitwise */

    return color;
  }

  componentDidMount() {
    const { dispatch, currentUser } = this.props;
    dispatch({
      type: 'accountSettings/fetchCurrent',
      payload: {'userId': currentUser.id},
    });
    if (currentUser != null) {
      dispatch({
        type: 'group/getUserGroup',
        payload: {'userId': currentUser.id},
      });
    }
  }

  componentDidUpdate(prevProps) {
    const { dispatch } = this.props;
    if (JSON.stringify(this.props.currentUser) != JSON.stringify(prevProps.currentUser)) {
      dispatch({
        type: 'group/getUserGroup',
        payload: {'userId': this.props.currentUser.id},
      });
      return;
    }
  }

  deleteUserFromGroup = (e) => {
    const { dispatch, currentUser } = this.props;
    dispatch({
      type: 'group/deletUserFromGroup',
      payload: {'userId': currentUser.id, 'userToDelete': e.target.id},
    });
  };

  showModal = () => {
    this.setState({
      visible: true,
      email: "",
    });
  };

  handleOk = async (e) => {
    const { dispatch, currentUser } = this.props;
    if (this.state.email == "") {
      message.error("You must input valid email for the user.", 5);
    } else {
      var userToSubmit = {email: this.state.email};
      await addUserToGroup({'userId': currentUser.id}, userToSubmit)
        .then(function(response) {
          message.success("User added to your group!", 3);
          dispatch({
            type: 'group/getUserGroup',
            payload: {'userId': currentUser.id},
          });
        })
        .catch(function(error) {
          if (error) {
            message.error("Failed to add user to your group: "+ error.message, 10);
          }
        });
      this.setState({
        visible: false
      });
    }
  };

  handleEmail = (e) => {
    this.state.email=e.target.value;
  }

  handleCancel = e => {
    this.setState({
      visible: false,
      email: "",
    });
  };

  render() {
    var groupList = [];
    console.log(this.props);
    const { group } = this.props.group;
    for (var i = 0; i< group.length; i++) {
      console.log(this.stringToColor(group[i].firstName));
      groupList.push(
        {
          title: group[i].firstName + " " + group[i].lastName,
          description: "email:" + group[i].email + ", phone: " + group[i].phone,
          actions: [
            <a key="delete" id={group[i].id} onClick={this.deleteUserFromGroup}>
              Delete
            </a>,
          ],
          avatar: <Avatar
            style={{
              backgroundColor: this.stringToColor(group[i].firstName),
              verticalAlign: 'middle',
            }}> {group[i].firstName[0]} </Avatar>,
        }
      );
    }
    return (
      <Fragment>
        <List
          itemLayout="horizontal"
          dataSource={groupList}
          renderItem={(item) => (
            <List.Item actions={item.actions}>
              <List.Item.Meta
                avatar={item.avatar}
                title={item.title}
                description={item.description}
              />
            </List.Item>
          )}
        />
        <p/>
        <p/>
        <Button htmlType="submit" icon={<UserAddOutlined/>} type="primary" onClick={this.showModal}>
          Add User to Group
        </Button>
        <Modal
          title="Add a user to your group"
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
              name="email"
              label="User's email"
              rules={[
                {
                  required: true,
                  message: "Email must not be null",
                },
              ]}
            >
              <Input value={this.state.email || ''} visibilityToggle="true" onChange={this.handleEmail}/>
            </Form.Item>
          </Form>
        </Modal>
      </Fragment>
    );
  }
}

export default GroupView;
