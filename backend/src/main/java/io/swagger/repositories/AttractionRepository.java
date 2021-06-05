package io.swagger.repositories;

import io.swagger.entities.AttractionEntity;
import io.swagger.entities.GuestEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionRepository extends CrudRepository<AttractionEntity, String> {

	@Query("SELECT a FROM AttractionEntity a ")
	public List<AttractionEntity> getAllAttractions();

}
