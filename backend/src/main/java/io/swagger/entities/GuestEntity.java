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
@Table(name = "guest")
public class GuestEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="guestAccount")
	private String guestAccount;

	@Column(name="guestName")
	private String name;

	@Column(name="password")
	private String password;

	@Column(name="guestEmail")
	private String email;

	@Column(name="guestPhoneNumber")
	private String phone;

	public String getId() {
		return guestAccount;
	}

	public GuestEntity setId(String id) {
		this.guestAccount = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public GuestEntity setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public GuestEntity setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public GuestEntity setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public GuestEntity setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public static User toModel(GuestEntity entity) {
		return new User()
				.email(entity.getEmail())
				.id(entity.getId())
				.phone(entity.getPhone())
				.password(entity.getPassword())
				.firstName(entity.getName().split(" ")[0])
				.lastName(entity.getName().split(" ").length > 1 ? entity.getName().split(" ")[1] : "");
	}

	public static CurrentUser toCurrentUser(GuestEntity entity) {
		return new CurrentUser()
				.email(entity.getEmail())
				.id(entity.getId())
				.phone(entity.getPhone())
				.password(entity.getPassword())
				.firstName(entity.getName().split(" ")[0])
				.lastName(entity.getName().split(" ").length > 1 ? entity.getName().split(" ")[1] : "");
	}

}
