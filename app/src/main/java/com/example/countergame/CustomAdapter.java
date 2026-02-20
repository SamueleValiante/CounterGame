package com.example.countergame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.countergame.Giocatore;
import com.example.countergame.R;

import java.util.List;
import java.util.zip.Inflater;

public class CustomAdapter extends ArrayAdapter<Giocatore>
{
    private LayoutInflater inflater;

    public CustomAdapter(Context ctx, int reside, List<Giocatore> giocatori)
    {
        super(ctx, reside, giocatori);

        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        v = inflater.inflate(R.layout.listelement, null);

        Giocatore g = getItem(position);

        TextView nome = v.findViewById(R.id.nome);

        TextView punti = v.findViewById(R.id.punti);

        String textNome = g.getNome();

        String textpunti = "" + g.getPunti();

        nome.setText(textNome);

        punti.setText(textpunti);

        nome.setTag(position);
        punti.setTag(position);

        return v;
    }
}
