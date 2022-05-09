package com.metadata.registration.service;

import com.metadata.registration.api.dto.*;

import java.util.List;

public interface RegistrationService {

    /**
     * Register a student to a course.
     * Restrictions apply to number of students per course and number of courses per student
     * @param registrationRequestDto student and course data to be bound
     */
    void registerToCourse(RegistrationRequestDto registrationRequestDto);
}
