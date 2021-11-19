package ru.geekbrains;

public class HomeWorkApp {
    public static void main(String[] args) {
        Person[] persons = new Person[5];

        persons[0] = new Person(
                "Ivanov Ivan",
                "Engineer",
                "ivivan@mailbox.com",
                "892312312",
                30_000,
                30);

        persons[1] = new Person(
                "Viviyan Sheirlaw",
                "Programmer Analyst I",
                "vsheirlaw0@google.es",
                "+225 849 569 6060",
                50_000,
                45);

        persons[2] = new Person(
                "Wyatan Meeke",
                "Engineer II",
                "wmeeke1@forbes.com",
                "+33 879 172 7580",
                60_000,
                42);

        persons[3] = new Person(
                "Korella Lalevee",
                "Operator",
                "klalevee2@blinklist.com",
                "+86 442 330 6887",
                20_000,
                32);

        persons[4] = new Person(
                "Verney Macklin",
                "Speech Pathologist",
                "vmacklin3@cbc.ca",
                "+504 180 258 7764",
                55_000,
                41);

        for (Person person: persons) {
            if (person.getAge() > 40) {
                person.printToConsole();
            }
        }
    }
}
