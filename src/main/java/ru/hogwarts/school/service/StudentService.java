package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private final Map<Long, Student> facultyStudents = new HashMap<>();
    private Long studentID;

    public Student createStudent(Student student) {
        student.setId(studentID++);
        facultyStudents.put(student.getId(), student);
        return student;
    }

    public Student findStudent(long id) {
        return facultyStudents.get(id);
    }

    public Student editStudent(Student student) {
        if (!facultyStudents.containsKey(student.getId())) {
            return null;
        }
        facultyStudents.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(long id) {
        return facultyStudents.remove(id);
    }

    public Collection<Student> findByAge(int age) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : facultyStudents.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}
