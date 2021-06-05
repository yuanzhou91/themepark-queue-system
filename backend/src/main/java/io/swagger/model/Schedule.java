package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Schedule
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-05-21T06:16:38.139Z")


public class Schedule   {
  @JsonProperty("id")
  protected String id = null;

  @JsonProperty("attraction")
  protected String attraction = null;

  @JsonProperty("totalSeats")
  protected Integer totalSeats = null;

  @JsonProperty("availableSeats")
  protected Integer availableSeats = null;

  @JsonProperty("reservedForQueue")
  protected Integer reservedForQueue = null;

  @JsonProperty("startTime")
  protected String startTime = null;

  @JsonProperty("endTime")
  protected String endTime = null;

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
  protected StatusEnum status = null;

  public Schedule id(String id) {
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

  public Schedule attraction(String attraction) {
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

  public Schedule totalSeats(Integer totalSeats) {
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

  public Schedule availableSeats(Integer availableSeats) {
    this.availableSeats = availableSeats;
    return this;
  }

  /**
   * Get availableSeats
   * @return availableSeats
  **/
  @ApiModelProperty(value = "")


  public Integer getReservedForQueue() {
    return reservedForQueue;
  }

  public void setReservedForQueue(Integer reservedForQueue) {
    this.reservedForQueue = reservedForQueue;
  }

  public Schedule reservedForQueue(Integer reservedForQueue) {
    this.reservedForQueue = reservedForQueue;
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

  public Schedule startTime(String startTime) {
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

  public Schedule endTime(String endTime) {
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

  public Schedule status(StatusEnum status) {
    this.status = status;
    return this;
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
    Schedule schedule = (Schedule) o;
    return Objects.equals(this.id, schedule.id) &&
        Objects.equals(this.attraction, schedule.attraction) &&
        Objects.equals(this.totalSeats, schedule.totalSeats) &&
        Objects.equals(this.availableSeats, schedule.availableSeats) &&
        Objects.equals(this.startTime, schedule.startTime) &&
        Objects.equals(this.endTime, schedule.endTime) &&
        Objects.equals(this.status, schedule.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, attraction, totalSeats, availableSeats, startTime, endTime, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Schedule {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    attraction: ").append(toIndentedString(attraction)).append("\n");
    sb.append("    totalSeats: ").append(toIndentedString(totalSeats)).append("\n");
    sb.append("    availableSeats: ").append(toIndentedString(availableSeats)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
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

