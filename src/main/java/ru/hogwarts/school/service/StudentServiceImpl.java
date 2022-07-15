package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    @Override
    public Student addStudent(Student student) { // метод принимает из контроллера данные о студенте
        student.setId(++lastId); // при добавлении нового студента ему присваеивается свой id
        students.put(lastId, student); // кладем студента (student) в нашу мапу (students) с уникальным id который был присвоен выше
        return student; // возвращаем студента которого положили в мапу
    }

    @Override
    public Student getStudent(long id) { // метод принимает введенный в контроллере id студента
        return students.get(id); // возвращает данные о студенте по введенному id
    }

    @Override
    public Student updateStudent(Student student) { // метод принимает из контроллера данные о студенте
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student); // НЕ ПОНЯТНО КАК ЭТО РАБОТАЕТ, КАК ПРОИСХОДИТ РЕДАКТИРОВАНИЕ СТУДЕНТА
            return student;
        }
        return null;
    }

    @Override
    public Student removeStudent(long id) { // метод принимает введенный в контроллере id студента
        return students.remove(id); // удаляет из мапы студента по введенному id и возвращает данные удаленного студента
    }

    @Override
    public ResponseEntity<Collection<Student>> getStudentForAge(int age) {
        Collection<Student> studentsByAge = students.values().stream()
                .filter(v -> v.getAge() == age)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentsByAge);
    }

    @Override
    public ResponseEntity<Collection<Student>> getStudents() {
        return ResponseEntity.ok(new ArrayList<>(students.values()));
    }
}
