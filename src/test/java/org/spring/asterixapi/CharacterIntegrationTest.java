package org.spring.asterixapi;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CharacterIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CharacterRepo characterRepo;

    @Test
    public void getAllCharacters() throws Exception {

        characterRepo.save(new Character("1", "Asterix", 35, "Krieger"));
        characterRepo.save(new Character("2", "Obelix", 35, "Lieferant"));

        mockMvc.perform(get("/api/asterix/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
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
                        """));
    }
}
