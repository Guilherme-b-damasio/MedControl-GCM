package model;

import java.util.ArrayList;


public class Especialidade {
    private String id_esp;
    private String nome_esp;
    private String sala_esp;
    private ArrayList<Medico> medicos;

    public Especialidade(String id_esp, String nome_esp, String sala_esp) {
        this.id_esp = id_esp;
        this.nome_esp = nome_esp;
        this.sala_esp = sala_esp;
    }

    public ArrayList<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(ArrayList<Medico> medicos) {
        this.medicos = medicos;
    }

    public String getId_esp() {
        return id_esp;
    }

    public void setId_esp(String id_esp) {
        this.id_esp = id_esp;
    }

    public String getNome_esp() {
        return nome_esp;
    }

    public void setNome_esp(String nome_esp) {
        this.nome_esp = nome_esp;
    }

    public String getSala_esp() {
        return sala_esp;
    }

    public void setSala_esp(String sala_esp) {
        this.sala_esp = sala_esp;
    }
    
}
