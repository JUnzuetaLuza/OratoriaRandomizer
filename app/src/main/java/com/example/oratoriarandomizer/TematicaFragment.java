package com.example.oratoriarandomizer;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

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
        txtPhrase1.setOnClickListener(v -> selectTematica(txtPhrase1));
        txtPhrase2.setOnClickListener(v -> selectTematica(txtPhrase2));

        Button btnRandom = view.findViewById(R.id.btnRandomTematica);
        Button btnReset = view.findViewById(R.id.btnResetTematica);
        btnRandom.setOnClickListener(this::randomPhrase);
        btnReset.setOnClickListener(this::resetPhrases);

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
        int[] randomNumber = new int[2];
        int[] posArray = new int[2];
        boolean anyPhraseHab = false;

        for (Phrase phrase : phrases) { //Recorrer el array
            if (phrase.isHab()) {             //Si alguno esta habilitado
                anyPhraseHab = true;
                break;
            }
        }
        if (anyPhraseHab) {
            for (int i = 0; i < 2; i++) {
                // Generar un numero aleatorio del 1 al 28 que este habilitado
                do {
                    randomNumber[i] = generateRandomNum(1, 10);
                } while (contains(randomNumber, randomNumber[i], i) || !phrases[randomNumber[i] - 1].isHab());
                posArray[i] = randomNumber[i] - 1;
            }
            for (int i = 0; i < 2; i++) {
                int textViewId = getResources().getIdentifier("txtPhraseTematica" + (i + 1), "id", requireContext().getPackageName());
                TextView textView = requireView().findViewById(textViewId);
                textView.setText(phrases[posArray[i]].getPhrase());
            }

        } else {
            txtPhrase1.setText("Todas las temáticas han sido utilizadas.");
            txtPhrase2.setText("");
        }

    }

    private boolean contains(int[] array, int number, int endIndex) {
        for (int i = 0; i < endIndex; i++) {
            if (array[i] == number) {
                return true;
            }
        }
        return false;
    }

    public void selectTematica(View view) {

        TextView selectedTextView = (TextView) view;
        String selectedPhrase = selectedTextView.getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Confirmación");
        builder.setMessage("¿Desea seleccionar esta temática?\n\n" + selectedPhrase);
        builder.setPositiveButton("Confirmar", ((dialog, which) -> {
            for (Phrase phrase : phrases) {
                if (phrase.getPhrase().equals(selectedPhrase)) {
                    phrase.setHab(false);
                    break;
                }
            }
            txtPhrase1.setText("");
            txtPhrase2.setText("");
            Toast.makeText(getContext(), "Temática seleccionada: " + selectedPhrase, Toast.LENGTH_SHORT).show();
        }));
        builder.setNegativeButton("Cancelar", ((dialog, which) -> dialog.dismiss()));

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void resetPhrases(View view) {
        for (Phrase phrase : phrases) { //Recorrer el array
            if(!phrase.isHab()) { phrase.changeHab(); }
        }
        txtPhrase1.setText("");
        txtPhrase2.setText("Las temáticas han sido refrescadas");
    }
}