package org.spring.asterixapi;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsterixServiceTest {

    private final CharacterRepo mockCharacterRepo = mock(CharacterRepo.class);
    private final IdService mockIdService = mock(IdService.class);
    private final AsterixService asterixService = new AsterixService(mockCharacterRepo, mockIdService);

    @Test
    void getAllCharactersTest() {
        List<Character> allCharcters = List.of(
                new Character("1","Asterix",35,"Krieger"),
                new Character("2","Obelix",35,"Lieferant")
        );

        List<Character> expectedCharacters = List.of(
                new Character("1","Asterix",35,"Krieger"),
                new Character("2","Obelix",35,"Lieferant")
        );

        when(mockCharacterRepo.findAll()).thenReturn(allCharcters);
        List<Character> actual = asterixService.getCharacters(null, null, null, null);

        verify(mockCharacterRepo).findAll();
        assertEquals(expectedCharacters, actual);

    }

    @Test
    void getAllCharactersByNameTest() {
        List<Character> allCharacters = List.of(
                new Character("1","Asterix",35,"Krieger"),
                new Character("2","Obelix",35,"Lieferant")
        );

        List<Character> expectedCharacters = List.of(
                new Character("1","Asterix",35,"Krieger")
        );

        when(mockCharacterRepo.findAll()).thenReturn(expectedCharacters);
        List<Character> actual = asterixService.getCharacters(null, "A", null, null);

        verify(mockCharacterRepo).findAll();
        assertEquals(expectedCharacters, actual);

    }

    @Test
    void getAllCharactersByAgeTest() {
        List<Character> allCharacters = List.of(
                new Character("1","Asterix",35,"Krieger"),
                new Character("2","Obelix",35,"Lieferant")
        );

        List<Character> expectedCharacters = List.of(
                new Character("1","Asterix",35,"Krieger"),
                new Character("2","Obelix",35,"Lieferant")
        );

        when(mockCharacterRepo.findAll()).thenReturn(expectedCharacters);
        List<Character> actual = asterixService.getCharacters(null, null, 35, null);

        verify(mockCharacterRepo).findAll();
        assertEquals(expectedCharacters, actual);

    }

    @Test
    void getAllCharactersByProfessionTest() {
        List<Character> allCharacters = List.of(
                new Character("1","Asterix",35,"Krieger"),
                new Character("2","Obelix",35,"Lieferant")
        );

        List<Character> expectedCharacters = List.of(
                new Character("2","Obelix",35,"Lieferant")
        );

        when(mockCharacterRepo.findAll()).thenReturn(expectedCharacters);
        List<Character> actual = asterixService.getCharacters(null, null, null, "Lieferant");

        verify(mockCharacterRepo).findAll();
        assertEquals(expectedCharacters, actual);
    }

    @Test
    void getCharacterTestByIdTest() {
        List<Character> allCharacters = List.of(
                new Character("1","Asterix",35,"Krieger"),
                new Character("2","Obelix",35,"Lieferant")
        );

        List<Character> expectedCharacters = List.of(
                new Character("2","Obelix",35,"Lieferant")
        );

        when(mockCharacterRepo.findAll()).thenReturn(expectedCharacters);
        List<Character> actual = asterixService.getCharacters("2", null, null, null);

        verify(mockCharacterRepo).findAll();
        assertEquals(expectedCharacters, actual);
    }

    @Test
    void updateCharacterByIdTest() {
        Character character = new Character("1","Asterix",35,"Krieger");
        Character expectedCharacter = new Character("1","Asterix",40,"Krieger");

        when(mockCharacterRepo.findById("1")).thenReturn(Optional.of(character));
        when(mockCharacterRepo.save(expectedCharacter)).thenReturn(expectedCharacter);

        Character actual = asterixService.updateCharacterById(expectedCharacter, "1");

        verify(mockCharacterRepo).findById("1");
        verify(mockCharacterRepo).save(expectedCharacter);
        assertEquals(expectedCharacter, actual);
    }

    @Test
    void deleteCharacterByIdTest() {

        doNothing().when(mockCharacterRepo).deleteById("1");
        asterixService.deleteCharacterById("1");
        verify(mockCharacterRepo).deleteById("1");
    }
}