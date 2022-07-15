package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    @Override
    public Faculty addFaculty(Faculty faculty) { // метод принимает из контроллера данные о факльтете
        faculty.setId(++lastId); // при добавлении нового факультета ему присваеивается свой id
        faculties.put(lastId, faculty); // кладем факультет (faculty) в нашу мапу (faculties) с уникальным id который был присвоен выше
        return faculty; // возвращаем факльтет который положили в мапу
    }

    @Override
    public Faculty getFaculty(long id) { // метод принимает введенный в контроллере id факультета
        return faculties.get(id); // возвращает данные о факультете по введенному id
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) { // метод принимает из контроллера данные о факультете
        faculties.put(faculty.getId(), faculty); // НЕ ПОНЯТНО КАК ЭТО РАБОТАЕТ, КАК ПРОИСХОДИТ РЕДАКТИРОВАНИЕ ФАКУЛЬТЕТА
        return faculty;
    }

    @Override
    public Faculty removeFaculty(long id) { // метод принимает введенный в контроллере id факультета
        return faculties.remove(id); // удаляет из мапы факультет по введенному id и возвращает данные удаленного факультета
    }

    @Override
    public ResponseEntity<Collection<Faculty>> getFaculties() {
        return ResponseEntity.ok(new ArrayList<>(faculties.values()));
    }

    @Override
    public ResponseEntity<Collection<Faculty>> getFacultiesForColor(String color) {
        List<Faculty> facultiesForColor = faculties.values().stream()
                .filter(v -> v.getColor().equals(color))
                .collect(Collectors.toList());
        return ResponseEntity.ok(facultiesForColor);
    }

}

