import React from "react";
import styles from "./index.less";
import { message, Card, Modal, Button, Row, Col, Select } from "antd";
import SelectMultiple from '../SelectMultiple';
import type { CurrentUser } from '@/services/themeparks/typings.d';
import { getSchedules } from '@/services/themeparks/schedule';
import { createReservation } from '@/services/themeparks/reservation';
import type { Dispatch } from 'umi';
import { connect } from 'umi';
import {ReloadOutlined} from '@ant-design/icons';

const { Option } = Select;

@connect(
  ({group, accountSettings, attractions}) => ({
    currentUser: accountSettings.currentUser,
    group: group,
    attractions: attractions,
  }),
)
class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      loading: true,
      schedulesVisble: false,
      selectedAttraction: null,
      schedules: [],
      selectedGroup:[],
      selectedSchedule: null,
      confirmDisabled: true,
      searchingDisabled: true
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
      loading: true,
      selectedGroup:[],
      selectedSchedule: null,
      selectedAttraction: null,
      schedulesVisble: false,
      confirmDisabled: true,
      searchingDisabled: true
    });
  };

  handleChange = (value) => {
    this.setState({
      selectedGroup: value
    });
  }

  handleOk = async e => {
    const { dispatch, currentUser } = this.props;
    if (this.state.selectedSchedule == null || this.state.selectedAttraction == null) {
      message.error("You need to choose a valid attraction and schedule")
    } else {
      var reservations = [];
      const { selectedGroup, selectedSchedule, selectedAttraction } = this.state;
      reservations.push({user: this.props.currentUser.id, attraction: selectedAttraction, schedule: selectedSchedule});
      for (let i = 0; i < selectedGroup.length; i++) {
        reservations.push({user: selectedGroup[i], attraction: selectedAttraction, schedule: selectedSchedule});
      }
      await createReservation(reservations)
      .then(function(response) {
        message.success("Reservation request succeeded!", 5);
        dispatch({
          type: 'reservations/getReservations',
          payload: {'userId': currentUser.id},
        });
      })
      .catch(function(error) {
        console.error(error);
        if (error) {
          message.error("Reservation request failed: " + error.message, 10);
        }
      });
      this.setState({
        visible: false
      });
    }
  };

  handleCancel = e => {
    this.setState({
      visible: false,
      selectedGroup:[],
      selectedSchedule: null,
      selectedAttraction: null,
      schedulesVisble: false,
      confirmDisabled: true,
      searchingDisabled: true
    });
  };

  showAvailableSchedules = async e => {
    if (this.state.selectedAttraction == null) {
      message.error("You need to choose a valid attraction and schedule")
    } else {
      const schedules = await getSchedules({attractionId: this.state.selectedAttraction});
      let schedulesAvailable = [];
      schedules.forEach( schedule =>
        {
          if (schedule.status == "upcoming") {
            schedulesAvailable.push(schedule);
          }
        }
      );
      this.setState({
        schedules: schedulesAvailable,
        loading: false,
      });
    }
  }

  onChangeSchedule = e => {
    this.setState({
      selectedSchedule: e,
      confirmDisabled: false
    });
  }

  onChangeAttraction = e => {
    console.log(`selected ${e}`);
    this.setState({
      selectedAttraction: e,
      searchingDisabled: false
    });
  }

  refresh = () => {
    const { dispatch } = this.props;
    dispatch({
      type: 'reservations/getReservations',
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

    return (
      <div>
        <Row>
          <Col span={16}>
            <Button type="primary" onClick={this.showModal} >
              Create New Reservation
            </Button>
          </Col>
          <Col span={8} align="right">
            <Button onClick={this.refresh} icon={<ReloadOutlined />}> Refresh </Button>
          </Col>
        </Row>
        <Modal
          title="Create New Reservation"
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
          width="800px"
        >
          <Card>
            <p>Additional group members: </p>
            <div id="components-select-demo-multiple">
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
            </div>
            <p>Choose your attraction to join the queue:</p>
            <Row>
              <Col span={16}>
                <div className={styles.container}>
                  <div id="components-select-demo-search">
                    <Select
                      showSearch
                      style={{ width: 400 }}
                      placeholder="Please Select an attraction"
                      value={this.state.selectedAttraction}
                      optionFilterProp="children"
                      onChange={this.onChangeAttraction}
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
                  </div>
                </div>
              </Col>
              <Col span={8}>
                <Button type="primary" onClick={this.showAvailableSchedules}>
                  Search for available schedules
                </Button>
              </Col>
            </Row>
          </Card>
          <Card loading={this.state.loading}>
              <Col span={8}>
                <p>Choose a schedule to reserve for a group of {this.state.selectedGroup.length + 1}: </p>
              </Col>
              <div className={styles.container}>
                <div id="components-select-demo-search">
                  <Select
                    showSearch
                    style={{ width: 400 }}
                    placeholder="Please Select a schedule"
                    optionFilterProp="children"
                    onChange={this.onChangeSchedule}
                    value={this.state.selectedSchedule}
                    filterOption={(input, option) =>
                      option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                    }
                  >
                    {this.state.schedules.map(schedule => {
                      if (schedule.status == 'upcoming' && schedule.availableSeats - schedule.reservedForQueue >= this.state.selectedGroup.length + 1) {
                        return (
                          <Select.Option key={schedule.id}>{schedule.startTime} - {schedule.endTime}</Select.Option>
                        );
                      } else {
                        return null;
                      }
                    })}
                  </Select>
                </div>
              </div>
          </Card>
        </Modal>
      </div>
    );
  }
}

const attractions = ["RollerCoaster", "Stuffs"];

export default () => (
  <div className={styles.container}>
    <div id="components-modal-demo-basic">
      <App />
    </div>
  </div>
);
