package ru.hogwarts.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FacultyService facultyService;

    @MockBean
    private AvatarService avatarService;

    @Test
    void shouldGetFaculty() throws Exception {
        // given
        long facultyId = 1L;
        Faculty faculty = new Faculty("Gryffindor", "Red");

        when(facultyService.findFaculty(facultyId)).thenReturn(faculty);

        // when
        ResultActions perform = mockMvc.perform(get("/faculty/{id}", facultyId));

        // then
        perform
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.age").value(faculty.getColor()))
                .andDo(print());
    }

    @Test
    void shouldCreateFaculty() throws Exception {
        // given
        Long facultyId = 1L;
        Faculty faculty = new Faculty("Gryffindor", "Red");
        Faculty savedfaculty = new Faculty("Gryffindor", "Red");
        savedfaculty.setId(facultyId);

        when(facultyService.createFaculty(faculty)).thenReturn(savedfaculty);

        // when
        ResultActions perform = mockMvc.perform(post("/faculty")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faculty)));

        // then
        perform
                .andExpect(jsonPath("$.id").value(savedfaculty.getId()))
                .andExpect(jsonPath("$.name").value(savedfaculty.getName()))
                .andExpect(jsonPath("$.age").value(savedfaculty.getColor()))
                .andDo(print());
    }

    @Test
    void shouldUpdateFaculty() throws Exception {
        // given
        Long facultyId = 1L;
        Faculty faculty = new Faculty("Gryffindor", "Red");

        when(facultyService.editFaculty(faculty)).thenReturn(faculty);

        // when
        ResultActions perform = mockMvc.perform(put("/faculty/{id}", facultyId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faculty)));

        // then
        perform
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.age").value(faculty.getColor()))
                .andDo(print());
    }
}
