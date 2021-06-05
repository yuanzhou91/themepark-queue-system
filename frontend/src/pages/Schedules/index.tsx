import { PageContainer } from '@ant-design/pro-layout';
import React, { useState, useEffect } from 'react';
import { message, Table, Tag, Card, Select, Row, Col, Button } from 'antd';
import styles from './index.less';
import SchedulesModal from '../SchedulesModal';
import { deleteSchedule } from '@/services/themeparks/schedule'
import moment from 'moment';
import { connect, useModel } from 'umi';
const { Option } = Select;
const format= "HH:mm";
import { ReloadOutlined } from '@ant-design/icons';

@connect(
  ({attractions, schedules}) => ({
    attractions: attractions,
    schedules: schedules
  }),
)
class SchedulesTable extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      loading: true,
      schedules: [],
      selectedAttraction: "",
    };
  }

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'attractions/getAttractions',
    });
  }

  onChangeAttraction = async e => {
    this.setState({
      selectedAttraction: e,
      loading: true,
    });
    const { dispatch } = this.props;
    await dispatch({
      type: 'schedules/getSchedules',
      payload: {attractionId: e}
    });
    await dispatch({
      type: 'schedules/getUpcomingSchedule',
      payload: {attractionId: e}
    });
    this.setState({
      loading: false,
    });
  }

  deleteSchedule = async (record) => {
    const { dispatch } = this.props;
    const { selectedAttraction } = this.state;
    await deleteSchedule({'scheduleId': record.id})
    .then(function(response) {
      message.success("Schedule is deleted", 5);
    })
    .catch(function(error) {
      console.error(error);
      if (error) {
        message.error("Schedule deletion failed: " + error.message, 5);
      }
    });
    dispatch({
      type: 'schedules/getSchedules',
      payload: {attractionId: selectedAttraction}
    });
  };

  refreshUpcomingSchedule = async () => {
    const { dispatch } = this.props;
    await dispatch({
      type: 'schedules/getUpcomingSchedule',
      payload: {attractionId: this.state.selectedAttraction}
    });
  }

  refresh = () => {
    const { dispatch } = this.props;
    const { selectedAttraction } = this.state;
    dispatch({
      type: 'schedules/getSchedules',
      payload: {attractionId: selectedAttraction},
    });
  }

  updateScheduleStatus =  async (record, status) => {
    const { dispatch } = this.props;
    await dispatch({
      type: 'schedules/updateSchedule',
      payload: {'id': record.id, 'status': status},
    });
    await dispatch({
      type: 'schedules/getUpcomingSchedule',
      payload: {attractionId: this.state.selectedAttraction}
    });
  };

  updateUpcomingScheduleStatus = async (record, status) => {
    const { dispatch } = this.props;
    await dispatch({
      type: 'schedules/updateUpcomingSchedule',
      payload: {'id': record.id, 'status': status},
    });
    await dispatch({
      type: 'schedules/getSchedules',
      payload: {attractionId: this.state.selectedAttraction}
    });
  };

  updateUpcomingScheduleSeats = (record, availableSeats, nextNumberToCall) => {
    const { dispatch } = this.props;
    console.log({'id': record.id, 'nextNumberToCall': nextNumberToCall, 'availableSeats': availableSeats});
    dispatch({
      type: 'schedules/updateUpcomingSchedule',
      payload: {'id': record.id, 'nextNumberToCall': nextNumberToCall, 'availableSeats': availableSeats},
    });
  };

  renderStatus = (status) => {
    let color = "geekblue";
    if (status === "checking-in") {
      color = "volcano";
    } else if (status === "completed" ) {
      color = "cyan";
    } else if (status === "cancelled") {
      color = "black";
    } else if (status === "post-checkin") {
      color = "green";
    }
    console.log("status is ", status);
    return (
      <Tag color={color} key={status}>
        {status.toUpperCase()}
      </Tag>
    );
  }
  renderCheckInManagementButton = (record, status) => {
    let button;
    if (status == 'checking-in') {
      button = <Button type="primary" onClick={() => this.updateUpcomingScheduleStatus(record, 'post-checkin')}>End reservation check-in and start processing queue</Button>;
    } else if (status == 'post-checkin') {
      if (record.availableSeats == 0 || record.nextNumberToCall == 0) {
        button = <Button type="primary" onClick={() => this.updateUpcomingScheduleStatus(record, 'completed')}>Complete and start the ride!</Button>;
      } else {
        button = <Button type="primary" disabled="true">You can complete when no seats are available or no one is waiting in queue.</Button>;
      }
    }
    return button;
  }
  renderQueueManagement = (record) => {
    if (record.status != 'post-checkin' || record.availableSeats == 0) {
      return null;
    }
    let callButton = null;
    let confirmButton = null;
    let passButton = null;
    if (record.nextNumberToCall > 0) {
      callButton = <a onClick={() => this.updateUpcomingScheduleSeats(record, record.availableSeats-1, record.nextNumberToCall+1)}>appear and call next</a>;
      confirmButton = <a onClick={() => this.updateUpcomingScheduleSeats(record, record.availableSeats-1, record.nextNumberToCall+1)}>appear and call next</a>;
      passButton = <a onClick={() => this.updateUpcomingScheduleSeats(record, record.availableSeats, record.nextNumberToCall+1)}>no-show and call next</a>;
    }
    return (
      <Row gutter={[16, 24]} wrap='true'>
        <Col span={6}>
          <b>Number in queue called now: {record.nextNumberToCall == 0 ? 'None' : record.nextNumberToCall} </b>
        </Col>
        <Col span={6}>
          {confirmButton}
        </Col>
        <Col span={6}>
          {passButton}
        </Col>
      </Row>
    );
  }
  render() {
    const { attractions } = this.props.attractions;
    const { schedules, upcomingSchedule } = this.props.schedules;
    var earliestScheduleId = "";
    var earlisteTime = null;
    for (let i = 0; i<schedules.length; i++) {
      if (schedules[i].status == "checking-in" || schedules[i].status == "post-checkin") {
        earliestScheduleId = "";
        break;
      }
      if (schedules[i].status == "upcoming") {
        if (earliestScheduleId == "" || moment(schedules[i].startTime, format).isBefore(earlisteTime)) {
          earlisteTime = moment(schedules[i].startTime, format);
          earliestScheduleId = schedules[i].id;
        }
      }
    }
    console.log("earlisteTime:", earliestScheduleId);
    var idToAttractions = attractions.reduce((map, obj) => (map[obj.id] = obj.name, map), {});
    var checkInManagmentCard = null;
    if (upcomingSchedule.startTime) {
      console.log("upcomingSchedule:", upcomingSchedule);
      checkInManagmentCard =
        <Card loading={this.state.loading} title={"Check-in and Queue operation for next schedule: " + idToAttractions[this.state.selectedAttraction]}
          extra={<a onClick={this.refreshUpcomingSchedule}>Refresh</a>} >
          <Row gutter={[16, 24]} wrap='true'>
            <Col span={6}>
              <b>Start time: {upcomingSchedule.startTime}</b>
            </Col>
            <Col span={6}>
              <b>End time: {upcomingSchedule.endTime}</b>
            </Col>
          </Row>
          <p/>
          <p/>
          <p/>
          <Row gutter={[16, 24]} wrap='true'>
            <Col span={6}>
              <b>Current Status:</b>
            </Col>
            <Col span={6}>
              {this.renderStatus(upcomingSchedule.status)}
            </Col>
          </Row>
          <p/>
          <p/>
          <p/>
          <Row gutter={[16, 24]} wrap='true'>
            <Col span={6}>
              <b>Total Seats: {upcomingSchedule.totalSeats}</b>
            </Col>
            <Col span={6}>
              <b>Available Seats: {upcomingSchedule.availableSeats}</b>
            </Col>
          </Row>
          <p/>
          <p/>
          <p/>
          {this.renderQueueManagement(upcomingSchedule)}
          <p/>
          <p/>
          <p/>
          <Row gutter={[16, 24]} wrap='true'>
            <Col span={6}>
              {this.renderCheckInManagementButton(upcomingSchedule, upcomingSchedule.status)}
            </Col>
          </Row>

        </Card>
    }

    const columns = [
      {
        title: "Attraction",
        dataIndex: "attraction",
        key: "attraction",
        render: id => <span>{idToAttractions[id]}</span>
      },
      {
        title: "Start Time",
        dataIndex: "startTime",
        key: "startTime"
      },
      {
        title: "End Time",
        dataIndex: "endTime",
        key: "endTime"
      },
      {
        title: "Total Seats",
        dataIndex: "totalSeats",
        key: "totalSeats"
      },
      {
        title: "Available Seats",
        dataIndex: "availableSeats",
        key: "availableSeats"
      },
      {
        title: "Reserved seats for Queue",
        dataIndex: "reservedForQueue",
        key: "reservedForQueue"
      },
      {
        title: "Status",
        key: "status",
        dataIndex: "status",
        render: status => this.renderStatus(status)
      },
      {
        title: "Action",
        key: "action",
        render: (text, record) => {
          let checkinButton;
          if (record.status === "upcoming" && record.id == earliestScheduleId) {
            checkinButton = <a style={{ marginRight: 16 }} onClick={() => this.updateScheduleStatus(record, 'checking-in')}>Start Check-in</a>;
          }
          let cancelText = "Cancel";
          let cancelButton = <a onClick={() => this.updateScheduleStatus(record, 'cancelled')}>{cancelText}</a>;
          if (record.status === "checking-in" || record.status === "post-checkin") {
            cancelButton = null;
          }
          if (record.status === "completed" || record.status === "cancelled") {
            cancelText = "Delete";
            cancelButton = <a onClick={() => this.deleteSchedule(record)}>{cancelText}</a>;
          }
          return (
            <span>
              {checkinButton}
              {cancelButton}
            </span>
          );
        }
      }
    ];
    return (
      <PageContainer>
        <Card title="Today's schedules">
          <Row>
            <Col span={9}>
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
            </Col>
            <Col span={10}>
              <SchedulesModal />
            </Col>
            <Col span={4} align="right">
              <Button onClick={this.refresh} icon={<ReloadOutlined />}> Refresh </Button>
            </Col>
          </Row>
          <p/>
          <div className={styles.container}>
            <div id="components-table-demo-basic">
              <Table rowKey={(record) => (record.id)} columns={columns} loading={this.state.loading} dataSource={schedules} pagination={{ position: ["none", "none"] }}/>
            </div>
          </div>
        </Card>
        {checkInManagmentCard}
      </PageContainer>
    );
  }
}
export default (SchedulesTable);
