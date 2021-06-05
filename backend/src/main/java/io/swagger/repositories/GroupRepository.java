package io.swagger.repositories;

import io.swagger.entities.GroupEntity;
import io.swagger.entities.GuestEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, String> {

	@Query("SELECT u FROM GroupEntity u WHERE u.owner = ?1")
	public List<GroupEntity> getGroupByUser(GuestEntity guest);

	@Query("SELECT u FROM GroupEntity u WHERE u.owner = ?1 AND u.member = ?2")
	public GroupEntity getGroupByUser(GuestEntity owner, GuestEntity member);

}
