
package model;


public class Paciente extends Pessoa2 {
    private String cep_paciente;
    private String rua_paciente;
    private String altura_paciente;
    private String numero_paciente;
    private String rg_paciente;
    private String complementos_paciente;
    private String plano_saude;
    private String contato_emergencia;
    private String estado_civil;
    private String tipo_sanguineo_paciente;
    private String peso_paciente;
    private String nome_paciente;
    private int id_paciente;

    public String getNome_paciente() {
        return nome_paciente;
    }

    public void setNome_paciente(String nome_paciente) {
        this.nome_paciente = nome_paciente;
    }

    
    public Paciente(String nome, String cpf, String id, String telefone, String email, String sexo, String data_nasc) {
        super(nome, cpf, id, telefone, email, sexo, data_nasc);
    }
    
    public Paciente(){
    }

    public String getCep_paciente() {
        return cep_paciente;
    }

    public void setCep_paciente(String cep_paciente) {
        this.cep_paciente = cep_paciente;
    }

    public String getRua_paciente() {
        return rua_paciente;
    }

    public void setRua_paciente(String rua_paciente) {
        this.rua_paciente = rua_paciente;
    }

    public String getAltura_paciente() {
        return altura_paciente;
    }

    public void setAltura_paciente(String altura_paciente) {
        this.altura_paciente = altura_paciente;
    }

    public String getNumero_paciente() {
        return numero_paciente;
    }

    public void setNumero_paciente(String numero_paciente) {
        this.numero_paciente = numero_paciente;
    }

    public String getRg_paciente() {
        return rg_paciente;
    }

    public void setRg_paciente(String rg_paciente) {
        this.rg_paciente = rg_paciente;
    }

    public String getComplementos_paciente() {
        return complementos_paciente;
    }

    public void setComplementos_paciente(String complementos_paciente) {
        this.complementos_paciente = complementos_paciente;
    }

    public String getPlano_saude() {
        return plano_saude;
    }

    public void setPlano_saude(String plano_saude) {
        this.plano_saude = plano_saude;
    }

    public String getContato_emergencia() {
        return contato_emergencia;
    }

    public void setContato_emergencia(String contato_emergencia) {
        this.contato_emergencia = contato_emergencia;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getTipo_sanguineo_paciente() {
        return tipo_sanguineo_paciente;
    }

    public void setTipo_sanguineo_paciente(String tipo_sanguineo_paciente) {
        this.tipo_sanguineo_paciente = tipo_sanguineo_paciente;
    }

    public String getPeso_paciente() {
        return peso_paciente;
    }

    public void setPeso_paciente(String peso_paciente) {
        this.peso_paciente = peso_paciente;
    }

    @Override
    public String toString() {
        return "Paciente: " + getNome();
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }
    
    public int getId_paciente(){
        return id_paciente;
    
    }

    
}
