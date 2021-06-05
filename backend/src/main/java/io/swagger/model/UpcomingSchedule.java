package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UpcomingSchedule
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-05-21T06:16:38.139Z")


public class UpcomingSchedule {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("attraction")
  private String attraction = null;

  @JsonProperty("totalSeats")
  private Integer totalSeats = null;

  @JsonProperty("availableSeats")
  private Integer availableSeats = null;

  @JsonProperty("startTime")
  private String startTime = null;

  @JsonProperty("endTime")
  private String endTime = null;

  @JsonProperty("nextNumberToCall")
  private Integer nextNumberToCall = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    UPCOMING("upcoming"),

    CHECKING_IN("checking-in"),

    POST_CHECKIN("post-checkin"),

    COMPLETED("completed"),

    CANCELLED("cancelled");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  public UpcomingSchedule() {}

  public UpcomingSchedule(Schedule schedule, int nextNumberToCall) {
    this.attraction = schedule.getAttraction();
    this.nextNumberToCall = schedule.availableSeats == 0 ? 0 : nextNumberToCall;
    this.availableSeats = schedule.availableSeats;
    this.endTime = schedule.endTime;
    this.startTime = schedule.startTime;
    this.id = schedule.id;
    this.totalSeats = schedule.totalSeats;
    this.status = StatusEnum.fromValue(schedule.getStatus().toString());
  }

  public UpcomingSchedule id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public UpcomingSchedule attraction(String attraction) {
    this.attraction = attraction;
    return this;
  }

  /**
   * Get attraction
   * @return attraction
  **/
  @ApiModelProperty(value = "")


  public String getAttraction() {
    return attraction;
  }

  public void setAttraction(String attraction) {
    this.attraction = attraction;
  }

  public UpcomingSchedule totalSeats(Integer totalSeats) {
    this.totalSeats = totalSeats;
    return this;
  }

  /**
   * Get totalSeats
   * @return totalSeats
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalSeats() {
    return totalSeats;
  }

  public void setTotalSeats(Integer totalSeats) {
    this.totalSeats = totalSeats;
  }

  public UpcomingSchedule availableSeats(Integer availableSeats) {
    this.availableSeats = availableSeats;
    return this;
  }

  /**
   * Get availableSeats
   * @return availableSeats
  **/
  @ApiModelProperty(value = "")


  public Integer getAvailableSeats() {
    return availableSeats;
  }

  public void setAvailableSeats(Integer availableSeats) {
    this.availableSeats = availableSeats;
  }

  public UpcomingSchedule startTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * Get startTime
   * @return startTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public UpcomingSchedule endTime(String endTime) {
    this.endTime = endTime;
    return this;
  }

  /**
   * Get endTime
   * @return endTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public UpcomingSchedule nextNumberToCall(Integer nextNumberToCall) {
    this.nextNumberToCall = nextNumberToCall;
    return this;
  }

  /**
   * Get nextNumberToCall
   * @return nextNumberToCall
  **/
  @ApiModelProperty(value = "")


  public Integer getNextNumberToCall() {
    return nextNumberToCall;
  }

  public void setNextNumberToCall(Integer nextNumberToCall) {
    this.nextNumberToCall = nextNumberToCall;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpcomingSchedule upcomingSchedule = (UpcomingSchedule) o;
    return Objects.equals(this.id, upcomingSchedule.id) &&
        Objects.equals(this.attraction, upcomingSchedule.attraction) &&
        Objects.equals(this.totalSeats, upcomingSchedule.totalSeats) &&
        Objects.equals(this.availableSeats, upcomingSchedule.availableSeats) &&
        Objects.equals(this.startTime, upcomingSchedule.startTime) &&
        Objects.equals(this.endTime, upcomingSchedule.endTime) &&
        Objects.equals(this.nextNumberToCall, upcomingSchedule.nextNumberToCall) &&
        Objects.equals(this.status, upcomingSchedule.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, attraction, totalSeats, availableSeats, startTime, endTime, nextNumberToCall, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpcomingSchedule {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    attraction: ").append(toIndentedString(attraction)).append("\n");
    sb.append("    totalSeats: ").append(toIndentedString(totalSeats)).append("\n");
    sb.append("    availableSeats: ").append(toIndentedString(availableSeats)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    nextNumberToCall: ").append(toIndentedString(nextNumberToCall)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

