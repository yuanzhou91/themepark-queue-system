import React from "react";
import styles from "./index.less";
import { Table, Tag } from "antd";
import type { CurrentUser } from '@/services/themeparks/typings.d';
import type { Dispatch } from 'umi';
import { connect, useModel } from 'umi';
const format= "HH:mm";
import moment from 'moment';

type ReservationsProps = {
  dispatch: Dispatch;
  currentUser: CurrentUser;
};

@connect(
  ({reservations, accountSettings, attractions}) => ({
    currentUser: accountSettings.currentUser,
    reservations: reservations,
    attractions: attractions,
  }),
)
class ReservationsTable extends React.Component<ReservationsProps> {
  constructor(props) {
    super(props);
    this.state = {
      loading: true,
      data: null,
    };
  }


  componentDidMount() {
    const { dispatch, currentUser } = this.props;
    console.log("fetching current user.");
    dispatch({
      type: 'accountSettings/fetchCurrent',
      payload: {'userId': currentUser.id},
    });
    dispatch({
      type: 'attractions/getAttractions',
    });
  }

  componentDidUpdate(prevProps) {
    const { dispatch } = this.props;
    if (this.props.currentUser != prevProps.currentUser) {
      dispatch({
        type: 'reservations/getReservations',
        payload: {'userId': this.props.currentUser.id},
      });
      return;
    }
  }

  cancelReservation = (record) => {
    const { dispatch, currentUser } = this.props;
    dispatch({
      type: 'reservations/cancelReservation',
      payload: {'reservationId': record.id, 'userId': currentUser.id},
    });
  };

  confirmReservation = (record) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'reservations/confirmReservation',
      payload: {'reservationId': record.id},
    });
  };

  render() {
    const { attractions } = this.props.attractions;
    var idToAttractions = attractions.reduce((map, obj) => (map[obj.id] = obj.name, map), {});
    const data = this.props.reservations;
    let reservations = data.reservations;
    reservations.sort((a, b) => {
      var dateA = moment(a.startTime, format);
      var dateB = moment(b.startTime, format);
      if (dateA.isBefore(dateB)) return -1;
      if (dateB.isBefore(dateA)) return 1;
      return 0;
    });
    const columns = [
      {
        title: "Attraction",
        dataIndex: "attraction",
        key: "attraction",
        render: id => <span>{idToAttractions[id]}</span>
      },
      {
        title: "Location",
        dataIndex: "location",
        key: "location"
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
        title: "Status",
        key: "status",
        dataIndex: "status",
        render: status => {
          let color = "geekblue";
          if (status === "checking-in") {
            color = "volcano";
          } else if (status === "passed" || status === "cancelled") {
            color = "black";
          } else if (status === "confirmed") {
            color = "green";
          }
          console.log("status is ", status);
          return (
            <Tag color={color} key={status}>
              {status.toUpperCase()}
            </Tag>
          );
        }
      },
      {
        title: "Actions",
        key: "action",
        render: (text, record) => {
          let confirmButton;
          if (record.status === "checking-in") {
            confirmButton = <a style={{ marginRight: 16 }} onClick={() => this.confirmReservation(record)}>Check-in </a>;
          }
          let cancelText = "Cancel";
          if (record.status === "passed" || record.status === "cancelled") {
            cancelText = "Delete";
          }
          let cancelButton = <a onClick={() => this.cancelReservation(record)}>{cancelText}</a>;
          if (record.status === "confirmed") {
            cancelButton = null;
          }
          return (
            <span>
              {confirmButton}
              {cancelButton}
            </span>
          );
        }
      }
    ];
    return (
      <div className={styles.container}>
        <div id="components-table-demo-basic">
          <Table rowKey={(record) => (record.id)} columns={columns} dataSource={reservations} loading={data.isLoading} />
        </div>
      </div>
    );
  }
}

export default (ReservationsTable);
