package com.example.oratoriarandomizer;

public class Phrase {
    private int id;
    private String phrase;
    private int cod;
    private int back;

    public Phrase(int id, String phrase, int cod, int back) {
        this.id = id;
        this.phrase = phrase;
        this.cod = cod;
        this.back = back;
    }

    public int getId() {
        return id;
    }
    public String getPhrase() {
        return phrase;
    }
    public int getCod() {
        return cod;
    }
    public int getBack() {
        return back;
    }
}
