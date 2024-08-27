package com.example.user_microservice.controller.mentorship;

import com.example.user_microservice.dto.mentorship.UserDto;
import com.example.user_microservice.manager.mentorship.MentorshipManager;
import com.example.user_microservice.utils.Paths;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.RECOMMENDATION)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MentorshipController {

    MentorshipManager mentorshipManager;

    @GetMapping("/mentees/{mentorId}")
    public List<UserDto> getMentees(@PathVariable("mentorId") Long mentorId) {
        return mentorshipManager.getMentees(mentorId);
    }

    @GetMapping("/mentors/{menteeId}")
    public List<UserDto> getMentors(@PathVariable("menteeId") Long menteeId) {
        return mentorshipManager.getMentors(menteeId);
    }

    @PutMapping("/{mentorId}/{menteeId}")
    public void deleteMentee(@PathVariable("mentorId") Long mentorId,
                             @PathVariable("menteeId") Long menteeId) {
        mentorshipManager.deleteMentee(mentorId, menteeId);
    }
}
