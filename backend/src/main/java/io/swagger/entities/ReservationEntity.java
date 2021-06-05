package io.swagger.entities;

import io.swagger.model.Attraction;
import io.swagger.model.Reservation;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class ReservationEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="reservationID")
	private String id;

	@OneToOne
	@JoinColumn(name = "attractionID")
	private AttractionEntity attraction;

	@OneToOne
	@JoinColumn(name = "guestAccount")
	private GuestEntity guest;

	@OneToOne
	@JoinColumn(name = "scheduleID")
	private ScheduleEntity schedule;

	@Column(name="status")
	private Status status;

	public String getId() {
		return id;
	}

	public ReservationEntity setId(String id) {
		this.id = id;
		return this;
	}

	public AttractionEntity getAttraction() {
		return attraction;
	}

	public ReservationEntity setAttraction(AttractionEntity attraction) {
		this.attraction = attraction;
		return this;
	}

	public GuestEntity getGuest() {
		return guest;
	}

	public ReservationEntity setGuest(GuestEntity guest) {
		this.guest = guest;
		return this;
	}

	public ScheduleEntity getSchedule() {
		return schedule;
	}

	public ReservationEntity setSchedule(ScheduleEntity schedule) {
		this.schedule = schedule;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public ReservationEntity setStatus(Status status) {
		this.status = status;
		return this;
	}

	public enum Status {
		waiting ("waiting"), passed ("passed"), cancelled ("cancelled"), confirmed ("confirmed"), checkingIn ("checking-in"), completed ("completed");

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

	public static Reservation toModel(ReservationEntity reservationEntity, AttractionEntity attractionEntity, ScheduleEntity scheduleEntity) {
		return new Reservation()
				.attraction(attractionEntity.getId())
				.endTime(scheduleEntity.getEndTime())
				.startTime(scheduleEntity.getStartTime())
				.id(reservationEntity.getId())
				.location(attractionEntity.getLocation())
				.schedule(scheduleEntity.getId())
				.status(Reservation.StatusEnum.fromValue(reservationEntity.getStatus().toString()))
				.user(reservationEntity.getGuest().getId());
	}
}
