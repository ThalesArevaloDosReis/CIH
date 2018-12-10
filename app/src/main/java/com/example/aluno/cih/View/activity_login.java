package com.example.aluno.cih.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.ActionBar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.cih.ConexaoFirebase.FirebaseConexao;
import com.example.aluno.cih.Controller.ListaUsuarios;
import com.example.aluno.cih.R;
import com.example.aluno.cih.Model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// classe do login

public class activity_login extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    String emailParaRecuperar, email, senha;
    EditText edtEmail;
    ListaUsuarios crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final TextView cadastrar = (TextView) findViewById(R.id.lblCadastrar);
        cadastrar.setText(Html.fromHtml("<u>Cadastre-se</u>"));

        TextView esqSenha = (TextView) findViewById(R.id.txtEsqueceuSenha);
        esqSenha.setText(Html.fromHtml("<u>Esqueci minha senha</u>"));

        Button entrar = (Button) findViewById(R.id.btnLogin);

        crud = new ListaUsuarios(getBaseContext());

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edEmail = (EditText)findViewById(R.id.txtEmail1);
                EditText edSenha = (EditText)findViewById(R.id.txtSenha);

                email = edEmail.getText().toString();
                senha = edSenha.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(activity_login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    crud.alterarSenha(email, senha);
                                    Intent it = new Intent(activity_login.this, activity_principal.class);
                                    it.putExtra("userEmail", email);
                                    startActivity(it);

                                } else {
                                    Toast.makeText(getBaseContext(), "Email ou senha inválidos!", Toast.LENGTH_SHORT).show();
                                }
                            }
                });
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(activity_login.this, activity_cadastro.class);
                startActivity(it);
            }
        });

        esqSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity_login.this);
                builder.setTitle("Digite o email para recuperação de senha:");
                LayoutInflater inflater = activity_login.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_email, null);
                builder.setView(dialogView);

                edtEmail = (EditText) dialogView.findViewById(R.id.txtEmail);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        emailParaRecuperar = edtEmail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(emailParaRecuperar.trim());
                        Toast.makeText(activity_login.this, "Link para recuperação de senha enviado para " + emailParaRecuperar, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancelar", null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseConexao.getFirebaseAuth();
    }
}