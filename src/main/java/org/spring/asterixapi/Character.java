package org.spring.asterixapi;


import lombok.With;
import org.springframework.data.annotation.Id;

public record Character(
        @Id
        String id,
        @With
        String name,
        @With
        int age,
        @With
        String profession
) {
}
