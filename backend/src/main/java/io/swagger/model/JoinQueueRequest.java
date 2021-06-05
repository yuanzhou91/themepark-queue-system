package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * JoinQueueRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-05-21T06:16:38.139Z")


public class JoinQueueRequest   {
  @JsonProperty("attraction")
  private String attraction = null;

  @JsonProperty("users")
  @Valid
  private List<String> users = null;

  public JoinQueueRequest attraction(String attraction) {
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

  public JoinQueueRequest users(List<String> users) {
    this.users = users;
    return this;
  }

  public JoinQueueRequest addUsersItem(String usersItem) {
    if (this.users == null) {
      this.users = new ArrayList<String>();
    }
    this.users.add(usersItem);
    return this;
  }

  /**
   * Get users
   * @return users
  **/
  @ApiModelProperty(value = "")


  public List<String> getUsers() {
    return users;
  }

  public void setUsers(List<String> users) {
    this.users = users;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JoinQueueRequest joinQueueRequest = (JoinQueueRequest) o;
    return Objects.equals(this.attraction, joinQueueRequest.attraction) &&
        Objects.equals(this.users, joinQueueRequest.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attraction, users);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JoinQueueRequest {\n");
    
    sb.append("    attraction: ").append(toIndentedString(attraction)).append("\n");
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
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

