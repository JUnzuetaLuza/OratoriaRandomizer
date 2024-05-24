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

public class DiscursoFragment extends Fragment {

    Phrase[] phrases = new Phrase[5];
    TextView txtPhrase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_discurso, container, false);

        txtPhrase = view.findViewById(R.id.txtPhraseDiscurso);

        Button btnRandom = view.findViewById(R.id.btnRandomDiscurso);
        Button btnReset = view.findViewById(R.id.btnResetDiscurso);
        btnRandom.setOnClickListener(this::randomPhrase);
        btnReset.setOnClickListener(this::resetPhrases);

        initializePhrases();
        return view;
    }

    private SharedPreferences getSharedPreferences() {
        return requireActivity().getSharedPreferences("DiscursoFragmentPreferences", Context.MODE_PRIVATE);
    }

    public void initializePhrases() {
        phrases[0] = new Phrase(1,"Narrativo",true,9901,8801);
        phrases[1] = new Phrase(2,"Expositivo",true,9902,8802);
        phrases[2] = new Phrase(3,"Argumentativo",true,9903,8803);
        phrases[3] = new Phrase(4,"Informativo",true,9904,8804);
        phrases[4] = new Phrase(5,"Publicitario",true,9905,8805);

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
                randomNumber = generateRandomNum(1, 5);
                posArray = randomNumber - 1;
            } while (!phrases[posArray].isHab());

            txtPhrase.setText(phrases[posArray].getPhrase());
            phrases[posArray].changeHab();
            saveRondaState(phrases[posArray]);
        } else {
            txtPhrase.setText("Todas los tipos de discurso han sido utilizados.");
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
        txtPhrase.setText("Los tipos de discurso han sido refrescadas");
    }
}