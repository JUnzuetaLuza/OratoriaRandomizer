package com.example.oratoriarandomizer;

public class Phrase {
    private int id;
    private String phrase;
    private boolean hab;

    public Phrase(int id, String phrase, boolean hab) {
        this.id = id;
        this.phrase = phrase;
        this.hab = hab;
    }

    public int getId() {
        return id;
    }
    public String getPhrase() {
        return phrase;
    }
    public boolean isHab() {
        return hab;
    }
    public void changeHab() {
        hab = !hab;
    }
    public void setHab(boolean set) { hab = set; }
}
