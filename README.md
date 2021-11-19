## tablifier

Usage:

```java
record Person(String name, int age) {}
List<Person> people = List.of(
        new Person("Elon Musk", 50),
        new Person("Keanu Reeves", 57)
);
Table<Person> table = new Table<>(people, BoxDrawingCharacters.Type.LIGHT);
table.addColumn("Name", Table.Alignment.CENTER, Person::name)
     .addColumn("Age", Table.Alignment.RIGHT, Person::age);
System.out.println(table);
```

Result:

```
┌──────────────┬─────┐
│ Name         │ Age │
├──────────────┼─────┤
│  Elon Musk   │  50 │
│ Keanu Reeves │  57 │
└──────────────┴─────┘
```