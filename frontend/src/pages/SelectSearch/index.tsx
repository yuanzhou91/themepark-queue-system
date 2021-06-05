import React from "react";
import styles from "./index.less";
import { Select } from "antd";

const { Option } = Select;

function onChange(value) {
  console.log(`selected ${value}`);
}

function onBlur() {
  console.log("blur");
}

function onFocus() {
  console.log("focus");
}

const attractions = ["RollerCoaster", "Water Park"];

export default (props) => (
  <div className={styles.container}>
    <div id="components-select-demo-search">
      <Select
        showSearch
        style={{ width: 400 }}
        placeholder="Please Select"
        optionFilterProp="children"
        onChange={onChange}
        onFocus={onFocus}
        onBlur={onBlur}
        filterOption={(input, option) =>
          option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
        }
      >
        {props.data.map(tag => {
          return (
            <Option value={tag}>{tag}</Option>
          );
        })}
      </Select>
    </div>
  </div>
);
