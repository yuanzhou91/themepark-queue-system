package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * Queue
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-05-21T06:16:38.139Z")


public class Queue   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("numberInQueue")
  private Integer numberInQueue = null;

  @JsonProperty("nextNumberToCall")
  private Integer nextNumberToCall = null;

  @JsonProperty("attraction")
  private String attraction = null;

  @JsonProperty("user")
  private String user = null;

  @JsonProperty("location")
  private String location = null;

  @JsonProperty("eta")
  private String eta = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    WAITING("waiting"),
    
    PASSED("passed"),
    
    CALLED("called"),
    
    CONFIRMED("confirmed");

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

  public Queue id(String id) {
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

  public Queue numberInQueue(Integer numberInQueue) {
    this.numberInQueue = numberInQueue;
    return this;
  }

  /**
   * Get numberInQueue
   * @return numberInQueue
  **/
  @ApiModelProperty(value = "")


  public Integer getNumberInQueue() {
    return numberInQueue;
  }

  public void setNumberInQueue(Integer numberInQueue) {
    this.numberInQueue = numberInQueue;
  }

  public Queue nextNumberToCall(Integer nextNumberToCall) {
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

  public Queue attraction(String attraction) {
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

  public Queue user(String user) {
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

  public Queue location(String location) {
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

  public Queue eta(String eta) {
    this.eta = eta;
    return this;
  }

  /**
   * Get eta
   * @return eta
  **/
  @ApiModelProperty(value = "")

  @Valid

  public String getEta() {
    return eta;
  }

  public void setEta(String eta) {
    this.eta = eta;
  }

  public Queue status(StatusEnum status) {
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
    Queue queue = (Queue) o;
    return Objects.equals(this.id, queue.id) &&
        Objects.equals(this.numberInQueue, queue.numberInQueue) &&
        Objects.equals(this.nextNumberToCall, queue.nextNumberToCall) &&
        Objects.equals(this.attraction, queue.attraction) &&
        Objects.equals(this.user, queue.user) &&
        Objects.equals(this.location, queue.location) &&
        Objects.equals(this.eta, queue.eta) &&
        Objects.equals(this.status, queue.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, numberInQueue, nextNumberToCall, attraction, user, location, eta, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Queue {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    numberInQueue: ").append(toIndentedString(numberInQueue)).append("\n");
    sb.append("    nextNumberToCall: ").append(toIndentedString(nextNumberToCall)).append("\n");
    sb.append("    attraction: ").append(toIndentedString(attraction)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    eta: ").append(toIndentedString(eta)).append("\n");
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

