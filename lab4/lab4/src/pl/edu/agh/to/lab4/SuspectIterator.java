package pl.edu.agh.to.lab4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SuspectIterator implements Iterator<Suspect> {

    Suspect suspect;
    Iterator<? extends Suspect> iterator;

    public SuspectIterator(Iterator<? extends Suspect> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Suspect next() {
        suspect = iterator.next();
        if (suspect != null) {
            return suspect;
        }
        throw new NoSuchElementException("There is no such element");
    }
}