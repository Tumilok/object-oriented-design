package pl.edu.agh.to.lab4;

import java.util.ArrayList;
import java.util.Collection;

public class PersonDatabase {

    private final Collection<Person> cracovPersons = new ArrayList<Person>();

    public PersonDatabase() {
        addCracovPerson("Jan", "Kowalski", 30);
        addCracovPerson("Janusz", "Krakowski", 30);
        addCracovPerson("Janusz", "Mlodociany", 10);
        addCracovPerson("Kasia", "Kosinska", 19);
        addCracovPerson("Piotr", "Zgredek", 29);
        addCracovPerson("Tomek", "Gimbus", 14);
        addCracovPerson("Janusz", "Gimbus", 15);
        addCracovPerson("Alicja", "Zaczarowana", 22);
        addCracovPerson("Janusz", "Programista", 77);
        addCracovPerson("Pawel", "Pawlowicz", 32);
        addCracovPerson("Krzysztof", "Mendel", 30);
    }

    public Collection<Person> getCracovPersons() {
        return this.cracovPersons;
    }

    private void addCracovPerson(String firstName, String lastName, int age) {
        cracovPersons.add(new Person(firstName, lastName, age));
    }
}
