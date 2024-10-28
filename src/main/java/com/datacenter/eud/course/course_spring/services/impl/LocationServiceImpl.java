package com.datacenter.eud.course.course_spring.services.impl;

import org.springframework.stereotype.Service;

import com.datacenter.eud.course.course_spring.dto.LocationDTO;
import com.datacenter.eud.course.course_spring.persistence.entities.CountryEntity;
import com.datacenter.eud.course.course_spring.persistence.entities.LocationEntity;
import com.datacenter.eud.course.course_spring.persistence.repositories.CountryRepository;
import com.datacenter.eud.course.course_spring.persistence.repositories.LocationRepository;
import com.datacenter.eud.course.course_spring.services.LocationService;
import com.datacenter.eud.course.course_spring.util.exception.DatoNoExisteException;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

	@NonNull
	private LocationRepository locationRepository;
	@NonNull
	private CountryRepository countryRepository;

	/*
	 * @Override public Long create(LocationDTO dto) {
	 * 
	 * LocationEntity entity = LocationEntity.builder() .city(dto.getCity())
	 * .countryId(dto.getCountryId()) .postalCode(dto.getPostalCode())
	 * .stateProvince(dto.getStateProvince()) .streetAddress(dto.getStreetAddress())
	 * .build();
	 * 
	 * entity = this.locationRepository.save(entity);
	 * 
	 * return entity.getId(); }
	 */

	@Override
	public Long create(LocationDTO dto) {

		Iterable<CountryEntity> countries = this.countryRepository.findAll();

		String countryId = null;

		for (CountryEntity c : countries) {
			if (c.getName().equals(dto.getCountryName())) {
				countryId = c.getId();
				break;
			}
		}

		if (countryId != null) {
			LocationEntity entity = LocationEntity.builder().city(dto.getCity()).countryId(countryId)
					.postalCode(dto.getPostalCode()).stateProvince(dto.getStateProvince())
					.streetAddress(dto.getStreetAddress()).build();

			entity = this.locationRepository.save(entity);

			return entity.getId();
		} else {
			throw new DatoNoExisteException("Country con nombre " + dto.getCountryName() + " NO existe");
		}
	}

}
