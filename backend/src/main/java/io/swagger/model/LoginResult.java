package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * LoginResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-05-21T06:16:38.139Z")


public class LoginResult   {
  @JsonProperty("status")
  private String status = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("currentAuthority")
  private String currentAuthority = null;

  public LoginResult status(String status) {
    this.status = status;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public LoginResult setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LoginResult type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public LoginResult currentAuthority(String currentAuthority) {
    this.currentAuthority = currentAuthority;
    return this;
  }

  /**
   * Get currentAuthority
   * @return currentAuthority
  **/
  @ApiModelProperty(value = "")


  public String getCurrentAuthority() {
    return currentAuthority;
  }

  public void setCurrentAuthority(String currentAuthority) {
    this.currentAuthority = currentAuthority;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginResult loginResult = (LoginResult) o;
    return Objects.equals(this.status, loginResult.status) &&
        Objects.equals(this.type, loginResult.type) &&
        Objects.equals(this.currentAuthority, loginResult.currentAuthority);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, type, currentAuthority);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoginResult {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    currentAuthority: ").append(toIndentedString(currentAuthority)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

