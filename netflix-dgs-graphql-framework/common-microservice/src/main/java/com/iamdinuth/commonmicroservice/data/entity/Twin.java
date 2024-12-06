package com.iamdinuth.commonmicroservice.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "twins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Twin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID twinId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String twinName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = true)
    private Double latitude;

    @Column(nullable = true)
    private Double longitude;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String uri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = true)
    private Client client;
}
