package com.example.countergame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class InputDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_input, null);

        EditText inputField = view.findViewById(R.id.edit_text_input);

        builder.setView(view)
                .setTitle("Nuovo Contatto")
                .setPositiveButton("Salva", (dialog, id) -> {
                    String testoInserito = inputField.getText().toString();
                    ((MainActivity) getActivity()).onDataInput(testoInserito);
                })
                .setNegativeButton("Annulla", (dialog, id) -> dismiss());

        return builder.create();
    }
}
