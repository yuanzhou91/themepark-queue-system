package io.swagger.repositories;

import io.swagger.entities.AttractionEntity;
import io.swagger.entities.GuestEntity;
import io.swagger.entities.QueueEntity;
import io.swagger.entities.ScheduleEntity;
import io.swagger.model.Queue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueRepository extends CrudRepository<QueueEntity, String> {

	@Query("SELECT s FROM QueueEntity s WHERE s.attraction=?1 AND (s.status = 0 OR s.status = 3) ORDER BY s.numberInLine ASC ")
	public List<QueueEntity> getQueuesOfAttraction(AttractionEntity attraction);

	@Query("SELECT s FROM QueueEntity s WHERE s.guest=?1")
	public QueueEntity getQueueOfUser(GuestEntity guest);


	@Query("SELECT s FROM QueueEntity s WHERE s.attraction=?1 AND s.status = 3")
	public QueueEntity getNextNumberCalled(AttractionEntity attraction);

	@Query("SELECT max(s.numberInLine) FROM QueueEntity s WHERE s.attraction=?1 AND (s.status = 0 OR s.status = 3)")
	public Integer getLargestNumberForQueue(AttractionEntity attraction);

	@Query("SELECT min(s.numberInLine) FROM QueueEntity s WHERE s.attraction=?1 AND (s.status = 0 OR s.status = 3)")
	public Integer getNextNumberToCall(AttractionEntity attraction);

	@Query("SELECT count(s) FROM QueueEntity s WHERE s.attraction=?1 AND (s.status = 0 OR s.status = 3)")
	public Integer getQueueSize(AttractionEntity attraction);

}
