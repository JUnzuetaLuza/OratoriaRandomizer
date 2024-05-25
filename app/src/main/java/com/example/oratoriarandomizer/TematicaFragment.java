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
import android.content.Context;
import android.content.SharedPreferences;

public class TematicaFragment extends Fragment {

    Phrase[] phrases = new Phrase[10];
    TextView txtPhrase1;
    TextView txtPhrase2;

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

    private SharedPreferences getSharedPreferences() {
        return requireActivity().getSharedPreferences("TematicaFragmentPreferences", Context.MODE_PRIVATE);
    }

    public void initializePhrases() {
        phrases[0] = new Phrase(1,"Conocimiento",true);
        phrases[1] = new Phrase(2,"Futuro",true);
        phrases[2] = new Phrase(3,"Tecnología",true);
        phrases[3] = new Phrase(4,"Desarrollo",true);
        phrases[4] = new Phrase(5,"Educación",true);
        phrases[5] = new Phrase(6,"Emprendimiento",true);
        phrases[6] = new Phrase(7,"Motivación",true);
        phrases[7] = new Phrase(8,"Inspiración",true);
        phrases[8] = new Phrase(9,"Vida",true);
        phrases[9] = new Phrase(10,"Cultura",true);

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

        int[] randomNumber = new int[2];
        int[] posArray = new int[2];
        boolean anyPhraseHab = false;
        int habCount = 0;

        for (Phrase phrase : phrases) {
            if (phrase.isHab()) {
                anyPhraseHab = true;
                habCount++;
            }
        }
        if (habCount < 2) {
            txtPhrase1.setText("No hay suficientes temáticas disponibles.");
            txtPhrase2.setText("");
            return;
        }
        if (anyPhraseHab) {
            for (int i = 0; i < 2; i++) {
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
                    saveTematicaState(phrase);
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

    private void saveTematicaState(Phrase phrase) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("phrase_" + phrase.getId(), phrase.isHab());
        editor.apply();
    }

    public void resetPhrases(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (Phrase phrase : phrases) {
            phrase.setHab(true);
            editor.putBoolean("phrase_" + phrase.getId(), true);
        }
        editor.apply();

        txtPhrase1.setText("");
        txtPhrase2.setText("Las temáticas han sido refrescadas");
    }
}