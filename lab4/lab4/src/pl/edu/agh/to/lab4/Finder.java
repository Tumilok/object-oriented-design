package pl.edu.agh.to.lab4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class Finder {
    private final SuspectAggregate allPersons;
    private final SuspectAggregate allPrisoners;

    public Finder(PersonDatabase personDatabase, PrisonersDatabase prisonersDatabase) {
        this.allPersons = personDatabase;
        this.allPrisoners = prisonersDatabase;
    }

    public void displayAllSuspectsWithName(String name) {
        ArrayList<Suspect> suspectedPersons = new ArrayList<Suspect>();
        Iterator<? extends Suspect> prisonersIterator = allPrisoners.iterator();
        Iterator<? extends Suspect> personsIterator = allPersons.iterator();

        while (prisonersIterator.hasNext()) {
            Suspect tempSuspect = prisonersIterator.next();
            if (tempSuspect.getFirstName().equals(name) && tempSuspect.canBeSuspected()) {
                suspectedPersons.add(tempSuspect);
                if (suspectedPersons.size() >= 10) {
                    break;
                }
            }
        }

        if (suspectedPersons.size() < 10) {
            while (personsIterator.hasNext()) {
                Suspect tempSuspect = personsIterator.next();
                if (tempSuspect.getFirstName().equals(name) && tempSuspect.canBeSuspected()) {
                    suspectedPersons.add(tempSuspect);
                    if (suspectedPersons.size() >= 10) {
                        break;
                    }
                }
            }
        }

        System.out.println("Znalazlem " + suspectedPersons.size() + " pasujacych podejrzanych!");

        for (Suspect suspect: suspectedPersons) {
            System.out.println(suspect.toString());
        }
    }
}
