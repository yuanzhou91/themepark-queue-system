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
 * ErrorResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-05-21T06:16:38.139Z")


public class ErrorResponse   {
  @JsonProperty("errorCode")
  private String errorCode = null;

  @JsonProperty("errorMessage")
  private String errorMessage = null;

  @JsonProperty("success")
  private Boolean success = null;

  public ErrorResponse errorCode(String errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * error code
   * @return errorCode
  **/
  @ApiModelProperty(required = true, value = "error code")
  @NotNull


  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public ErrorResponse errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * error message
   * @return errorMessage
  **/
  @ApiModelProperty(value = "error message")


  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public ErrorResponse success(Boolean success) {
    this.success = success;
    return this;
  }

  /**
   * success or not
   * @return success
  **/
  @ApiModelProperty(value = "success or not")


  public Boolean isSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse errorResponse = (ErrorResponse) o;
    return Objects.equals(this.errorCode, errorResponse.errorCode) &&
        Objects.equals(this.errorMessage, errorResponse.errorMessage) &&
        Objects.equals(this.success, errorResponse.success);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorCode, errorMessage, success);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorResponse {\n");
    
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
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

