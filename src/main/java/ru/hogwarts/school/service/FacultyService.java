package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);

    Faculty getFaculty(long id);

    Faculty updateFaculty(Faculty faculty);

    Faculty removeFaculty(long id);

    ResponseEntity<Collection<Faculty>> getFaculties();

    ResponseEntity<Collection<Faculty>> getFacultiesForColor(String color);
}
