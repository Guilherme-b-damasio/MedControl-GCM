
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Atendente extends Pessoa2{
    private String admissao_atendente;
    private String hr_trab_atendente;
    private Controle_acesso acesso_atendente;
    
    public Atendente(String nome, String cpf, String id, String telefone, String email, String sexo, String data_nasc, String admissao_atendente, String hr_trab_atendente, Controle_acesso acesso_atendente){
        super(nome, cpf, id, telefone, email, sexo, data_nasc);
        this.admissao_atendente = admissao_atendente;
        this.hr_trab_atendente = hr_trab_atendente;
        this.acesso_atendente = acesso_atendente;
    }

    public Atendente(){
    }

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

    public String getAdmissao_atendente() {
        return admissao_atendente;
    }

    public void setAdmissao_atendente(String admissao_atendente) {
        this.admissao_atendente = admissao_atendente;
    }

    public String getHr_trab_atendente() {
        return hr_trab_atendente;
    }

    public void setHr_trab_atendente(String hr_trab_atendente) {
        this.hr_trab_atendente = hr_trab_atendente;
    }

    public Controle_acesso getAcesso_atendente() {
        return acesso_atendente;
    }

    public void setAcesso_atendente(Controle_acesso acesso_atendente) {
        this.acesso_atendente = acesso_atendente;
    }
    
  
    
}
