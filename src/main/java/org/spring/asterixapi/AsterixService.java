package org.spring.asterixapi;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AsterixService {

    private final CharacterRepo characterRepo;
    private final IdService idService;

    public AsterixService(CharacterRepo characterRepo, IdService idService) {
        this.characterRepo = characterRepo;
        this.idService = idService;
    }


    public List<Character> getCharacters(String id,
                                         String name,
                                         Integer age,
                                         String profession) {
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

    public Character addCharacter(CharacterPostDTO character) {

        Character newCharacter = new Character(
                idService.generateId(),
                character.name(),
                character.age(),
                character.profession());

        return characterRepo.save(newCharacter);

    }

    public Character updateCharacterById(Character updateCharacter, String id) {
        Character character = characterRepo.findById(id)
                .orElseThrow()
                .withAge(updateCharacter.age())
                .withName(updateCharacter.name())
                .withProfession(updateCharacter.profession());

        return characterRepo.save(character);
    }

    public void deleteCharacterById(String id) {

        characterRepo.deleteById(id);
    }
}
