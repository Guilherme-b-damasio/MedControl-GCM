package medcontrolcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import db.DB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import medcontrol.Main;
import model.Paciente;

public class FXMLTelaMedicoController {
    
  

    @FXML
    private ImageView imgvUser;

    @FXML
    private Label lblmatricula;

    @FXML
    private Label lblNome;

    @FXML
    private Pane paneConsultas;

    @FXML
    private JFXRadioButton rbtnConsAgendadas;

    @FXML
    private ToggleGroup listagem;

    @FXML
    private GridPane gridListaConsAgendadas;

    @FXML
    private Label contadorConsAgendadas;

    @FXML
    private JFXListView<String> lvConsAgendadas;

    @FXML
    private GridPane gridListaHistoricoCons;

    @FXML
    private Label contadorHistoricoCons;

    @FXML
    private JFXListView<String> lvHistoricoCons;

    @FXML
    private Pane paneReceita;

    @FXML
    private GridPane gridListaConsAgendadas1;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnImprimir;

    @FXML
    private JFXTextArea txtPrescricao;

    @FXML
    private JFXTextArea txtDiagnostico;

    @FXML
    private JFXTextField nome_paciente;

    @FXML
    private JFXTextField id_consulta;

    @FXML
    private Label lblSaudacao;

    @FXML
    private ImageView imvSaudacao;

    
    public void initialize(URL location, ResourceBundle resources) {
        imvSaudacao.setVisible(true);
        lblSaudacao.setVisible(true);
        
        paneReceita.setVisible(false);
        paneConsultas.setVisible(false);
       
        
    }
    
    
    @FXML
    void abreGridConsAgendadaLista(ActionEvent event) {  
        gridListaConsAgendadas.setVisible(true);
        gridListaHistoricoCons.setVisible(false);
       
        
    }

    @FXML
    void abreGridHistoricoConsLista(ActionEvent event) {
        gridListaConsAgendadas.setVisible(false);
        gridListaHistoricoCons.setVisible(true);
       
    }

    @FXML
    void abrirSecaoLIstar(ActionEvent event) {
        paneConsultas.setVisible(true);
        paneReceita.setVisible(false);
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);
        carregarConsAgendadaLista();
        carregarHistoricoCons();
    }

    @FXML
    void abrirSecaoReceita(ActionEvent event) {
        paneReceita.setVisible(true);
        paneConsultas.setVisible(false);
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);

    }

    @FXML
    void impReceita(ActionEvent event) {
        exibirAlerta("IMPRIMIR", "Enviando ao spooler de impressão", AlertType.INFORMATION);

    }

    @FXML
    void removerHistoricoConsLista(ActionEvent event) {
        
    }

    @FXML
    void sair(ActionEvent event) {
        Main.trocarTela("login");

    }

    @FXML
    void salvarReceita(ActionEvent event) throws SQLException {
        Connection conn = DB.getConnection();
        Paciente paciente = new Paciente();

        if (conn == null) {
            System.out.println("BD Não conectado");
            return; // Evitar a execução do restante do método
        }

        String idConsulta = id_consulta.getText();

        String sql = "select * from consulta where id_consulta = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idConsulta);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String sql2 = "insert consultamedica(fk_consulta, receita_cons, diagnostico_cons) values (?,?,?)";
                    String pescr = txtPrescricao.getText();
                    String diag = txtDiagnostico.getText();

                    try (PreparedStatement pst = conn.prepareStatement(sql2)) {

                        pst.setString(1, idConsulta);
                        pst.setString(2, pescr);
                        pst.setString(3, diag);
               

                        int rowsAffected = pst.executeUpdate();

                        if (rowsAffected > 0) {
                           exibirAlerta("Sucesso", "Registro cadastrado com sucesso", AlertType.INFORMATION);



                        } else {
                           exibirAlerta("Falha", "Falha ao inserir o registro medico.", AlertType.ERROR);
                            // Adicione aqui qualquer lógica de tratamento de erro necessária.
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                        exibirAlerta("Falha", "Falha ao inserir o registro medico." + e.getMessage(), AlertType.ERROR);
                        // Adicione aqui qualquer lógica de tratamento de erro necessária.
                    }
              
                } else {
                    exibirAlerta("Falha", "Falha ao encontrar consulta.", AlertType.ERROR);
                }
            }
        }          
        
    }

    @FXML            
    void verConsAgendadas(ActionEvent event) {
         String nomeConsAgendada = lvConsAgendadas.getSelectionModel().getSelectedItem();

        if (nomeConsAgendada != null) {
            exibirDetalhesConsulta(nomeConsAgendada);
        }
    }
    
    @FXML
    void verHistoricoConsLista(ActionEvent event) {
    // Lógica para visualizar histórico de consultas
        String itemSelecionado = lvHistoricoCons.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            // Extrair o ID do histórico da string selecionada
            int idHistConsulta = Integer.parseInt(itemSelecionado.split(" - ")[0]);
            exibirDetalhesHistCons(idHistConsulta);
        }
    }
    
    void carregarPacientesLista(){
    }
    
    void carregarConsAgendadaLista(){
        ObservableList<String> listaPacientes = FXCollections.observableArrayList();
        int contador = 0;
        String sql = "SELECT p.nome_paciente FROM consulta c " +
                 "JOIN paciente p ON c.fk_paciente = p.id_paciente";

        try (Connection connection = DB.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nomePaciente = rs.getString("nome_paciente");
                listaPacientes.add(nomePaciente);
                ++contador;
            }

            } catch (SQLException e) {
                e.printStackTrace();
            // Adicione a lógica de tratamento de erro aqui
    }
        contadorConsAgendadas.setText(""+ contador);
        lvConsAgendadas.setItems(listaPacientes);
    }
    
    void carregarHistoricoCons() {
        ObservableList<String> historicoConsultas = FXCollections.observableArrayList();
        int contador = 0;

        String sql = "SELECT hc.id_historicoConsulta, p.nome_paciente, c.data_consulta " +
                     "FROM historico_consulta hc " +
                     "JOIN consulta c ON hc.fk_consulta = c.id_consulta " +
                     "JOIN paciente p ON c.fk_paciente = p.id_paciente " +
                     "ORDER BY c.data_consulta";

        try (Connection connection = DB.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int idHistConsulta = rs.getInt("id_historicoConsulta");
                String nomePaciente = rs.getString("nome_paciente");
                String dataConsulta = rs.getString("data_consulta");
                historicoConsultas.add(idHistConsulta + " - " + nomePaciente + " - Data: " + dataConsulta);
                ++contador;
            }

            // O restante do código para atualizar a lista no seu aplicativo
            // ...

        } catch (SQLException e) {
            e.printStackTrace();
            // Adicione a lógica de tratamento de erro aqui
        }
        contadorHistoricoCons.setText(""+contador);
        lvHistoricoCons.setItems(historicoConsultas);
    }
    
    
    
    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
    
    
    private void exibirDetalhesConsulta(String nomePaciente) {
        String sql = "SELECT c.*, m.nome_medico, e.nome_esp " +
                     "FROM consulta c " +
                     "JOIN medicos m ON c.fk_medico = m.id_medico " +
                     "JOIN especialidade e ON c.fk_especialidade = e.id_esp " +
                     "JOIN paciente p ON c.fk_paciente = p.id_paciente " +
                     "WHERE p.nome_paciente = ?";
            System.out.println("aaaaaaaaaa");

        try (Connection connection = DB.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setString(1, nomePaciente);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int idConsulta = rs.getInt("id_consulta");
                    String cepConsulta = rs.getString("cep_consulta");
                    String dataConsulta = rs.getString("data_consulta");
                    String horarioConsulta = rs.getString("horario_consulta");
                    String salaConsulta = rs.getString("sala_consulta");
                    String nomeMedico = rs.getString("nome_medico");
                    String especialidadeMedico = rs.getString("nome_esp");

                    // Aqui você pode exibir essas informações conforme necessário
                    System.out.println("ID da Consulta: " + idConsulta);
                    System.out.println("CEP da Consulta: " + cepConsulta);
                    System.out.println("Data da Consulta: " + dataConsulta);
                    System.out.println("Horário da Consulta: " + horarioConsulta);
                    System.out.println("Sala da Consulta: " + salaConsulta);
                    System.out.println("Médico: " + nomeMedico);
                    System.out.println("Especialidade: " + especialidadeMedico);

                    // Aqui você pode implementar a lógica para exibir essas informações
                    // Pode usar labels, pop-ups, etc.
                    exibirAlerta("Detalhes da Consulta", "ID: " + idConsulta +
                            "\nCEP: " + cepConsulta +
                            "\nData: " + dataConsulta +
                            "\nHorário: " + horarioConsulta +
                            "\nSala: " + salaConsulta +
                            "\nMédico: " + nomeMedico +
                            "\nEspecialidade: " + especialidadeMedico, Alert.AlertType.INFORMATION);


                    System.out.println("ID: " + idConsulta +
                            "\nCEP: " + cepConsulta +
                            "\nData: " + dataConsulta +
                            "\nHorário: " + horarioConsulta +
                            "\nSala: " + salaConsulta +
                            "\nMédico: " + nomeMedico +
                            "\nEspecialidade: " + especialidadeMedico);

                } else {
                    System.out.println("Consulta não encontrada");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Adicione a lógica de tratamento de erro aqui
        }
    }
    
    private void exibirDetalhesHistCons(int idHistConsulta) {
    String sql = "SELECT hc.*, c.data_consulta, c.horario_consulta, m.nome_medico, e.nome_esp, cm.receita_cons, cm.diagnostico_cons " +
                 "FROM historico_consulta hc " +
                 "JOIN consulta c ON hc.fk_consulta = c.id_consulta " +
                 "JOIN medicos m ON c.fk_medico = m.id_medico " +
                 "JOIN especialidade e ON c.fk_especialidade = e.id_esp " +
                 "JOIN consultamedica cm ON hc.fk_ConsultaMED = cm.id_cons " +
                 "WHERE hc.id_historicoConsulta = ?";

    StringBuilder detalhesConsulta = new StringBuilder();

    try (Connection connection = DB.getConnection();
         PreparedStatement pst = connection.prepareStatement(sql)) {

        pst.setInt(1, idHistConsulta);

        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                
                
                String diagnostico = rs.getString("diagnostico_cons");
                String receita = rs.getString("receita_cons");
                String dataConsulta = rs.getString("data_consulta");
                String horarioConsulta = rs.getString("horario_consulta");
                String nomeMedico = rs.getString("nome_medico");
                String especialidadeMedico = rs.getString("nome_esp");

                detalhesConsulta.append("ID do Histórico de Consulta: ").append(idHistConsulta).append("\n");
                
                
                detalhesConsulta.append("Diagnóstico: ").append(diagnostico).append("\n");
                detalhesConsulta.append("Receita: ").append(receita).append("\n");
                detalhesConsulta.append("Data da Consulta: ").append(dataConsulta).append("\n");
                detalhesConsulta.append("Horário da Consulta: ").append(horarioConsulta).append("\n");
                detalhesConsulta.append("Médico: ").append(nomeMedico).append("\n");
                detalhesConsulta.append("Especialidade: ").append(especialidadeMedico);

                // Exibir detalhes (você pode adaptar essa parte conforme necessário)
                System.out.println(detalhesConsulta.toString());
                exibirAlerta("Detalhes da Consulta", detalhesConsulta.toString(), Alert.AlertType.INFORMATION);

            } else {
                System.out.println("Histórico de Consulta não encontrado");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Adicione a lógica de tratamento de erro aqui
    }
}
}

