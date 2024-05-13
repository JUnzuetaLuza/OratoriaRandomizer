package com.example.oratoriarandomizer;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class TematicaFragment extends Fragment {

    Phrase[] phrases = new Phrase[10];
    TextView txtPhrase1;
    TextView txtPhrase2;
    //TextView txtRandomNum;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tematica, container, false);

        txtPhrase1 = view.findViewById(R.id.txtPhraseTematica1);
        txtPhrase2 = view.findViewById(R.id.txtPhraseTematica2);
        //txtRandomNum = view.findViewById(R.id.txtRandomNumTematica);

        Button btnRandom = view.findViewById(R.id.btnRandomTematica);
        Button btnReset = view.findViewById(R.id.btnResetTematica);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomPhrase(v);
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPhrases(v);
            }
        });

        initializePhrases();

        return view;
    }
    public void initializePhrases() {
        phrases[0] = new Phrase(1,"Conocimiento",true,9901,8801);
        phrases[1] = new Phrase(2,"Futuro",true,9902,8802);
        phrases[2] = new Phrase(3,"Tecnología",true,9903,8803);
        phrases[3] = new Phrase(4,"Desarrollo",true,9904,8804);
        phrases[4] = new Phrase(5,"Educación",true,9905,8805);
        phrases[5] = new Phrase(6,"Emprendimiento",true,9906,8806);
        phrases[6] = new Phrase(7,"Motivación",true,9907,8807);
        phrases[7] = new Phrase(8,"Inspiración",true,9908,8808);
        phrases[8] = new Phrase(9,"Vida",true,9909,8809);
        phrases[9] = new Phrase(10,"Cultura",true,9910,8810);
    }
    private int generateRandomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
    public void randomPhrase(View view) {

        // Declarar variables
        int randomNumber;
        int posArray;
        boolean anyPhraseHab = false;

        for (Phrase phrase : phrases) { //Recorrer el array
            if (phrase.isHab()) {             //Si alguno esta habilitado
                anyPhraseHab = true;
                break;
            }
        }
        if (anyPhraseHab) {
            // Generar un numero aleatorio del 1 al 28 que este habilitado
            do {
                randomNumber = generateRandomNum(1, 10);
                posArray = randomNumber - 1;
            } while (!phrases[posArray].isHab());

            //txtRandomNum.setText(String.valueOf(randomNumber)); //Muesta el numero generado
            txtPhrase1.setText(phrases[posArray].getPhrase()); //Muestra la frase designada
            phrases[posArray].changeHab();//Deshabilita la frase


            // Generar un numero aleatorio del 1 al 28 que este habilitado
            do {
                randomNumber = generateRandomNum(1, 10);
                posArray = randomNumber - 1;
            } while (!phrases[posArray].isHab());

            //txtRandomNum.setText(String.valueOf(randomNumber)); //Muesta el numero generado
            txtPhrase2.setText(phrases[posArray].getPhrase()); //Muestra la frase designada
            phrases[posArray].changeHab();//Deshabilita la frase

        } else {
            //txtRandomNum.setText(" ");
            txtPhrase1.setText("Todas las temáticas han sido utilizadas.");
            txtPhrase2.setText("Todas las temáticas han sido utilizadas.");
        }
    }
    public void resetPhrases(View view) {
        for (Phrase phrase : phrases) { //Recorrer el array
            if(!phrase.isHab()) { phrase.changeHab(); }
        }
        //txtRandomNum.setText(" ");
        txtPhrase1.setText("Las temáticas han sido refrescadas");
        txtPhrase2.setText("Las temáticas han sido refrescadas");
    }
}