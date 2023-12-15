package model;

import java.util.ArrayList;

/**
 *
 * @author luca
 */
public abstract class Pessoa2 {
    protected String nome;
    protected String cpf;
    protected String id;
    
    protected String telefone;
    protected String email;
    protected String sexo;
    protected String data_nasc;
    
    
    
    
    //CONSTRUTOR PARA PACIENTE
    public Pessoa2(String nome, String cpf, String id, String telefone, String email,String sexo, String data_nasc) {
        this.nome = nome;
        this.cpf = cpf;
        this.id = id;
        this.telefone = telefone;
        this.email = email;
        this.sexo = sexo;
        this.data_nasc = data_nasc;
    }
    
    public Pessoa2(){
    
    }
        
    //METODOS ESPECIAIS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }
        
}
