package com.rectangularchicken.diydictionaryserver;

public class Entry {

    private final String term, definition;

    public Entry(String term, String definition) {
        this.term = term;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }
}
