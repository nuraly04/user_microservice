package com.example.user_microservice.controller.mentorship;

import com.example.user_microservice.dto.mentorship.MentorshipFilterDto;
import com.example.user_microservice.dto.mentorship.MentorshipRequestAddDto;
import com.example.user_microservice.dto.mentorship.MentorshipRequestDto;
import com.example.user_microservice.dto.user.RemoveUsers;
import com.example.user_microservice.dto.user.UserDto;
import com.example.user_microservice.manager.mentorship.MentorshipManager;
import com.example.user_microservice.utils.Paths;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Paths.RECOMMENDATION)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MentorshipController {

    MentorshipManager mentorshipManager;

    @PostMapping("/{menteeId}")
    public void requestMentorship(
            @PathVariable("menteeId") Long menteeId,
            @RequestBody @Valid MentorshipRequestAddDto requestDto
    ) {
        mentorshipManager.requestMentorship(menteeId, requestDto);
    }

    @GetMapping("/{mentorId}/requests")
    public List<MentorshipRequestDto> getRequests(
            @PathVariable("mentorId") Long mentorId,
            @RequestBody MentorshipFilterDto filterDto) {
        return mentorshipManager.getRequests(mentorId, filterDto);
    }

    @PutMapping("/{mentorId}/accept/{requestId}")
    public void acceptRequest(
            @PathVariable("mentorId") Long mentorId,
            @PathVariable("requestId") Long requestId
    ) {
        mentorshipManager.acceptRequest(mentorId, requestId);
    }

    @PutMapping("/{mentorId}/reject/{requestId}")
    public void rejectRequest(
            @PathVariable("mentorId") Long mentorId,
            @PathVariable("requestId") Long requestId
    ) {
        mentorshipManager.rejectRequest(mentorId, requestId);
    }

    @GetMapping("/{mentorId}/mentees")
    public List<UserDto> getMentees(@PathVariable("mentorId") Long mentorId) {
        return mentorshipManager.getMentees(mentorId);
    }

    @GetMapping("/{menteeId}/mentors")
    public List<UserDto> getMentors(@PathVariable("menteeId") Long menteeId) {
        return mentorshipManager.getMentors(menteeId);
    }

    @PutMapping("/{mentorId}/mentees")
    public void deleteMentee(
            @PathVariable("mentorId") Long mentorId,
            @RequestBody RemoveUsers users
    ) {
        mentorshipManager.deleteMentee(mentorId, users);
    }

    @PutMapping("/{menteeId}/mentors")
    public void deleteMentor(
            @PathVariable("menteeId") Long menteeId,
            @RequestBody RemoveUsers users
    ) {
        mentorshipManager.deleteMentor(menteeId, users);
    }
}
