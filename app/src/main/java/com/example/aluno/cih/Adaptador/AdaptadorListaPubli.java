package com.example.aluno.cih.Adaptador;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.aluno.cih.Controller.ListaPublicacoes;
import com.example.aluno.cih.Model.Publicacao;

import java.util.List;

public class AdaptadorListaPubli extends BaseAdapter {
    private final List<Publicacao> pub;

    public AdaptadorListaPubli(ListaPublicacoes pub, Activity act) {
        this.pub = (List<Publicacao>) pub;
    }

    @Override
    public int getCount() {
        return pub.size();
    }

    @Override
    public Object getItem(int position) {
        return pub.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
