package com.iamdinuth.jobmicroservice.data.entity;

import com.iamdinuth.jobmicroservice.graphql.generated.types.Country;
import com.iamdinuth.jobmicroservice.graphql.generated.types.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.iamdinuth.jobmicroservice.graphql.generated.types.JobStatus;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private JobStatus jobStatus;

    @Column
    private String countryCode;
    @Column
    private UUID regionId;

    @Transient
    private Country country;
//    @Transient
//    private Region region;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="country_code")
//    @ToString.Exclude
//    private Country country;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="region_id")
//    @ToString.Exclude
//    private Region region;

}

