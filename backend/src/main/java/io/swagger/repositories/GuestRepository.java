package io.swagger.repositories;

import io.swagger.entities.GuestEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<GuestEntity, String> {

	@Query("SELECT u FROM GuestEntity u WHERE u.email = ?1 and u.password = ?2")
	public GuestEntity findUserByEmailAndPassword(String email, String password);

	@Query("SELECT u FROM GuestEntity u WHERE u.email = ?1")
	public GuestEntity findUserByEmail(String email);

}
