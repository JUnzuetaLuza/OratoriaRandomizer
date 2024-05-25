package com.example.oratoriarandomizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OrdenFragment extends Fragment {

    Phrase[] phrases = new Phrase[6];
    TextView txtPhrase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orden, container, false);

        txtPhrase = view.findViewById(R.id.txtPhraseOrden);

        Button btnRandom = view.findViewById(R.id.btnRandomOrden);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomPhrase(v);
            }
        });

        initializePhrases();
        return view;
    }
    public void initializePhrases() {
        phrases[0] = new Phrase(1,"Economía",true);
        phrases[1] = new Phrase(2,"Administración de negocios globales",true);
        phrases[2] = new Phrase(3,"Marketing y administración comercial",true);
        phrases[3] = new Phrase(4,"Turismo, Hotelería y Gastronomía",true);
        phrases[4] = new Phrase(5,"Administración y Gerencia",true);
        phrases[5] = new Phrase(6,"Contabilidad y Finanzas",true);
    }
    private int generateRandomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
    public void randomPhrase(View view) {

        List<Phrase> enabledPhrases = new ArrayList<>();

        for (Phrase phrase : phrases) {
            if (phrase.isHab()) {
                enabledPhrases.add(phrase);
            }
        }
        if (!enabledPhrases.isEmpty()) {
            Collections.shuffle(enabledPhrases);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < enabledPhrases.size(); i++) {
                stringBuilder.append(i + 1).append(". ").append(enabledPhrases.get(i).getPhrase()).append("\n");
            }

            txtPhrase.setText(stringBuilder.toString());
        } else {
            txtPhrase.setText("Todas las frases han sido utilizadas.");
        }
    }
}