package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private long studentID;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student readStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public ArrayList<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }


    public ArrayList<Student> findByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Integer getStudentsAmount() {
        return studentRepository.getStudentsAmount();
    }

    public Integer getAverageStudentsAge() {
        return studentRepository.getAverageStudentsAge();
    }

    public ArrayList<Student> findLastFiveStudents() {
        return studentRepository.findLastFiveStudents();
    }
}
