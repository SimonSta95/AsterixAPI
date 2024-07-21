package org.spring.asterixapi;

import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("characters")
public record Character(String id,
                        @With
                        String name,
                        @With
                        int age,
                        @With
                        String profession) {
}
