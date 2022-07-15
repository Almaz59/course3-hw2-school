package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentService {
    Student addStudent(Student student);

    Student getStudent(long id);

    Student updateStudent(Student student);

    Student removeStudent(long id);

    ResponseEntity<Collection<Student>> getStudentForAge(int age);

    ResponseEntity<Collection<Student>> getStudents();
}
