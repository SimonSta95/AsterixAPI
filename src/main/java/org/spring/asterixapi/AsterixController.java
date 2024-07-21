package org.spring.asterixapi;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix")
public class AsterixController {

    private final CharacterRepo characterRepo;

    public AsterixController(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    @GetMapping("/characters")
    public List<Character> getAllCharacters() {
        return characterRepo.findAll();
    }

    @PostMapping("/characters")
    public String addCharacter(@RequestBody Character character) {
        characterRepo.save(character);
        return character.toString() + " added to Database";
    }

    @PutMapping("/characters")
    public String updateCharacter(@RequestBody Character UpdateCharacter) {
        List<Character> characters = characterRepo.findAll();
        for (Character character : characters) {
            if (character.id().equals(UpdateCharacter.id())) {
                characterRepo.save(character);
            }
        }

        return UpdateCharacter.toString() + " updated to " + characterRepo.findAll().toString();
    }

    @DeleteMapping("/characters")
    public String deleteCharacter(@RequestBody String characterId) {
        List<Character> characters = characterRepo.findAll();
        for (Character character : characters) {
            if (character.id().equals(characterId)) {
                characterRepo.delete(character);
            }
        }

        return characterRepo.findAll().toString() + " deleted from Database";
    }
}
