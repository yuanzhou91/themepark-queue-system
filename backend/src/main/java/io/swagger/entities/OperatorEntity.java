package io.swagger.entities;

import io.swagger.model.CurrentUser;
import io.swagger.model.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operator")
public class OperatorEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="operatorID")
	private String id;

	@Column(name="operatorName")
	private String name;

	@Column(name="password")
	private String password;

	@Column(name="operatorEmail")
	private String email;

	@Column(name="operatorPhoneNo")
	private String phone;

	public String getId() {
		return id;
	}

	public OperatorEntity setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public OperatorEntity setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public OperatorEntity setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public OperatorEntity setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public OperatorEntity setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public static CurrentUser toModel(OperatorEntity entity) {
		return new CurrentUser()
				.email(entity.getEmail())
				.id(entity.getId())
				.phone(entity.getPhone())
				.password(entity.getPassword())
				.firstName(entity.getName().split(" ")[0])
				.lastName(entity.getName().split(" ").length > 1 ? entity.getName().split(" ")[1] : "");
	}

}
