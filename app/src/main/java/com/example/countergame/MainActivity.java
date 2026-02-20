package com.example.countergame;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements InputDialogFragment.OnInputListener
{

    List<Giocatore> giocatori = new ArrayList<>();

    boolean started = false;
    Giocatore attuale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button punteggi = findViewById(R.id.punteggio);
        Button inizia = findViewById(R.id.start);
        Button increment = findViewById(R.id.increment);
        TextView counter = findViewById(R.id.counter);
        Button fine = findViewById(R.id.stop);

        inizia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                InputDialogFragment dialog = new InputDialogFragment();
                dialog.show(getSupportFragmentManager(), "InputTag");
            }
        });

        punteggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                started = false;
                counter.setText("0");
                Intent intent = new Intent(getApplicationContext(), SecondaryActivity.class);
                intent.putExtra("giocatori", (Serializable) giocatori);
                startActivity(intent);
            }
        });

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(started)
                {
                    int text = Integer.valueOf(counter.getText().toString()) + 1;

                    counter.setText("" + text);

                    attuale.setPunti(attuale.getPunti() + 1);
                }
            }
        });

        fine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                started = false;
                counter.setText("0");
                Toast.makeText(getApplicationContext(), "Partita terminata per" + attuale.getNome(), Toast.LENGTH_SHORT);
            }
        });

    }

    @Override
    public void sendInput(String input)
    {
        for(Giocatore g: giocatori)
            if(g.getNome().equals(input))
            {
                attuale = g;
                started = true;
                return;
            }

        attuale = new Giocatore(input, 0);
        started = true;
    }

    public void onDataInput(String nome)
    {
        try
        {
            if(!nome.isEmpty())
            {
                sendInput(nome);
            }
        } catch (NumberFormatException e) {
            InputDialogFragment dialog = new InputDialogFragment();
            dialog.show(getSupportFragmentManager(), "InputTag");
        }
    }
}