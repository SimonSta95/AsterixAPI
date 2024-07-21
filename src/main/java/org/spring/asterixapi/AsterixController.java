package org.spring.asterixapi;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/asterix")
public class AsterixController {

    private final CharacterRepo characterRepo;

    public AsterixController(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    @GetMapping("/characters")
    public List<Character> getAllCharacters(@RequestParam(required = false) String id,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) Integer age,
                                            @RequestParam (required = false) String profession){

        List<Character> characters = new ArrayList<>();
        List<Character> allCharacters = characterRepo.findAll();

        if (id != null) {
            for (Character character : allCharacters) {
                if (character.id().equals(id)) {
                    characters.add(character);
                }
            }
            return characters;
        }

        if (name != null) {
            for (Character character : allCharacters) {
                if (character.name().startsWith(name)) {
                    characters.add(character);
                }
            }
            return characters;
        }

        if (age != null) {
            for (Character character : allCharacters) {
                if (character.age() == age) {
                    characters.add(character);
                }
            }
            return characters;
        }

        if (profession != null) {
            for (Character character : allCharacters) {
                if (character.profession().equals(profession)) {
                    characters.add(character);
                }
            }
            return characters;
        }

        return allCharacters;

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
