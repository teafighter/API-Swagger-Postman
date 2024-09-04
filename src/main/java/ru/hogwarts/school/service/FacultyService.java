package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {

    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Faculty " + faculty.getName() + " was created");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        if (id < 0) {
            logger.warn("Try again with positive id");
        }
        logger.info("Faculty " + facultyRepository.findById(id).get() + " exists");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Faculty " + faculty.getName() + " was updated");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        if (id < 0) {
            logger.warn("Try again with positive id");
        }
        logger.info("Faculty " + facultyRepository.findById(id).get() + " was deleted");
        facultyRepository.deleteById(id);
    }

    public ArrayList<Faculty> findByColor(String color) {
        logger.info("Found " + facultyRepository.findByColor(color).size() + " faculties with " + color + " color");
        return facultyRepository.findByColor(color);
    }

    public Faculty findByColorOrNameIgnoreCase(String color, String name) {
        logger.info("Found something");
        return facultyRepository.findByColorOrNameIgnoreCase(color, name);
    }
}
