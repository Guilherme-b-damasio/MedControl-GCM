/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


enum Nivel_acesso {
    ATENDENTE("atendente"),MEDICO("medico"),ADMIN("admin");
    
private String nivel;
    
Nivel_acesso(String nivel){
    
}
}

public class Controle_acesso {
    private String id_usuario;
    private String nome_usuario;
    private String senha_usuario;
    private Nivel_acesso nivel_acesso;
    
    

    public Controle_acesso(String id_usuario, String nome_usuario, String senha_usuario, Nivel_acesso nivel_acesso) {
        this.id_usuario = id_usuario;
        this.nome_usuario = nome_usuario;
        this.senha_usuario = senha_usuario;
        this.nivel_acesso = nivel_acesso;
    }

    
    
    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public Nivel_acesso getNivel_acesso() {
        return nivel_acesso;
    }

    public void setNivel_acesso(Nivel_acesso nivel_acesso) {
        this.nivel_acesso = nivel_acesso;
    }
    
}

