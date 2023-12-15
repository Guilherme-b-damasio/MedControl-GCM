
package model;
import java.util.ArrayList;


public class Medico extends Pessoa2 {
    private ArrayList<Consulta> consultas;    
    private String crm_medico;
    private String admissao_medico;
    private String hr_trab_medico; 
    private Controle_acesso acesso_medico;
    private int id_medico;

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }
    
    public Medico(String nome, String cpf, String id, String telefone, String email, String sexo, String data_nasc,
    String crm_medico, String admissao_medico, String horario_trabalho, Controle_acesso acesso_medico) {
        super(nome, cpf, id, telefone, email, sexo, data_nasc);
        this.crm_medico = crm_medico;
        this.admissao_medico = admissao_medico;
        this.hr_trab_medico = horario_trabalho;        
        this.acesso_medico = acesso_medico;
    }
    
    public Medico(){
    
    }
    
    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }

    public String getCrm_medico() {
        return crm_medico;
    }

    public void setCrm_medico(String crm_medico) {
        this.crm_medico = crm_medico;
    }

    public String getAdmissao_medico() {
        return admissao_medico;
    }

    public void setAdmissao_medico(String admissao_medico) {
        this.admissao_medico = admissao_medico;
    }

    public String getHr_trab_medico() {
        return hr_trab_medico;
    }

    public void setHr_trab_medico(String hr_trab_medico) {
        this.hr_trab_medico = hr_trab_medico;
    }

    public Controle_acesso getAcesso_medico() {
        return acesso_medico;
    }

    public void setAcesso_medico(Controle_acesso acesso_medico) {
        this.acesso_medico = acesso_medico;
    }
    
    
    
    @Override
    public String toString() {
        return "Médico:" + getNome();
    }
    
}
