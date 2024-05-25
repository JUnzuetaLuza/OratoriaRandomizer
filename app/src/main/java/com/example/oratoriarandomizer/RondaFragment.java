package com.example.oratoriarandomizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import android.content.Context;
import android.content.SharedPreferences;

public class RondaFragment extends Fragment {

    Phrase[] phrases = new Phrase[6];
    TextView txtPhrase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ronda, container, false);

        txtPhrase = view.findViewById(R.id.txtPhraseRonda);

        Button btnRandom = view.findViewById(R.id.btnRandomRonda);
        Button btnReset = view.findViewById(R.id.btnResetRonda);
        btnRandom.setOnClickListener(this::randomPhrase);
        btnReset.setOnClickListener(this::resetPhrases);

        initializePhrases();
        return view;
    }

    private SharedPreferences getSharedPreferences() {
        return requireActivity().getSharedPreferences("RondaFragmentPreferences", Context.MODE_PRIVATE);
    }

    public void initializePhrases() {
        phrases[0] = new Phrase(1,"Pensamientos Encarnados",true);
        phrases[1] = new Phrase(2,"Duelo de Discursos",true);
        phrases[2] = new Phrase(3,"El Reloj del Mundo",true);
        phrases[3] = new Phrase(4,"La Rueda de las Perspectivas",true);
        phrases[4] = new Phrase(5,"Historias de Sincronicidad",true);
        phrases[5] = new Phrase(6,"Transformando Desafíos",true);

        SharedPreferences sharedPreferences = getSharedPreferences();
        for (Phrase phrase : phrases) {
            boolean isHab = sharedPreferences.getBoolean("phrase_" + phrase.getId(), true);
            phrase.setHab(isHab);
        }
   }
    private int generateRandomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
    public void randomPhrase(View view) {

        int randomNumber;
        int posArray;
        boolean anyPhraseHab = false;

        for (Phrase phrase : phrases) {
            if (phrase.isHab()) {
                anyPhraseHab = true;
                break;
            }
        }
        if (anyPhraseHab) {
            do {
                randomNumber = generateRandomNum(1, 6);
                posArray = randomNumber - 1;
            } while (!phrases[posArray].isHab());

            txtPhrase.setText(phrases[posArray].getPhrase());
            phrases[posArray].changeHab();
            saveRondaState(phrases[posArray]);
        } else {
            txtPhrase.setText("Todas las rondas han sido realizadas.");
        }
    }

    private void saveRondaState(Phrase phrase) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("phrase_" + phrase.getId(), phrase.isHab());
        editor.apply();
    }

    public void resetPhrases(View view) {
        for (Phrase phrase : phrases) {
            if(!phrase.isHab()) { phrase.changeHab(); }
        }
        txtPhrase.setText("Las rondas han sido refrescadas");
    }
}