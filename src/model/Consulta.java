package model;

/**
 *
 * @author rened
 */
public class Consulta {
    private String id_consulta;
    private String data_consulta;
    private String sala_consulta;
    private String horario_consulta;

    
    
    
    public Consulta(String id_consulta, String data_consulta, String sala_consulta, String horario_consulta) {
        this.id_consulta = id_consulta;
        this.data_consulta = data_consulta;
        this.sala_consulta = sala_consulta;
        this.horario_consulta = horario_consulta;
    }
    
    public Consulta(){
    
    }

    public String getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(String id_consulta) {
        this.id_consulta = id_consulta;
    }

    public String getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(String data_consulta) {
        this.data_consulta = data_consulta;
    }

    public String getSala_consulta() {
        return sala_consulta;
    }

    public void setSala_consulta(String sala_consulta) {
        this.sala_consulta = sala_consulta;
    }

    public String getHorario_consulta() {
        return horario_consulta;
    }

    public void setHorario_consulta(String horario_consulta) {
        this.horario_consulta = horario_consulta;
    }

    public void setData_Consulta(String toString) {
    }


    public void setHorario_Consulta(String toString) {
    }

    public String getData_Consulta() {  
        return null;
    }

    public String getSala_Consulta() {
        return sala_consulta;
    }

    public String getHorario_Consulta() {
        return null;
    }

    public void setSala_Consulta(String sala_consulta) {
       this.sala_consulta = sala_consulta;
    }
    
    
}
