package com.example.aluno.cih.Model;

public class UserCurteCom {
    private String emailUsuario;
    private int numeroComentario;
    private int numeroPublicacao;
    private char valor;

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public int getNumeroComentario() {
        return numeroComentario;
    }

    public void setNumeroComentario(int numeroComentario) {
        this.numeroComentario = numeroComentario;
    }

    public int getNumeroPublicacao() {
        return numeroPublicacao;
    }

    public void setNumeroPublicacao(int numeroPublicacao) {
        this.numeroPublicacao = numeroPublicacao;
    }

    public char getValor() {
        return valor;
    }

    public void setValor(char valor) {
        this.valor = valor;
    }
}