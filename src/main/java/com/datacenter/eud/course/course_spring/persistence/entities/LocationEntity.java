package com.datacenter.eud.course.course_spring.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "LOCATIONS")
@Data
@Builder
public class LocationEntity {
	
	@Id
	@Column(name = "LOCATION_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LocationEntityGenerator")
	@SequenceGenerator(sequenceName = "LOCATIONS_SEQ", name = "LocationEntityGenerator", allocationSize = 1, initialValue = 1) // Los dos últimos parámetros se deben poner para el cahé  de la secuencia
	private Long id;
	
	@Column(name = "STREET_ADDRESS")
	private String streetAddress;
	
	@Column(name = "POSTAL_CODE")
	private String postalCode;
	
	@Column()
	private String city;
	
	@Column(name = "STATE_PROVINCE")
	private String stateProvince;
	
	@Column(name = "COUNTRY_ID")
	private String countryId;
}
