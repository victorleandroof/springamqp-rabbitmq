package br.com.victor.jobmq.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Serializable {

    @SerializedName("nome")
    private String nome;
    @SerializedName("email")
    private String email;
    @SerializedName("token")
    private String token;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
