package io.swagger.repositories;

import io.swagger.entities.AttractionEntity;
import io.swagger.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends CrudRepository<ScheduleEntity, String> {

	@Query("SELECT s FROM ScheduleEntity s WHERE s.attraction=?1 ORDER BY s.startTime ASC")
	public List<ScheduleEntity> getSchedules(AttractionEntity attraction);

	@Query("SELECT s FROM ScheduleEntity s WHERE s.attraction=?1 AND (s.status = 4 OR s.status = 1)")
	public ScheduleEntity getUpcomingSchedule(AttractionEntity attraction);

}
