package org.leniv.tablifier.characters;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoxDrawingCharacters {

    public enum Type {
        LIGHT, HEAVY, DOUBLE
    }

    public static Map<BoxDrawingCharacter, Character> getCharacters(Type type) {
        return switch (type) {
            case LIGHT -> getLightCharacters();
            case HEAVY -> getHeavyCharacters();
            case DOUBLE -> getDoubleCharacters();
        };
    }

    public static Map<BoxDrawingCharacter, Character> getLightCharacters() {
        return new EnumMap<>(Stream.of(BoxDrawingCharacter.values())
                                   .collect(Collectors.toMap(Function.identity(), BoxDrawingCharacter::getValue)));
    }

    public static Map<BoxDrawingCharacter, Character> getHeavyCharacters() {
        return new EnumMap<>(Map.ofEntries(
                Map.entry(BoxDrawingCharacter.HORIZONTAL, '━'),
                Map.entry(BoxDrawingCharacter.VERTICAL, '┃'),
                Map.entry(BoxDrawingCharacter.DOWN_RIGHT, '┏'),
                Map.entry(BoxDrawingCharacter.DOWN_LEFT, '┓'),
                Map.entry(BoxDrawingCharacter.UP_RIGHT, '┗'),
                Map.entry(BoxDrawingCharacter.UP_LEFT, '┛'),
                Map.entry(BoxDrawingCharacter.VERTICAL_RIGHT, '┣'),
                Map.entry(BoxDrawingCharacter.VERTICAL_LEFT, '┫'),
                Map.entry(BoxDrawingCharacter.DOWN_HORIZONTAL, '┳'),
                Map.entry(BoxDrawingCharacter.UP_HORIZONTAL, '┻'),
                Map.entry(BoxDrawingCharacter.VERTICAL_HORIZONTAL, '╋')
        ));
    }

    public static Map<BoxDrawingCharacter, Character> getDoubleCharacters() {
        return new EnumMap<>(Map.ofEntries(
                Map.entry(BoxDrawingCharacter.HORIZONTAL, '═'),
                Map.entry(BoxDrawingCharacter.VERTICAL, '║'),
                Map.entry(BoxDrawingCharacter.DOWN_RIGHT, '╔'),
                Map.entry(BoxDrawingCharacter.DOWN_LEFT, '╗'),
                Map.entry(BoxDrawingCharacter.UP_RIGHT, '╚'),
                Map.entry(BoxDrawingCharacter.UP_LEFT, '╝'),
                Map.entry(BoxDrawingCharacter.VERTICAL_RIGHT, '╠'),
                Map.entry(BoxDrawingCharacter.VERTICAL_LEFT, '╣'),
                Map.entry(BoxDrawingCharacter.DOWN_HORIZONTAL, '╦'),
                Map.entry(BoxDrawingCharacter.UP_HORIZONTAL, '╩'),
                Map.entry(BoxDrawingCharacter.VERTICAL_HORIZONTAL, '╬')
        ));
    }

}
