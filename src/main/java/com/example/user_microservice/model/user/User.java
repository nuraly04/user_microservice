package com.example.user_microservice.model.user;

import com.example.user_microservice.model.base.BaseEntity;
import com.example.user_microservice.model.contact.Contact;
import com.example.user_microservice.model.event.Event;
import com.example.user_microservice.model.reference.RefCommonReference;
import com.example.user_microservice.model.skill.Skill;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "surname", nullable = false, length = 64)
    private String surname;

    @Column(name = "patronymic", nullable = false, length = 64)
    private String patronymic;

    private String about;

    @ManyToOne
    @JoinColumn(name = "city")
    private RefCommonReference city;

    @ManyToMany(mappedBy = "users")
    private List<Skill> skills;

    @ManyToMany(mappedBy = "mentors", cascade = CascadeType.ALL)
    private List<User> mentees;

    @ManyToMany
    @JoinTable(name = "m2m_mentorship",
    joinColumns = @JoinColumn(name = "mentee_id"),
    inverseJoinColumns = @JoinColumn(name = "mentor_id"))
    private List<User> mentors;

    @ManyToMany(mappedBy = "followers")
    private List<User> authors;

    @ManyToMany
    @JoinTable(name = "m2m_subscription",
    joinColumns = @JoinColumn(name = "author_id"),
    inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private List<User> followers;

    @ManyToMany
    @JoinTable(name = "m2m_user_event",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> events;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    @Email
    @Column(name = "email", unique = true, nullable = false, length = 128)
    private String email;

    @Column(name = "phone", unique = true, nullable = false, length = 64)
    private String phone;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
