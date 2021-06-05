package io.swagger.entities;

import io.swagger.model.Queue;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "guestWaitInAttraction")
public class QueueEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private Integer id;

	@OneToOne
	@JoinColumn(name="attractionID")
	private AttractionEntity attraction;

	@OneToOne
	@JoinColumn(name = "guestAccount")
	private GuestEntity guest;

	@Column(name="numberInLine")
	private int numberInLine;

	@Column(name="status")
	private Status status;

	public AttractionEntity getAttraction() {
		return attraction;
	}

	public QueueEntity setAttraction(AttractionEntity attraction) {
		this.attraction = attraction;
		return this;
	}

	public GuestEntity getGuest() {
		return guest;
	}

	public QueueEntity setGuest(GuestEntity guest) {
		this.guest = guest;
		return this;
	}

	public int getNumberInLine() {
		return numberInLine;
	}

	public QueueEntity setNumberInLine(int numberInLine) {
		this.numberInLine = numberInLine;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public QueueEntity setStatus(Status status) {
		this.status = status;
		return this;
	}

	public Integer getId() {
		return id;
	}

	public QueueEntity setId(Integer id) {
		this.id = id;
		return this;
	}

	public enum Status {
		waiting ("waiting"), passed ("passed"), cancelled ("cancelled"), called ("called");

		private final String name;

		Status(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			// (otherName == null) check is not needed because name.equals(null) returns false
			return name.equals(otherName);
		}

		public String toString() {
			return this.name;
		}
	}

	public static Queue toModel(QueueEntity entity, int nextNumberToCall) {
		return new Queue()
				.nextNumberToCall(nextNumberToCall)
				.status(Queue.StatusEnum.fromValue(entity.getStatus().toString()))
				.numberInQueue(entity.getNumberInLine())
				.user(entity.getGuest().getId())
				.attraction(entity.getAttraction().getId())
				.location(entity.getAttraction().getLocation())
				.id(String.valueOf(entity.getId()));
	}
}
