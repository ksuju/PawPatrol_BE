package com.patrol.domain.animal.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AnimalType {
    DOG("강아지"),
    CAT("고양이");

    private final String description;

    AnimalType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static AnimalType fromString(String value) {
        for (AnimalType type : AnimalType.values()) {
            if (type.name().equalsIgnoreCase(value) || type.description.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("잘못된 AnimalType 값: " + value);
    }
}
