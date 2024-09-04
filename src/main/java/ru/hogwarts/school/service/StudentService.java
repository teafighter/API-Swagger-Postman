package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    private long studentID;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Student" + student.getName() + "was created");
        return studentRepository.save(student);
    }

    public Student readStudent(long id) {
        logger.info("Student" + studentRepository.findById(id).get().getName() + " is found");
        return studentRepository.findById(id).get();
    }

    public Student updateStudent(Student student) {
        logger.info("Student" + student.getName() + "was updated");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Student" + studentRepository.findById(id).get().getName() + "is deleted");
        studentRepository.deleteById(id);
    }

    public ArrayList<Student> findByAge(int age) {
        if (age < 11) {
            logger.warn("There is no such young students");
        }
        logger.info("Found " + studentRepository.findByAge(age).size() + " students at age of " + age);
        return studentRepository.findByAge(age);
    }


    public ArrayList<Student> findByAgeBetween(int minAge, int maxAge) {
        logger.info("Found " + studentRepository.findByAgeBetween(minAge, maxAge).size() + " students at age between " + minAge + " and " + maxAge);
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Integer getStudentsAmount() {
        logger.info("We have " + studentRepository.getStudentsAmount() + " students overall");
        return studentRepository.getStudentsAmount();
    }

    public Integer getAverageStudentsAge() {
        logger.info("Average student age is " + studentRepository.getAverageStudentsAge() + " years");
        return studentRepository.getAverageStudentsAge();
    }

    public ArrayList<Student> findLastFiveStudents() {
        logger.info("We have at least five students. Yay!");
        return studentRepository.findLastFiveStudents();
    }
}
