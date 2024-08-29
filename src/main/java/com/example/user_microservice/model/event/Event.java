package com.example.user_microservice.model.event;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.reference.RefCommonReference;
import com.example.user_microservice.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
public class Event extends BaseEntity {

    @ManyToOne
    @JoinTable(name = "owner_id")
    private User owner;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private RefCommonReference city;

    @Column(nullable = false)
    private String location;

    @Column(name = "max_attendees")
    private Integer maxAttendees;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
}
