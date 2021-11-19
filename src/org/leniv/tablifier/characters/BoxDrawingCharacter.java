package org.leniv.tablifier.characters;

public enum BoxDrawingCharacter {

    HORIZONTAL('─'),
    VERTICAL('│'),
    DOWN_RIGHT('┌'),
    DOWN_LEFT('┐'),
    UP_RIGHT('└'),
    UP_LEFT('┘'),
    VERTICAL_RIGHT('├'),
    VERTICAL_LEFT('┤'),
    DOWN_HORIZONTAL('┬'),
    UP_HORIZONTAL('┴'),
    VERTICAL_HORIZONTAL('┼');

    private final char value;

    BoxDrawingCharacter(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

}
