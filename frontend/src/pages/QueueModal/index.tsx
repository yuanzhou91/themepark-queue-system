import React from "react";
import styles from "./index.less";
import { message, Modal, Button, Select, Row, Col } from "antd";
import SelectSearch from '../SelectSearch';
import SelectMultiple from '../SelectMultiple';
import { connect, useModel } from 'umi';
import { joinNewQueue, getCurrentQueueSize } from '@/services/themeparks/queue';
import { ReloadOutlined } from '@ant-design/icons';

const { Option } = Select;

@connect(
  ({group, accountSettings, attractions}) => ({
    currentUser: accountSettings.currentUser,
    group: group,
    attractions: attractions,
  }),
)
class QueueModal extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      loading: true,
      selectedAttraction: null,
      selectedGroup:[],
      queueSize: -1,
    };
  }

  componentDidMount() {
    const { dispatch, currentUser } = this.props;
    dispatch({
      type: 'accountSettings/fetchCurrent',
      payload: {'userId': currentUser.id},
    });
    dispatch({
      type: 'attractions/getAttractions',
    });
    dispatch({
      type: 'group/getUserGroup',
      payload: {'userId': currentUser.id},
    });

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

  showModal = () => {
    this.setState({
      visible: true,
      selectedAttraction: null,
      selectedGroup:[],
    });
  };

  handleOk = async e => {
    const { dispatch, currentUser } = this.props;
    const { selectedGroup, selectedAttraction } = this.state;
    if (this.state.selectedAttraction == null) {
      message.error("You must choose a valid attraction");
    } else {
      var users = [];
      users.push(this.props.currentUser.id);
      selectedGroup.forEach(item => {
        users.push(item);
      });
      console.log({users: users, attraction: selectedAttraction});
      await joinNewQueue({users: users, attraction: selectedAttraction})
        .then(function(response) {
          message.success("Join queue request succeeded!", 5);
          dispatch({
            type: 'queue/getCurrentQueue',
            payload: {'userId': currentUser.id},
          });
        })
        .catch(function(error) {
          if (error) {
            console.log(error);
            message.error("Join queue request failed: " + error.message, 5);
          }
        });
      this.setState({
        visible: false
      });
    }
  };

  handleChange = (value) => {
    this.setState({
      selectedGroup: value
    });
  }

  onChangeAttraction = async e => {
    console.log(`selected ${e}`);
    this.setState({
      selectedAttraction: e,
      searchingDisabled: false
    });
    const response = await getCurrentQueueSize({attractionId: e})
    console.log(response)
    this.setState({
      queueSize: response
    });
  }

  handleCancel = e => {
    console.log(e);
    this.setState({
      visible: false
    });
  };

  refresh = () => {
    const { dispatch } = this.props;
    dispatch({
      type: 'queue/getCurrentQueue',
      payload: {'userId': this.props.currentUser.id},
    });
  }

  render() {
    const children = [];
    const { group } = this.props.group;
    const { attractions } = this.props.attractions;
    for (let i = 0; i < group.length; i++) {
      children.push(<Option key={group[i].id}>{group[i].firstName}</Option>);
    }
    const { queueSize } = this.state;
    let queue = <span> </span>
    if (queueSize >= 0 && this.state.selectedAttraction != null) {
      queue = <span>Attraction has total {queueSize} people in queue right now. </span>
    }
    return (
      <div>
        <Row>
          <Col span={16}>
            <Button type="primary" onClick={this.showModal}>
              Join New Queue
            </Button>
          </Col>
          <Col span={8} align="right">
            <Button onClick={this.refresh} icon={<ReloadOutlined />}> Refresh </Button>
          </Col>
        </Row>
        <Modal
          title="Join new Queue"
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
          <p>Choose your attraction to join the queue:</p>
          <Select
            showSearch
            style={{ width: 400 }}
            placeholder="Please Select an attraction"
            optionFilterProp="children"
            onChange={this.onChangeAttraction}
            value={this.state.selectedAttraction}
            filterOption={(input, option) =>
              option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
            }
          >
            {attractions.map(attraction => {
              return (
                <Select.Option key={attraction.id}>{attraction.name}</Select.Option>
              );
            })}
          </Select>
          <p></p>
          {queue}
          <p></p>
          <p>Additional group members: </p>
          <Select
            mode="multiple"
            style={{ width: "100%" }}
            placeholder="Please select"
            defaultValue={this.state.selectedGroup}
            onChange={this.handleChange}
            value={this.state.selectedGroup}
          >
            {children}
          </Select>
        </Modal>
      </div>
    );
  }
}

const attractions = ["RollerCoaster", "Stuffs"];

export default () => (
  <div className={styles.container}>
    <div id="components-modal-demo-basic">
      <QueueModal />
    </div>
  </div>
);
