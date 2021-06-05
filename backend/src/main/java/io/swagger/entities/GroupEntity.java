package io.swagger.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "guestGroup")
public class GroupEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "owner")
	private GuestEntity owner;

	@OneToOne
	@JoinColumn(name = "member")
	private GuestEntity member;

	public Integer getId() {
		return id;
	}

	public GroupEntity setId(Integer id) {
		this.id = id;
		return this;
	}

	public GuestEntity getOwner() {
		return owner;
	}

	public GroupEntity setOwner(GuestEntity owner) {
		this.owner = owner;
		return this;
	}

	public GuestEntity getMember() {
		return member;
	}

	public GroupEntity setMember(GuestEntity member) {
		this.member = member;
		return this;
	}
}
