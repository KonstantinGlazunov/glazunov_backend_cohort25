package de.ait.task_05.controllers;

import de.ait.task_05.security.config.TestSecurityConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestSecurityConfig.class)
@AutoConfigureMockMvc
@DisplayName("Endpoint /events is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class EventsIntegrationTests {
    @Autowired
    private MockMvc mockMvc;


    @Nested
    @DisplayName("POST /events:")
    public class PostCourse {

        @WithUserDetails(value = "user")
        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_created_event() throws Exception {
            mockMvc.perform(post("/api/events")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\n" +
                                    "  \"title\": \"New Event\",\n" +
                                    "  \"date\": \"2022-02-02\"\n" +
                                    "}"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(5)));
        }
        @WithUserDetails(value = "user")
        @Test
        public void return_400_for_not_valid_event() throws Exception {
            mockMvc.perform(post("/api/events")
                            .contentType("application/json")
                            .content("{\n" +
                                    "  \"title\": \"New Event\",\n" +
                                    "  \"date\": \"2022-00-00\"\n" +
                                    "}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.errors.size()", is(2)));
        }
        @WithUserDetails(value = "user")
        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_404_for_not_existed_course() throws Exception {
            mockMvc.perform(get("/api/events/5555/participants"))
                    .andExpect(status().isNotFound());
    }
}}
