import React from "react";
import styles from "./index.less";
import { Card, Row, Col } from "antd";
import StepsError from '../StepsError';

export default () => (
  <div className={styles.container}>
    <div id="components-grid-demo-basic">
      <>
        <Row>
          <Col span={24}>
          </Col>
        </Row>
        <Row>
          <Col span={12}>
            <Card>
              <StepsError />
            </Card>
          </Col>
          <Col span={12}>col-12</Col>
        </Row>
        <Row>
          <Col span={8}>col-8</Col>
          <Col span={8}>col-8</Col>
          <Col span={8}>col-8</Col>
        </Row>
        <Row>
          <Col span={6}>col-6</Col>
          <Col span={6}>col-6</Col>
          <Col span={6}>col-6</Col>
          <Col span={6}>col-6</Col>
        </Row>
      </>
    </div>
  </div>
);
