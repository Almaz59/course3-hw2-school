package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService; // инжект сервиса с методами для возможности работы с ними

    public StudentController(StudentService studentService) { // инициализация сервиса
        this.studentService = studentService;
    }

    @GetMapping("{id}")// получение студента по id. id заключено в фигурные скобки т.к. в адресной строке будет вводиться номер id студента которого мы хотим получить, пример https://locslhost:8080/student/5
    public ResponseEntity<Student> getStudent(@PathVariable Long id) { // аннотация PathVariable используется для извлечения шаблонной части URI, представленной переменной {id}. ResponseEntity специальный класс который позволяет генерировать ответ который нам необходим   .
        Student student = studentService.getStudent(id); // с помощью метода getStudent класса studentService мы извлекаем по id студента из нашей мапы в studentService где храняться данные о всех студентах
        if (student == null) {
            return ResponseEntity.notFound().build(); // метод notFound возвращает ответ, что искомый объект не найден, а метод build собирает объект который в последствии выводится в окно браузера
        }
        return ResponseEntity.ok(student); // возвращает студента есть все ОК и он есть в списке
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudentsByAge(
            @RequestParam(value = "age", required = false) Integer age){
        if(age != null){
            return studentService.getStudentForAge(age);
        }
        return studentService.getStudents();

    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student student1 = studentService.updateStudent(student);
        if (student1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student1);
    }

    @DeleteMapping("{id}")
    public Student removeStudent(@PathVariable long id) {
        return studentService.removeStudent(id);
    }

}
