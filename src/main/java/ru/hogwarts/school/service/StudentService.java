package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> findAllNamesStartsWithA() {
        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
    }

    public Double getAverageStudentsAgeInStream() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }

    public void printAllStudentsParallel() {
        List<String> names = studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .toList();
        System.out.println(names.get(0));
        System.out.println(names.get(1));
        new Thread(() -> {
            System.out.println(names.get(2));
            System.out.println(names.get(3));
        }).start();
        new Thread(() -> {
                System.out.println(names.get(4));
                System.out.println(names.get(5));
        }).start();
    }

    private synchronized void printNameSynchronized(String name) {
        System.out.println(name);
    }

    public void printAllStudentsSynchronized() {
        List<String> names = studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .toList();
        printNameSynchronized(names.get(0));
        printNameSynchronized(names.get(1));

        new Thread(() -> {
            printNameSynchronized(names.get(2));
            printNameSynchronized(names.get(3));
        }).start();

        new Thread(() -> {
            printNameSynchronized(names.get(4));
            printNameSynchronized(names.get(5));
        }).start();
    }
}