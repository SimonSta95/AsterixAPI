package org.spring.asterixapi;

import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("characters")
@With
public record Character(String id,
                        String name,
                        int age,
                        String profession) {
}
