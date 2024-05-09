package com.example.oratoriarandomizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class RondaFragment extends Fragment {

    //String orden[] = {
    //        "Economía",
    //        "Administración de negocios globales",
    //        "Marketing y administración comercial",
    //        "Turismo, Hotelería y Gastronomía",
    //        "Administración y Gerencia",
    //        "Contabilidad y Finanzas"
    //};
    Phrase[] phrases = new Phrase[6];
    TextView txtPhrase;
    TextView txtRandomNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ronda, container, false);

        txtPhrase = view.findViewById(R.id.txtPhraseRonda);
        txtRandomNum = view.findViewById(R.id.txtRandomNumRonda);

        Button btnRandom = view.findViewById(R.id.btnRandomRonda);
        Button btnReset = view.findViewById(R.id.btnResetRonda);
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
        phrases[0] = new Phrase(1,"Pensamientos Encarnados",true,9901,8801);
        phrases[1] = new Phrase(2,"Duelo de Discursos",true,9902,8802);
        phrases[2] = new Phrase(3,"El Reloj del Mundo",true,9903,8803);
        phrases[3] = new Phrase(4,"La Rueda de las Perspectivas",true,9904,8804);
        phrases[4] = new Phrase(5,"Historias de Sincronicidad",true,9905,8805);
        phrases[5] = new Phrase(6,"Transformando Desafíos",true,9906,8806);
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
                randomNumber = generateRandomNum(1, 6);
                posArray = randomNumber - 1;
            } while (!phrases[posArray].isHab());

            txtRandomNum.setText(String.valueOf(randomNumber)); //Muesta el numero generado
            txtPhrase.setText(phrases[posArray].getPhrase()); //Muestra la frase designada
            phrases[posArray].changeHab();//Deshabilita la frase
        } else {
            txtRandomNum.setText(" ");
            txtPhrase.setText("Todas las rondas han sido realizadas.");
        }
    }
    public void resetPhrases(View view) {
        for (Phrase phrase : phrases) { //Recorrer el array
            if(!phrase.isHab()) { phrase.changeHab(); }
        }
        txtRandomNum.setText(" ");
        txtPhrase.setText("Las rondas han sido refrescadas");
    }
}