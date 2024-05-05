package com.example.oratoriarandomizer;

public class Phrase {
    private int id;
    private String phrase;
    private boolean hab;
    private int cod;
    private int back;

    public Phrase(int id, String phrase, boolean hab, int cod, int back) {
        this.id = id;
        this.phrase = phrase;
        this.hab = hab;
        this.cod = cod;
        this.back = back;
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
    public int getCod() {
        return cod;
    }
    public int getBack() {
        return back;
    }

    public void changeHab() {
        hab = !hab;
    }
}
