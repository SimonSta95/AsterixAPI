package org.spring.asterixapi;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

    public String addCharacter(CharacterPostDTO character) {

        Character newCharacter = new Character(UUID.randomUUID().toString(),
                character.name(),
                character.age(),
                character.profession());

        characterRepo.save(newCharacter);
        return newCharacter.toString() + " added to Database";
    }

    public String updateCharacter(Character UpdateCharacter) {
        List<Character> characters = characterRepo.findAll();
        for (Character character : characters) {
            if (character.id().equals(UpdateCharacter.id())) {

                if (UpdateCharacter.age() != 0) { character.withAge(UpdateCharacter.age());}
                if (UpdateCharacter.name() != "") {character.withName(UpdateCharacter.name());}
                if (UpdateCharacter.profession() != "") { character.withProfession(UpdateCharacter.profession());}

                characterRepo.save(character);
            }
        }

        return UpdateCharacter.toString() + " updated";
    }

    public String updateCharacterById(Character updateCharacter,String id) {
        List<Character> characters = characterRepo.findAll();
        for (Character character : characters) {
            if (character.id().equals(id)) {

                characterRepo.save(character);
            }
        }

        return "Character with id " + id + " updated";
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

    public String deleteCharacterById(String id) {
        List<Character> characters = characterRepo.findAll();
        for (Character character : characters) {
            if (character.id().equals(id)) {
                characterRepo.delete(character);
            }
        }

        return "Character with id " + id + " deleted";
    }
}
