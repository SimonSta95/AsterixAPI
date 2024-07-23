package org.spring.asterixapi;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix")
public class AsterixController {

    public final AsterixService asterixService;

    public AsterixController(AsterixService asterixService) {
        this.asterixService = asterixService;
    }

    @GetMapping("/characters")
    public List<Character> getAllCharacters(@RequestParam(required = false) String id,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) Integer age,
                                            @RequestParam(required = false) String profession) {

        return asterixService.getCharacters(id, name, age, profession);
    }

    @PostMapping("/characters")
    public Character addCharacter(@RequestBody CharacterPostDTO character) {

        return asterixService.addCharacter(character);
    }

    @PutMapping("/characters/{id}")
    public Character updateCharacterById(@RequestBody Character UpdateCharacter, @PathVariable String id) {

        return asterixService.updateCharacterById(UpdateCharacter, id);
    }

    @DeleteMapping("/characters/{id}")
    public void deleteCharacterById(@PathVariable String id) {

        asterixService.deleteCharacterById(id);
    }
}
