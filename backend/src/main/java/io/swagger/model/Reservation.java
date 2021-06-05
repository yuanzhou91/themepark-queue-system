package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Reservation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-05-21T06:16:38.139Z")


public class Reservation   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("attraction")
  private String attraction = null;

  @JsonProperty("user")
  private String user = null;

  @JsonProperty("schedule")
  private String schedule = null;

  @JsonProperty("startTime")
  private String startTime = null;

  @JsonProperty("endTime")
  private String endTime = null;

  @JsonProperty("location")
  private String location = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    WAITING("waiting"),
    
    PASSED("passed"),
    
    CANCELLED("cancelled"),

    CONFIRMED("confirmed"),

    CHECKING_IN("checking-in"),

    COMPLETED("completed");

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

  public Reservation id(String id) {
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

  public Reservation attraction(String attraction) {
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

  public Reservation user(String user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
  **/
  @ApiModelProperty(value = "")


  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public Reservation schedule(String schedule) {
    this.schedule = schedule;
    return this;
  }

  /**
   * Get schedule
   * @return schedule
  **/
  @ApiModelProperty(value = "")


  public String getSchedule() {
    return schedule;
  }

  public void setSchedule(String schedule) {
    this.schedule = schedule;
  }

  public Reservation startTime(String startTime) {
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

  public Reservation endTime(String endTime) {
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

  public Reservation location(String location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Reservation status(StatusEnum status) {
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
    Reservation reservation = (Reservation) o;
    return Objects.equals(this.id, reservation.id) &&
        Objects.equals(this.attraction, reservation.attraction) &&
        Objects.equals(this.user, reservation.user) &&
        Objects.equals(this.schedule, reservation.schedule) &&
        Objects.equals(this.startTime, reservation.startTime) &&
        Objects.equals(this.endTime, reservation.endTime) &&
        Objects.equals(this.location, reservation.location) &&
        Objects.equals(this.status, reservation.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, attraction, user, schedule, startTime, endTime, location, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Reservation {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    attraction: ").append(toIndentedString(attraction)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    schedule: ").append(toIndentedString(schedule)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
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

