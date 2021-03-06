import React from "react";
import styles from "./index.less";
import { Select } from "antd";

const { Option } = Select;

const children = [];
for (let i = 10; i < 36; i++) {
  children.push(<Option key={i}>{"member " + i}</Option>);
}

function handleChange(value) {
  console.log(`selected ${value}`);
}

export default () => (
  <div className={styles.container}>
    <div id="components-select-demo-multiple">
      <Select
        mode="multiple"
        style={{ width: "100%" }}
        placeholder="Please select"
        defaultValue={[]}
        onChange={handleChange}
      >
        {children}
      </Select>
    </div>
  </div>
);
