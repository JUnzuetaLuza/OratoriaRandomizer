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
    //TextView txtRandomNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orden, container, false);

        txtPhrase = view.findViewById(R.id.txtPhraseOrden);
        //txtRandomNum = view.findViewById(R.id.txtRandomNumOrden);

        Button btnRandom = view.findViewById(R.id.btnRandomOrden);
        //Button btnReset = view.findViewById(R.id.btnResetOrden);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomPhrase(v);
            }
        });
        //btnReset.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        resetPhrases(v);
        //    }
        //});

        initializePhrases();

        return view;
    }
    public void initializePhrases() {
        phrases[0] = new Phrase(1,"Economía",true,9901,8801);
        phrases[1] = new Phrase(2,"Administración de negocios globales",true,9902,8802);
        phrases[2] = new Phrase(3,"Marketing y administración comercial",true,9903,8803);
        phrases[3] = new Phrase(4,"Turismo, Hotelería y Gastronomía",true,9904,8804);
        phrases[4] = new Phrase(5,"Administración y Gerencia",true,9905,8805);
        phrases[5] = new Phrase(6,"Contabilidad y Finanzas",true,9906,8806);
    }
    private int generateRandomNum(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
    public void randomPhrase(View view) {

        List<Phrase> enabledPhrases = new ArrayList<>();

        for (Phrase phrase : phrases) {         //Recorrer el array
            if (phrase.isHab()) {               //Si alguno esta habilitado
                enabledPhrases.add(phrase);     //Agregar al array temporal
            }
        }
        if (!enabledPhrases.isEmpty()) {
            Collections.shuffle(enabledPhrases);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < enabledPhrases.size(); i++) {
                stringBuilder.append(i + 1).append(". ").append(enabledPhrases.get(i).getPhrase()).append("\n");
            }

            //txtRandomNum.setText(""); //Muesta el numero generado
            txtPhrase.setText(stringBuilder.toString()); //Muestra la frase designada
        } else {
            //txtRandomNum.setText(" ");
            txtPhrase.setText("Todas las frases han sido utilizadas.");
        }
    }
    //public void resetPhrases(View view) {
    //    for (Phrase phrase : phrases) { //Recorrer el array
    //        if(!phrase.isHab()) { phrase.changeHab(); }
    //    }
    //    //txtRandomNum.setText(" ");
    //    txtPhrase.setText("Las frases han sido refrescadas");
    //}
}