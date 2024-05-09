package com.example.oratoriarandomizer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class FraseFragment extends Fragment {

    Phrase[] phrases = new Phrase[28];
    TextView txtPhrase;
    //TextView txtRandomNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frase, container, false);

        txtPhrase = view.findViewById(R.id.txtPhraseFrase);
        //txtRandomNum = view.findViewById(R.id.txtRandomNumFrase);

        Button btnRandom = view.findViewById(R.id.btnRandomFrase);
        Button btnReset = view.findViewById(R.id.btnResetFrase);
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
        phrases[0] = new Phrase(1,"Epícuro: \"Cuanto más grande es la dificultad, más gloria hay en superarla\".",true,9901,8801);
        phrases[1] = new Phrase(2,"Soren Kierkegaard: \"La vida debe ser comprendida hacia atrás. Pero debe ser vivida hacia delante\".",true,9902,8802);
        phrases[2] = new Phrase(3,"Jean-Paul Sartre: \"El hombre está condenado a ser libre\".",true,9903,8803);
        phrases[3] = new Phrase(4,"Anaxágoras: \"En todo hay una parte de todo\".",true,9904,8804);
        phrases[4] = new Phrase(5,"Demócrito: \"El hombre valiente es el que no solo supera a sus enemigos, sino también a sus placeres\".",true,9905,8805);
        phrases[5] = new Phrase(6,"Erich Fromm: \"La creatividad requiere que la valentía se desprenda de las certezas\".",true,9906,8806);
        phrases[6] = new Phrase(7,"Edmund Burke: \"Aquellos que no conocen la historia están condenados a repetirla\".",true,9907,8807);
        phrases[7] = new Phrase(8,"Dante: \"De una pequeña chispa puede prender una llama\".",true,9908,8808);
        phrases[8] = new Phrase(9,"Gandhi: \"Nadie puede herirme sin mi permiso\".",true,9909,8809);
        phrases[9] = new Phrase(10,"Spinoza: \"Puedo controlar mis pasiones y emociones si puedo entender su naturaleza\".",true,9910,8810);
        phrases[10] = new Phrase(11,"Sergio Rodríguez Abitia: \"El turismo tiene como objetivo la construcción de mejores personas y no de mejores fortunas\".",true,9911,8811);
        phrases[11] = new Phrase(12,"John Maynard Keynes: \"El mercado puede permanecer irracional más tiempo del que usted puede permanecer solvente\".",true,9912,8812);
        phrases[12] = new Phrase(13,"Gary Vaynerchuk: \"Por favor, piense en su legado, porque lo está escribiendo todos los días\".",true,9913,8813);
        phrases[13] = new Phrase(14,"Stephen Covey: \"El liderazgo efectivo es poner primero lo primero. La gestión eficaz es la disciplina llevada a cabo\".",true,9914,8814);
        phrases[14] = new Phrase(15,"John D. Rockefeller: \"Una buena gestión consiste en mostrar a gente promedio cómo hacer el trabajo de gente superior\".",true,9915,8815);
        phrases[15] = new Phrase(16,"Peter F. Drucker: \"Lo que se mide mejora\".",true,9916,8816);
        phrases[16] = new Phrase(17,"Jeff Bezos: \"Tu marca es lo que la gente dice de ti cuando no estás en la sala\".",true,99176,8817);
        phrases[17] = new Phrase(18,"Lao Tsé: \"No hay que ir para atrás ni para darse impulso\".",true,9918,8818);
        phrases[18] = new Phrase(19,"Charles Baudelaire: \"Para trabajar basta estar convencido de una cosa: que trabajar es menos aburrido que divertirse\".",true,9919,8819);
        phrases[19] = new Phrase(20,"Jacinto Benavente: \"Lo peor que hacen los malos es obligarnos a dudar de los buenos\".",true,9920,8820);
        phrases[20] = new Phrase(21,"Confucio: \"Aprende a vivir y sabrás morir bien\".",true,9921,8821);
        phrases[21] = new Phrase(22,"Albert Einstein: \"Cada día sabemos más y entendemos menos\".",true,9922,8822);
        phrases[22] = new Phrase(23,"Elizabeth Gilbert: \"El conocimiento es una brújula que te guía en la oscuridad\".",true,9923,8823);
        phrases[23] = new Phrase(24,"Sócrates: \"La verdadera sabiduría está en reconocer la propia ignorancia\".",true,9924,8824);
        phrases[24] = new Phrase(25,"Robert Collier: \"El éxito es el resultado de pequeños esfuerzos repetidos día tras día\".",true,9925,8825);
        phrases[25] = new Phrase(26,"Fyodor Dostoyevski: \"El hombre se complace en enumerar sus pesares, pero no enumera sus alegrías\".",true,9926,8826);
        phrases[26] = new Phrase(27,"Herman Hesse: \"El pájaro pelea hasta que consigue salir del huevo. El huevo es su mundo. Todo ser viviente debería intentar destruir el mundo\".",true,9927,8827);
        phrases[27] = new Phrase(28,"Mark Twain: \"No hay una visión más triste que la de un joven pesimista\".",true,9928,8828);
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
                randomNumber = generateRandomNum(1, 28);
                posArray = randomNumber - 1;
            } while (!phrases[posArray].isHab());

            //txtRandomNum.setText(String.valueOf(randomNumber)); //Muesta el numero generado
            txtPhrase.setText(phrases[posArray].getPhrase()); //Muestra la frase designada
            phrases[posArray].changeHab();//Deshabilita la frase
        } else {
            //txtRandomNum.setText(" ");
            txtPhrase.setText("Todas las frases han sido utilizadas.");
        }
    }
    public void resetPhrases(View view) {
        for (Phrase phrase : phrases) { //Recorrer el array
            if(!phrase.isHab()) { phrase.changeHab(); }
        }
        //txtRandomNum.setText(" ");
        txtPhrase.setText("Las frases han sido refrescadas");
    }
}