package com.datacenter.eud.course.course_spring.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "regions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegionEntity {

    @Id
    @Column(name = "region_id")
    private Long id;

    @Column(name = "region_name")
    private String name;
}
