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
                                            @RequestParam (required = false) String profession){

        return asterixService.getCharacters(id,name, String.valueOf(age),profession);

    }

    @PostMapping("/characters")
    public String addCharacter(@RequestBody Character character) {
        return asterixService.addCharacter(character);
    }


    @PutMapping("/characters")
    public String updateCharacter(@RequestBody Character UpdateCharacter) {

        return asterixService.updateCharacter(UpdateCharacter);
    }

    @DeleteMapping("/characters")
    public String deleteCharacter(@RequestBody String characterId) {

        return asterixService.deleteCharacter(characterId);
    }
}
