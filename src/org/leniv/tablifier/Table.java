package org.leniv.tablifier;

import org.leniv.tablifier.characters.BoxDrawingCharacter;
import org.leniv.tablifier.characters.BoxDrawingCharacters;

import java.util.*;
import java.util.function.Function;

public class Table<T> {

    private final Collection<T> rows;
    private final List<Column> columns = new ArrayList<>();
    private final Map<BoxDrawingCharacter, Character> characters;

    public enum Alignment {
        LEFT, CENTER, RIGHT
    }

    public Table(Collection<T> rows) {
        this(rows, BoxDrawingCharacters.Type.LIGHT);
    }

    public Table(Collection<T> rows, BoxDrawingCharacters.Type type) {
        this.rows = rows;
        characters = BoxDrawingCharacters.getCharacters(type);
    }

    public Table<T> addColumn(String header, Alignment alignment, Function<? super T, ?> extractor) {
        columns.add(new Column(header, alignment, extractor));
        return this;
    }

    @Override
    public String toString() {
        return header() + content() + footer();
    }

    private String divider(BoxDrawingCharacter first, BoxDrawingCharacter middle, BoxDrawingCharacter last) {
        StringBuilder divider = new StringBuilder();
        divider.append(characters.get(first));
        for (int i = 0; i < columns.size(); i++) {
            divider.append(columns.get(i).getDivider(characters.get(BoxDrawingCharacter.HORIZONTAL)))
                   .append(characters.get(i == columns.size() - 1 ? last : middle));
        }
        return divider.toString();
    }

    private String header() {
        StringBuilder header = new StringBuilder();
        header.append(divider(
                      BoxDrawingCharacter.DOWN_RIGHT,
                      BoxDrawingCharacter.DOWN_HORIZONTAL,
                      BoxDrawingCharacter.DOWN_LEFT
              ))
              .append(System.lineSeparator())
              .append(characters.get(BoxDrawingCharacter.VERTICAL));
        for (Column column : columns) {
            header.append(column.getHeader()).append(characters.get(BoxDrawingCharacter.VERTICAL));
        }
        header.append(System.lineSeparator())
              .append(divider(
                      BoxDrawingCharacter.VERTICAL_RIGHT,
                      BoxDrawingCharacter.VERTICAL_HORIZONTAL,
                      BoxDrawingCharacter.VERTICAL_LEFT
              ))
              .append(System.lineSeparator());
        return header.toString();
    }

    private String content() {
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < rows.size(); i++) {
            content.append(characters.get(BoxDrawingCharacter.VERTICAL));
            for (Column column : columns) {
                content.append(column.getCell(i)).append(characters.get(BoxDrawingCharacter.VERTICAL));
            }
            content.append(System.lineSeparator());
        }
        return content.toString();
    }

    private String footer() {
        return divider(BoxDrawingCharacter.UP_RIGHT, BoxDrawingCharacter.UP_HORIZONTAL, BoxDrawingCharacter.UP_LEFT);
    }

    private class Column {

        final String header;
        final Alignment alignment;
        final Function<? super T, ?> extractor;
        final String[] cells;
        final int width;

        Column(String header, Alignment alignment, Function<? super T, ?> extractor) {
            this.header = header;
            this.alignment = alignment;
            this.extractor = extractor;
            cells = rows.stream().map(extractor).map(Object::toString).toArray(String[]::new);
            width = Math.max(Arrays.stream(cells).mapToInt(String::length).max().orElse(0), header.length());
        }

        String getHeader() {
            return (" %-" + width + "s ").formatted(header);
        }

        String getCell(int index) {
            return switch (alignment) {
                case LEFT -> (" %-" + width + "s ").formatted(cells[index]);
                case CENTER -> {
                    int numberOfSpaces = width - cells[index].length();
                    String leadingSpaces = " ".repeat(numberOfSpaces / 2 + 1);
                    String trailingSpaces = " ".repeat((int) Math.round(numberOfSpaces / 2.) + 1);
                    yield leadingSpaces + cells[index] + trailingSpaces;
                }
                case RIGHT -> (" %" + width + "s ").formatted(cells[index]);
            };
        }

        String getDivider(char c) {
            return Character.toString(c).repeat(width + 2);
        }

    }

}
