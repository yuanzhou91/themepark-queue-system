package io.swagger.entities;

import io.swagger.model.Attraction;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attraction")
public class AttractionEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="attractionID")
	private String id;

	@Column(name="attractionName")
	private String attractionName;

	@Column(name="capacity")
	private int capacity;

	@Column(name="physicalArea")
	private String location;

	@OneToOne
	@JoinColumn(name = "operatorID")
	private OperatorEntity operator;

	public String getId() {
		return id;
	}

	public AttractionEntity setId(String id) {
		this.id = id;
		return this;
	}

	public String getAttractionName() {
		return attractionName;
	}

	public AttractionEntity setAttractionName(String attractionName) {
		this.attractionName = attractionName;
		return this;
	}

	public int getCapacity() {
		return capacity;
	}

	public AttractionEntity setCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public AttractionEntity setLocation(String location) {
		this.location = location;
		return this;
	}

	public OperatorEntity getOperator() {
		return operator;
	}

	public AttractionEntity setOperator(OperatorEntity operator) {
		this.operator = operator;
		return this;
	}

//	public static AttractionEntity fromModel(Attraction attraction) {
//		return new AttractionEntity()
//				.setAttractionName(attraction.getName())
//				.setLocation(attraction.getLocation())
//
//	}
}

