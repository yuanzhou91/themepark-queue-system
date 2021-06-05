package io.swagger.repositories;

import io.swagger.entities.AttractionEntity;
import io.swagger.entities.GuestEntity;
import io.swagger.entities.ReservationEntity;
import io.swagger.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<ReservationEntity, String> {

	@Query("SELECT r FROM ReservationEntity r WHERE r.guest = ?1")
	public List<ReservationEntity> getReservationsByUser(GuestEntity guestEntity);

	@Query("SELECT r FROM ReservationEntity r WHERE r.guest = ?1 AND r.attraction = ?2")
	public ReservationEntity getReservationsByUserAndAttraction(GuestEntity guestEntity, AttractionEntity attractionEntity);

	@Query("SELECT r FROM ReservationEntity r WHERE r.schedule = ?1")
	public List<ReservationEntity> getReservationsFromSchedule(ScheduleEntity scheduleEntity);
}
