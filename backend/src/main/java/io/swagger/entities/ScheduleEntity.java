package io.swagger.entities;

import io.swagger.model.Schedule;
import org.hibernate.annotations.GenericGenerator;
import org.threeten.bp.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="scheduleID")
	private String id;

	@Column(name="startTime")
	private String startTime;

	@Column(name="endTime")
	private String endTime;

	@Column(name="physicalArea")
	private String location;

	@OneToOne
	@JoinColumn(name = "operatorID")
	private OperatorEntity operator;

	@Column(name="totalCapacity")
	private int totalCapacity;

	@Column(name="availableCapacity")
	private int availableCapacity;

	@Column(name="reservedForQueue")
	private int reservedForQueue;

	@OneToOne
	@JoinColumn(name = "attractionID")
	private AttractionEntity attraction;

	@Column(name="status")
	private Status status;

	public String getId() {
		return id;
	}

	public ScheduleEntity setId(String id) {
		this.id = id;
		return this;
	}

	public String getStartTime() {
		return startTime;
	}

	public ScheduleEntity setStartTime(String startTime) {
		this.startTime = startTime;
		return this;
	}

	public String getEndTime() {
		return endTime;
	}

	public ScheduleEntity setEndTime(String endTime) {
		this.endTime = endTime;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public ScheduleEntity setLocation(String location) {
		this.location = location;
		return this;
	}

	public OperatorEntity getOperator() {
		return operator;
	}

	public ScheduleEntity setOperator(OperatorEntity operator) {
		this.operator = operator;
		return this;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public ScheduleEntity setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
		return this;
	}

	public int getAvailableCapacity() {
		return availableCapacity;
	}

	public ScheduleEntity setAvailableCapacity(int availableCapacity) {
		this.availableCapacity = availableCapacity;
		return this;
	}

	public AttractionEntity getAttraction() {
		return attraction;
	}

	public ScheduleEntity setAttraction(AttractionEntity attraction) {
		this.attraction = attraction;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public ScheduleEntity setStatus(Status status) {
		this.status = status;
		return this;
	}

	public int getReservedForQueue() {
		return reservedForQueue;
	}

	public ScheduleEntity setReservedForQueue(int reservedForQueue) {
		this.reservedForQueue = reservedForQueue;
		return this;
	}

	public enum Status {
		upcoming ("upcoming"), postCheckin ("post-checkin"), cancelled ("cancelled"), completed ("completed"), checkingIn ("checking-in");

		private final String name;

		Status(String s) {
			name = s;
		}

		public boolean equalsName(String otherName) {
			// (otherName == null) check is not needed because name.equals(null) returns false
			return name.equals(otherName);
		}

		public static Status fromValue(String text) {
			for (Status b : Status.values()) {
				if (String.valueOf(b.name).equals(text)) {
					return b;
				}
			}
			return null;
		}

		public String toString() {
			return this.name;
		}
	}

	public static ScheduleEntity fromModel(Schedule schedule) {
		return new ScheduleEntity()
				.setAttraction(new AttractionEntity().setId(schedule.getAttraction()))
				.setAvailableCapacity(schedule.getAvailableSeats())
				.setEndTime(schedule.getEndTime())
				.setStartTime(schedule.getStartTime())
				.setReservedForQueue(schedule.getReservedForQueue())
				.setTotalCapacity(schedule.getTotalSeats())
				.setStatus(Status.fromValue(schedule.getStatus().toString()));
	}

	public static Schedule toModel(ScheduleEntity entity) {
		return new Schedule()
				.attraction(entity.getAttraction().getId())
				.id(entity.getId())
				.availableSeats(entity.getAvailableCapacity())
				.totalSeats(entity.getTotalCapacity())
				.status(Schedule.StatusEnum.fromValue(entity.getStatus().toString()))
				.reservedForQueue(entity.getReservedForQueue())
				.endTime(entity.getEndTime())
				.startTime(entity.getStartTime());
	}

}
