package com.example.user_microservice.utils;

public class Paths {

    private static final String API_V1 = "/api/v1";

    public static final String USER = API_V1 + "/users";
    public static final String SKILL = API_V1 + "/skills";
    public static final String RECOMMENDATION = API_V1 + "/recommendations";
    public static final String MENTORSHIP = API_V1 + "/mentorships";
    public static final String SUBSCRIPTION = API_V1 + "/subscriptions";

    // EVENT
    public static final String EVENT = API_V1 + "/events";
    public static final String EVENT_PARTICIPATION = API_V1 + EVENT + "/participation";

    // Goal
    public static final String GOAL = API_V1 + "/{userId}/goals";
    public static final String GOAL_INVITATION = API_V1 + GOAL + "/{goalId}/invitations";
}
