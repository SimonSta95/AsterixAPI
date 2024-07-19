package org.spring.asterixapi;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/asterix")
public class AsterixController {

    private final CharacterRepo characterRepo;

    public AsterixController(CharacterRepo characterRepo) {
        this.characterRepo = characterRepo;
    }

    //GET
    @GetMapping("/character")
    public List<Character> getCharacters(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String profession) {

//        Query query = new Query();
//        if (id != null) {
//            query.addCriteria(Criteria.where("id").is(id));
//            characterRepo.find(query)
//            ;
//        }
//        if (name != null) {
//            query.addCriteria(Criteria.where("name").is(name));
//        }
//        if (profession != null) {
//            query.addCriteria(Criteria.where("profession").is(profession));
//        }

        return null;
    }

    //CREATE
    @PostMapping("/character")
    public String addCharacter(@RequestBody Character character) {
        characterRepo.save(character);

        return "Character saved.";
    }

    //UPDATE
    @PutMapping("/character/update")
    public String updateCharacter(@RequestBody Character character) {
        if (characterRepo.existsById(character.id())) {
            Character updateCharacter = characterRepo.findById(character.id()).get();
            updateCharacter.withAge(character.age());
            updateCharacter.withName(character.name());
            updateCharacter.withProfession(character.profession());
            characterRepo.save(updateCharacter);



            return "Character updated";
        }

        return "404 Character not found";
    }

    //DELETE
    @DeleteMapping("/character/{id}")
    public String deleteCharacter(@PathVariable("id") String id) {
        if (characterRepo.existsById(id)) {
            characterRepo.deleteById(id);
            return "Character deleted";
        }

        return "404 Character not found";
    }


}

