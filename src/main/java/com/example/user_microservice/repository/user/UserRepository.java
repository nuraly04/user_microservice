package com.example.user_microservice.repository.user;

import com.example.user_microservice.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {

    @Query(nativeQuery = true, value = """
        SELECT (*) FROM users u
        JOIN m2m_mentorship men ON u.id = men.mentor_id
        WHERE men.mentor_id = :mentorId
        """)
    List<User> findMenteesByMentorId(Long mentorId);

    @Query(nativeQuery = true, value = """
        SELECT (*) FROM users u
        JOIN m2m_mentorship men ON u.id = men.mentee_id
        WHERE men.mentee_id = :menteeId
        """)
    List<User> findMentorsByMenteeId(Long menteeId);

    Long countByAuthors(User author);

    Long countByFollowers(User follower);

    boolean existsByAuthorsIdEqualsAndFollowersIdEquals(Long followerId, Long authorId);
}
