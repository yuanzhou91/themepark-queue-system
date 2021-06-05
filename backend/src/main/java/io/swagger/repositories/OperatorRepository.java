package io.swagger.repositories;

import io.swagger.entities.OperatorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends CrudRepository<OperatorEntity, String> {

	@Query("SELECT u FROM OperatorEntity u WHERE u.email = ?1 and u.password = ?2")
	public OperatorEntity findOperatorEntityBy(String email, String password);

}
