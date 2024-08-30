package com.example.user_microservice.repository.event;

import com.example.user_microservice.model.event.Event;
import com.example.user_microservice.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, QuerydslPredicateExecutor<Event> {

    List<Event> findAllByOwner(User owner);

    @Query(nativeQuery = true, value = """
            SELECT (*) FROM event  e
            WHERE id in (SELECT event_id FROM m2m_user_event WHERE user_id = :member)
            """)
    List<Event> findAllByUsers(User member);
}
