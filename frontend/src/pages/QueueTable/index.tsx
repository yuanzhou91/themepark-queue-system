import React from "react";
import styles from "./index.less";
import { Table, Tag } from "antd";
import { connect, useModel } from 'umi';
import { Card } from 'antd';
import QueueModal from '../QueueModal';

@connect(
  ({queue, accountSettings, attractions}) => ({
    currentUser: accountSettings.currentUser,
    queue: queue,
    attractions: attractions,
  }),
)
class QueueTable extends React.Component {
  constructor(props) {
    super(props);
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
  }

  componentDidUpdate(prevProps) {
    const { dispatch } = this.props;
    if (this.props.currentUser != prevProps.currentUser) {
      dispatch({
        type: 'queue/getCurrentQueue',
        payload: {'userId': this.props.currentUser.id},
      });
      return;
    }
  }

  quit = () => {
    const { dispatch, currentUser } = this.props;
    dispatch({
      type: 'queue/quitQueue',
      payload: {'user': currentUser.id},
    });
  };

  render () {
    const { queue } = this.props.queue;
    const { attractions } = this.props.attractions;
    var idToAttractions = attractions.reduce((map, obj) => (map[obj.id] = obj.name, map), {});

    const columns = [
      {
        title: "Attraction",
        dataIndex: "attraction",
        key: "attraction",
        render: id => <span>{idToAttractions[id]}</span>
      },
      {
        title: "My Number in Queue",
        dataIndex: "numberInQueue",
        key: "numberInQueue"
      },
      {
        title: "Location",
        dataIndex: "location",
        key: "location"
      },
      {
        title: "Next Number to Call",
        dataIndex: "nextNumberToCall",
        key: "nextNumberToCall"
      },
      {
        title: "ETA",
        dataIndex: "eta",
        key: "eta"
      },
      {
        title: "Status",
        key: "status",
        dataIndex: "status",
        render: status => {
          let color = "geekblue";
          if (status === "called") {
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
        title: "Action",
        key: "action",
        render: (text, record) => (
          <span>
            <a onClick={() => this.quit()}>Quit</a>
          </span>
        )
      }
    ];

    return (
      <Card>
        <QueueModal />
        <p />
        <div className={styles.container}>
          <div id="components-table-demo-basic">
            <Table rowKey={(record) => (record.id)} columns={columns} dataSource={queue} pagination={{ position: ["none", "none"] }}/>
          </div>
        </div>
      </Card>
    );
  }
}

export default (QueueTable);
