package com.example.user_microservice.model.event;

import com.example.user_microservice.model.base.DeletedEntity;
import com.example.user_microservice.model.reference.RefCommonReference;
import com.example.user_microservice.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event extends DeletedEntity {

    @ManyToOne
    @JoinTable(name = "owner_id")
    private User owner;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private RefCommonReference city;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "event")
    private List<EventSkill> skills;

    @Column(name = "max_attendees")
    private Long maxAttendees;

    @ManyToMany(mappedBy = "events"                               )
    private List<User> users;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
}
