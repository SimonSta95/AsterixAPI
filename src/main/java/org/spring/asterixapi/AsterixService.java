package org.spring.asterixapi;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsterixService {

    private final CharacterRepo characterRepo;

    public AsterixService(CharacterRepo characterRepo){
        this.characterRepo = characterRepo;
    }


    public List<Character> getCharacters(String id,
                                         String name,
                                         Integer age,
                                         String profession){
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

    public String addCharacter(@RequestBody Character character) {
        characterRepo.save(character);
        return character.toString() + " added to Database";
    }

    public String updateCharacter(@RequestBody Character UpdateCharacter) {
        List<Character> characters = characterRepo.findAll();
        for (Character character : characters) {
            if (character.id().equals(UpdateCharacter.id())) {
                characterRepo.save(character);
            }
        }

        return UpdateCharacter.toString() + " updated to " + characterRepo.findAll().toString();
    }

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
