package pl.edu.agh.to.lab4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Finder {
    private final Collection<Person> allPersons;

    private final Map<String, Collection<Prisoner>> allPrisoners;

    public Finder(Collection<Person> allPersons, Map<String, Collection<Prisoner>> allPrisoners) {
        this.allPersons = allPersons;
        this.allPrisoners = allPrisoners;
    }

    public Finder(PersonDatabase personDatabase, PrisonersDatabase prisonersDatabase) {
        this(personDatabase.getCracovPersons(), prisonersDatabase.getPrisoners());
    }

    public void displayAllSuspectsWithName(String name) {
        ArrayList<Suspect> suspectedPersons = new ArrayList<Suspect>();

        for (Collection<Prisoner> prisonerCollection : allPrisoners.values()) {
            for (Prisoner prisoner : prisonerCollection) {
                if (!prisoner.canBeSuspected() && prisoner.getFirstName().equals(name)) {
                    suspectedPersons.add(prisoner);
                }
                if (suspectedPersons.size() >= 10) {
                    break;
                }
            }
            if (suspectedPersons.size() >= 10) {
                break;
            }
        }

        if (suspectedPersons.size() < 10) {
            for (Person person : allPersons) {
                if (person.canBeSuspected() && person.getFirstName().equals(name)) {
                    suspectedPersons.add(person);
                }
                if (suspectedPersons.size() >= 10) {
                    break;
                }
            }
        }

        System.out.println("Znalazlem " + suspectedPersons.size() + " pasujacych podejrzanych!");

        for (Suspect suspect: suspectedPersons) {
            System.out.println(suspect.toString());
        }
    }
}
