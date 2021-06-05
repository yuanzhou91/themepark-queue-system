import React from "react";
import styles from "./index.less";
import { message, Card, Modal, Button, Select, TimePicker, InputNumber } from "antd";
import { connect, useModel } from 'umi';
import { getSchedules, createSchedule } from '@/services/themeparks/schedule';
import moment from 'moment';

const { Option } = Select;
const format= "HH:mm";

@connect(
  ({attractions}) => ({
    attractions: attractions,
  }),
)
class SchedulesModal extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      loading: true,
      selectedAttraction: "",
      schedulesVisble: false,
      schedules: [],
      startTime: "00:00",
      endTime: "00:00",
      seats: 20,
    };
  }

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'attractions/getAttractions',
    });
  }

  showModal = () => {
    this.setState({
      visible: true,
      loading: true,
      selectedAttraction: null,
      schedulesVisble: false,
      schedules: [],
      startTime: "12:00",
      endTime: "13:00",
      seats: 20,
      reservedForQueue: 0,
    });
  };

  handleOk = async e => {
    const { dispatch, currentUser } = this.props;
    const { selectedAttraction } = this.state;
    var startTime = moment(this.state.startTime, format);
    var endTime = moment(this.state.endTime, format);
    if (this.state.selectedAttraction == null) {
      message.error("You must choose a valid attraction");
    } else if (endTime.isBefore(startTime)) {
      message.error("End time must be after start time!");
    } else {
      await createSchedule({
          attraction: selectedAttraction,
          availableSeats: this.state.seats,
          totalSeats: this.state.seats,
          reservedForQueue: this.state.reservedForQueue,
          startTime: this.state.startTime,
          endTime: this.state.endTime,
          status: 'upcoming'
        })
        .then(function(response) {
          message.success("New schedule created!", 5);
          dispatch({
            type: 'schedules/getSchedules',
            payload: {attractionId: selectedAttraction}
          });
        })
        .catch(function(error) {
          if (error) {
            console.log(error);
            message.error("Schedule creation request failed: "+ error.message, 10);
          }
        });
      this.setState({
        visible: false
      });
    }
  };

  handleChangeStartTime = (time, timeStr) => {
    this.setState({
      startTime: timeStr
    });
  }

  handleChangeSeats = (seats) => {
    this.setState({
      seats: seats
    });
  }

  handleChangeReservedForQueue = (seats) => {
    if (seats > this.state.seats) {
      message.error('Cannot set seats reserved for queue larger than total seats.');
      this.setState({
        reservedForQueue: this.state.seats
      });
    } else {
      this.setState({
        reservedForQueue: seats
      });
    }
  }

  handleChangeEndTime = (time, timeStr) => {
    this.setState({
      endTime: timeStr
    });
  }

  onChangeAttraction = async e => {
    const schedules = await getSchedules({attractionId: e});
    this.setState({
      selectedAttraction: e,
      schedules: schedules,
      loading: false,
    });
  }

  handleCancel = e => {
    this.setState({
      visible: false,
      loading: true,
      selectedAttraction: null,
      schedulesVisble: false,
      schedules: [],
      startTime: "12:00",
      endTime: "13:00",
      seats: 100,
    });
  };

  render() {
    const { attractions } = this.props.attractions;
    return (
      <div>
        <Button type="primary" onClick={this.showModal}>
          Create New Schedule
        </Button>
        <Modal
          title="Create new schedule"
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
          <p>Choose an attraction create the schedule:</p>
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
          <Card loading={this.state.loading}>
            <p><b>Set total seats for this schedule: </b></p>
            <InputNumber min={1} max={100} value={this.state.seats} onChange={this.handleChangeSeats} />
            <p></p>
            <p></p>
            <p></p>
            <p><b>Set seats reserved for people in queue: </b></p>
            <InputNumber min={0} max={100} value={this.state.reservedForQueue} onChange={this.handleChangeReservedForQueue} />
            <p></p>
            <p></p>
            <p></p>
            <p><b>Currently scheduled times: </b></p>
            {this.state.schedules.map(schedule => {
              return (
                <span key={schedule.startTime} ><p>{schedule.startTime} - {schedule.endTime}</p><p/></span>
              );
            })}
            <p></p>
            <p></p>
            <p></p>
            <p><b>Select start and end time: </b></p>
            <span>From </span>
            <TimePicker value={moment(this.state.startTime, format)} defaultValue={moment("12:00", format)} onChange={this.handleChangeStartTime} format="HH:mm" />
            <span> To </span>
            <TimePicker value={moment(this.state.endTime, format)} defaultValue={moment("13:00", format)} onChange={this.handleChangeEndTime} format="HH:mm" />
          </Card>
        </Modal>
      </div>
    );
  }
}

export default () => (
  <div className={styles.container}>
    <div id="components-modal-demo-basic">
      <SchedulesModal />
    </div>
  </div>
);
