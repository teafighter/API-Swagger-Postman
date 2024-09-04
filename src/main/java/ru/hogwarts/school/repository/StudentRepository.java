package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;

public interface StudentRepository extends JpaRepository<Student, Long> {
    ArrayList<Student> findByAge(int age);

    ArrayList<Student> findByAgeBetween(int minAge, int maxAge);

    // - Возможность получить количество всех студентов в школе. Эндпоинт должен вернуть число.
    @Query(value = "SELECT COUNT(*) FROM Student")
    Integer getStudentsAmount();

    //- Возможность получить средний возраст студентов. Эндпоинт должен вернуть число.
    @Query(value = "SELECT AVG(age) FROM Student")
    Integer getAverageStudentsAge();

    //- Возможность получать только пять последних студентов. Последние студенты считаются теми, у кого идентификатор больше других.
    @Query(value = "SELECT * FROM Student ORDER BY id  desc LIMIT 5", nativeQuery = true)
    ArrayList<Student> findLastFiveStudents();
}
