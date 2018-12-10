package com.example.aluno.cih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import com.example.aluno.cih.Controller.ListaUsuarios;
import com.example.aluno.cih.View.activity_cadastro;
import com.example.aluno.cih.View.activity_login;

public class Usuario extends AppCompatActivity {
    public ImageView image;
    public String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        email = getIntent().getStringExtra("userEmail");

        TextView nome = (TextView) findViewById(R.id.txtNome);
        TextView conteudo = (TextView) findViewById(R.id.txtConteudo);
        Button editar = (Button) findViewById(R.id.btnEditarPerfil);
        image = (ImageView) findViewById(R.id.imageView3);

        ListaUsuarios lista = new ListaUsuarios(getBaseContext());
        for(com.example.aluno.cih.Model.Usuario u : lista.lerUsuarios()){
            if(u.getEmail().equals(email)){
                image.setImageBitmap(u.getImage());
                nome.setText(u.getNome());
                conteudo.setText(u.getPerfil());
            }
        }

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Usuario.this, EditarPerfil.class);
                it.putExtra("userEmail", email);
                startActivity(it);
            }
        });
    }
}