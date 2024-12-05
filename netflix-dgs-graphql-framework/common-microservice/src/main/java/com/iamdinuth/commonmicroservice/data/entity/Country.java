package com.iamdinuth.commonmicroservice.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    //what the region breakdown is called in the country. Eg, state, district, province etc.
    //if null, no regions
    @Column(columnDefinition = "TEXT")
    private String regionName;

    @ToString.Exclude
    @OneToMany(mappedBy = "country")
    private List<Region> regions;

    @Id
    @Column(columnDefinition = "TEXT")
    private String code;

    @Transient
    private String president;
}
