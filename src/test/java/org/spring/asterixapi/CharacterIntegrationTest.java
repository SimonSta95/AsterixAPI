package org.spring.asterixapi;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class CharacterIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CharacterRepo characterRepo;

    @Test
    public void getAllCharacters() throws Exception {

        //GIVEN
        characterRepo.save(new Character("1", "Asterix", 35, "Krieger"));
        characterRepo.save(new Character("2", "Obelix", 35, "Lieferant"));

        //WHEN
        mockMvc.perform(get("/api/asterix/characters"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                        [
                          {
                            "id": "1",
                            "name": "Asterix",
                            "age": 35,
                            "profession": "Krieger"
                          },
                          {
                            "id": "2",
                            "name": "Obelix",
                            "age": 35,
                            "profession": "Lieferant"
                          }
                        ]
                        """
                ));
    }

    @Test
    @DirtiesContext
    public void postCharacter() throws Exception {

        mockMvc.perform(post("/api/asterix/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                          {
                            "name": "Asterix",
                            "age": 35,
                            "profession": "Krieger"
                          }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                      {
                                        "name": "Asterix",
                                        "age": 35,
                                        "profession": "Krieger"
                                      }
                                """
                ))
                .andExpect(jsonPath("$.id").exists());

    }

    @Test
    @DirtiesContext
    public void updateCharacter() throws Exception {
        //GIVEN
        characterRepo.save(new Character("1", "Asterix", 35, "Krieger"));

        //WHEN
        mockMvc.perform(put("/api/asterix/characters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                                {
                                "id": "1",
                                "name": "Test",
                                "age": 35,
                                "profession": "Krieger"
                                }
                        """
                ))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                {
                                    "id":"1",
                                    "name":"Test",
                                    "age":35,
                                    "profession":"Krieger"
                                }
                                """
                ));

    }

    @Test
    public void deleteCharacter() throws Exception {
        characterRepo.save(new Character("1", "Asterix", 35, "Krieger"));

        mockMvc.perform(delete("/api/asterix/characters/1"))
                .andExpect(status().isOk());
    }
}
