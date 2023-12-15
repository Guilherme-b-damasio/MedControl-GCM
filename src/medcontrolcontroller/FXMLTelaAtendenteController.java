package medcontrolcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import db.DB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import medcontrol.Main;
import model.Consulta;
import model.Medico;
import model.Paciente;

public class FXMLTelaAtendenteController implements Initializable{
    
    
    ObservableList<String> opcoesHorarioCons = FXCollections.observableArrayList(
                "08:00", "10:00", "14:00", "16:00", "18:00"
    );
    ObservableList<String> opcoesSexo = FXCollections.observableArrayList(
                "M", "F"
        );
    ObservableList<String> opcoesSalaCon = FXCollections.observableArrayList(
                "SalaA", "SalaB" , "SalaC"
    );
    ObservableList<String> opcoesEstCivil = FXCollections.observableArrayList(
                "Casado", "Solteiro", "Viuvo"
    );
     ObservableList<String> opcoesTipoSangue = FXCollections.observableArrayList(
                "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    );
    
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        horario_consulta.setItems(opcoesHorarioCons);
        
        paneRegistro.setVisible(false);
        paneListar.setVisible(false);
        paneConsulta.setVisible(false);
        sexo_paciente.setItems(opcoesSexo);
        estado_civil.setItems(opcoesEstCivil);
        tipo_sanguineo_paciente.setItems(opcoesTipoSangue);
    }

    @FXML
    private ImageView imgvUser;

    @FXML
    private Label lblmatricula;

    @FXML
    private Label lblNome;

    @FXML
    private Pane paneRegistro;

    @FXML
    private ScrollPane scrollPaciente;

    @FXML
    private JFXTextField nome_paciente;

    @FXML
    private JFXTextField cpf_paciente;

    @FXML
    private JFXTextField rg_paciente;

    @FXML
    private JFXTextField id_paciente;

    @FXML
    private JFXTextField telefone_paciente;

    @FXML
    private JFXTextField email_paciente;

    @FXML
    private JFXComboBox<String> sexo_paciente;

    @FXML
    private JFXDatePicker data_nascimento_paciente;

    @FXML
    private JFXComboBox<String> estado_civil;

    @FXML
    private JFXTextField contato_emergencia;

    @FXML
    private JFXTextField peso_paciente;

    @FXML
    private JFXTextField plano_saude;

    @FXML
    private JFXComboBox<String> tipo_sanguineo_paciente;

    @FXML
    private JFXTextField altura_paciente;

    @FXML
    private JFXTextField cep_paciente;

    @FXML
    private JFXTextField rua_paciente;

    @FXML
    private JFXTextField rua_numero_paciente;

    @FXML
    private JFXTextField complementos_paciente;

    @FXML
    private JFXButton btnRegistrarPaciente;

    @FXML
    private Pane paneConsulta;

    @FXML
    private ScrollPane scrollConsulta;

    @FXML
    private JFXDatePicker data_consulta;

    @FXML
    private JFXComboBox<?> sala_consulta;

    @FXML
    private JFXComboBox<String> horario_consulta;

    @FXML
    private JFXComboBox<?> nome_esp;

    @FXML
    private JFXTextField nome_medico;

    @FXML
    private JFXButton btnRegistrarConsulta;

    @FXML
    private Pane paneListar;

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
    private GridPane gridListaPacientes;

    @FXML
    private Label contadorPacientes;

    @FXML
    private JFXListView<String> lvPacientes;

    @FXML
    private GridPane gridListaMedicos;

    @FXML
    private Label contadorMedicos;

    @FXML
    private JFXListView<String> lvMedicos;

    @FXML
    private Label lblSaudacao;

    @FXML
    private ImageView imvSaudacao;

    @FXML
    void RegistrarConsulta(ActionEvent event) throws SQLException {
        Consulta consulta = new Consulta();
        Paciente paciente = new Paciente();
        Medico medico = new Medico();
        
        

        int idPaciente = 0; // valor padrão ou outro valor que faça sentido no seu contexto

        try {
            idPaciente = Integer.parseInt(id_paciente.getText());
        } catch (NumberFormatException e) {
            exibirAlerta("Erro de entrada", "O ID do paciente deve ser um número inteiro.", Alert.AlertType.ERROR);
            return;
        }
        
        String nomeMedicod = nome_medico.getText();

        // Consulta ao banco de dados para obter informações do paciente
        String sqlConsultaPaciente = "SELECT nome_paciente FROM paciente WHERE id_paciente = ?";

        try (Connection connection = DB.getConnection();
             PreparedStatement pstConsultaPaciente = connection.prepareStatement(sqlConsultaPaciente)) {

            pstConsultaPaciente.setInt(1, idPaciente);
            ResultSet rsPaciente = pstConsultaPaciente.executeQuery();

            if (rsPaciente.next()) {
                nome_paciente.setText(rsPaciente.getString("nome_paciente"));

                // Consulta ao banco de dados para obter o ID do médico pelo nome
                String nomeMedico = nomeMedicod;  // Substitua "nome_medico_aqui" pelo nome do médico fornecido pelo usuário
                String sqlConsultaMedico = "SELECT id_medico FROM medicos WHERE nome_medico = ?";

                try (PreparedStatement pstConsultaMedico = connection.prepareStatement(sqlConsultaMedico)) {
                    pstConsultaMedico.setString(1, nomeMedico);
                    ResultSet rsMedico = pstConsultaMedico.executeQuery();

                    if (rsMedico.next()) {
                        int idMedico = rsMedico.getInt("id_medico");
                        medico.setId_medico(idMedico);

                        // Continue com o restante do código para registrar a consulta
                        LocalDate dataConsulta = data_consulta.getValue();
                        consulta.setData_Consulta(dataConsulta.toString());
                        consulta.setSala_Consulta((String) sala_consulta.getSelectionModel().getSelectedItem());
                        LocalTime horarioConsulta = LocalTime.parse((CharSequence) horario_consulta.getSelectionModel().getSelectedItem());
                        paciente.setId_paciente(idPaciente);

                        String sqlRegistroConsulta = "INSERT INTO consulta (data_consulta, sala_consulta, horario_consulta, fk_paciente, fk_medico) VALUES (?, ?, ?, ?, ?)";

                        try (PreparedStatement pstRegistroConsulta = connection.prepareStatement(sqlRegistroConsulta)) {
                            pstRegistroConsulta.setDate(1, java.sql.Date.valueOf(dataConsulta));
                            pstRegistroConsulta.setString(2, consulta.getSala_Consulta());
                            pstRegistroConsulta.setTime(3, java.sql.Time.valueOf(horarioConsulta));
                            pstRegistroConsulta.setInt(4, paciente.getId_paciente());
                            pstRegistroConsulta.setInt(5, medico.getId_medico());

                            int rowsAffected = pstRegistroConsulta.executeUpdate();

                            if (rowsAffected > 0) {
                                exibirAlerta("Sucesso", "Consulta registrada com sucesso.", Alert.AlertType.INFORMATION);
                                data_consulta.setValue(null);
                                sala_consulta.setValue(null);
                                horario_consulta.setValue(null);
                            } else {
                                exibirAlerta("Falha", "Falha ao inserir o registro da consulta.", Alert.AlertType.ERROR);
                            }

                        } catch (SQLException e) {
                            exibirAlerta("Erro", "Erro ao criar consulta: " + e.getMessage(), Alert.AlertType.ERROR);
                        }

                    } else {
                        exibirAlerta("Médico não encontrado", "Não foi possível encontrar um médico com o nome fornecido.", Alert.AlertType.ERROR);
                    }

                } catch (SQLException e) {
                    exibirAlerta("Erro", "Erro ao consultar médico: " + e.getMessage(), Alert.AlertType.ERROR);
                }

            } else {
                exibirAlerta("Paciente não encontrado", "Não foi possível encontrar um paciente com o ID fornecido.", Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            exibirAlerta("Erro", "Erro ao consultar paciente: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    
    
    @FXML
    void registrarPaciente(ActionEvent event) {
        // Lógica para registrar paciente
        Paciente paciente = new Paciente();
        
        paciente.setNome_paciente(nome_paciente.getText());
        System.out.println(paciente.getNome_paciente());
        paciente.setCpf(cpf_paciente.getText());
        paciente.setRg_paciente(rg_paciente.getText());
        paciente.setTelefone(telefone_paciente.getText());
        paciente.setEmail(email_paciente.getText());
        Object selectedSexoPaciente = sexo_paciente.getSelectionModel().getSelectedItem();
        paciente.setSexo(selectedSexoPaciente != null ? selectedSexoPaciente.toString() : null);

        LocalDate dataNascimentoPaciente = data_nascimento_paciente.getValue();

        if (dataNascimentoPaciente != null) {
            paciente.setData_nasc(dataNascimentoPaciente.toString());
        } else {
            exibirAlerta("Falha", "Data de nascimento vazia.", Alert.AlertType.ERROR);
        }

        // Verifica se o item está selecionado antes de chamar toString()
        Object selectedEstadoCivil = estado_civil.getSelectionModel().getSelectedItem();
        paciente.setEstado_civil(selectedEstadoCivil != null ? selectedEstadoCivil.toString() : null);
        
        Object selectedTipoSanguineo = tipo_sanguineo_paciente.getSelectionModel().getSelectedItem();
        paciente.setTipo_sanguineo_paciente(selectedTipoSanguineo != null ? selectedTipoSanguineo.toString() : null);



        paciente.setContato_emergencia(contato_emergencia.getText());
        paciente.setPeso_paciente(peso_paciente.getText());
        paciente.setPlano_saude(plano_saude.getText());


        paciente.setAltura_paciente(altura_paciente.getText());
        paciente.setCep_paciente(cep_paciente.getText());
        paciente.setRua_paciente(rua_paciente.getText());
        paciente.setNumero_paciente(rua_numero_paciente.getText());
        paciente.setComplementos_paciente(complementos_paciente.getText());

        String sql = "INSERT INTO paciente (nome_paciente, cfp_paciente, rg_paciente, telefone_paciente, email_paciente, sexo_paciente, data_nascimento, estado_civil, contato_emergencia, peso_paciente, plano_saude, tipo_sanguineo_paciente, altura_paciente, cep_paciente, rua_paciente, numero_paciente, complementos_paciente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DB.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setString(1, paciente.getNome_paciente());
            pst.setString(2, paciente.getCpf());
            pst.setString(3, paciente.getRg_paciente());
            pst.setString(4, paciente.getTelefone());
            pst.setString(5, paciente.getEmail());
            pst.setString(6, paciente.getSexo());
            pst.setDate(7, java.sql.Date.valueOf(dataNascimentoPaciente));
            pst.setString(8, paciente.getEstado_civil());
            pst.setString(9, paciente.getContato_emergencia());
            pst.setString(10, paciente.getPeso_paciente());
            pst.setString(11, paciente.getPlano_saude());
            pst.setString(12, paciente.getTipo_sanguineo_paciente());
            pst.setString(13, paciente.getAltura_paciente());
            pst.setString(14, paciente.getCep_paciente());
            pst.setString(15, paciente.getRua_paciente());
            pst.setString(16, paciente.getNumero_paciente());
            pst.setString(17, paciente.getComplementos_paciente());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                exibirAlerta("Sucesso", "Paciente registrado com sucesso.", Alert.AlertType.INFORMATION);
                // Adicione aqui qualquer lógica adicional necessária após a inserção bem-sucedida.
                nome_paciente.setText("");
                cpf_paciente.setText("");
                rg_paciente.setText("");
                telefone_paciente.setText("");
                email_paciente.setText("");
                sexo_paciente.setValue(null);
                data_nascimento_paciente.setValue(null);
                estado_civil.setValue(null);
                contato_emergencia.setText("");
                peso_paciente.setText("");
                plano_saude.setText("");
                tipo_sanguineo_paciente.setValue(null);
                altura_paciente.setText("");
                cep_paciente.setText("");
                rua_paciente.setText("");
                rua_numero_paciente.setText("");
                complementos_paciente.setText("");
            } else {
                exibirAlerta("Falha", "Falha ao inserir o registro do paciente.", Alert.AlertType.ERROR);
                // Adicione aqui qualquer lógica de tratamento de erro necessária.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Falha ao inserir o registro do paciente. Exceção: " + e.getMessage());
            exibirAlerta("Falha", "Falha ao inserir o registro do paciente." + e.getMessage(), Alert.AlertType.ERROR);
            // Adicione aqui qualquer lógica de tratamento de erro necessária.
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            System.out.println("Falha ao converter datas. Exceção: " + e.getMessage());
            // Adicione aqui qualquer lógica de tratamento de erro necessária.
        }
    }
    void carregarPacientesLista(){
    ObservableList<String> listaPacientes = FXCollections.observableArrayList();
    
    String sql = "SELECT nome_paciente FROM paciente"; // Pode ajustar a query conforme necessário

    try (Connection connection = DB.getConnection();
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String nomePaciente = rs.getString("nome_paciente");
            listaPacientes.add(nomePaciente);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        // Adicione a lógica de tratamento de erro aqui
    }

    lvPacientes.setItems(listaPacientes);    
    }
    
    void carregarConsAgendadaLista(){
        ObservableList<String> listaPacientes = FXCollections.observableArrayList();

    String sql = "SELECT p.nome_paciente FROM consulta c " +
                 "JOIN paciente p ON c.fk_paciente = p.id_paciente";

    try (Connection connection = DB.getConnection();
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String nomePaciente = rs.getString("nome_paciente");
            listaPacientes.add(nomePaciente);
        }

        } catch (SQLException e) {
            e.printStackTrace();
        // Adicione a lógica de tratamento de erro aqui
    }

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
    
    void carregarMedicosLista(){
        ObservableList<String> listaMedicos = FXCollections.observableArrayList();
    
    String sql = "SELECT nome_medico FROM medicos"; // Pode ajustar a query conforme necessário

    try (Connection connection = DB.getConnection();
         Statement stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String nomeMedico = rs.getString("nome_medico");
            listaMedicos.add(nomeMedico);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        // Adicione a lógica de tratamento de erro aqui
    }

    lvMedicos.setItems(listaMedicos);
    }

    @FXML
    void abreGridPacientesLista(ActionEvent event) {
        // Lógica para abrir a lista de pacientes
        lvPacientes.setVisible(true);
        gridListaPacientes.setVisible(true);
        gridListaMedicos.setVisible(false);
        gridListaHistoricoCons.setVisible(false);
        gridListaConsAgendadas.setVisible(false); 
        
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);
        carregarPacientesLista();
    
    }
    
    
    
    @FXML
    void abreGridConsAgendadaLista(ActionEvent event) {
        gridListaMedicos.setVisible(false);
        gridListaPacientes.setVisible(false);
        gridListaHistoricoCons.setVisible(false);
        gridListaConsAgendadas.setVisible(true);
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);
        
        carregarConsAgendadaLista();
    }
    
    
    @FXML
    void abreGridHistoricoConsLista(ActionEvent event){
        gridListaMedicos.setVisible(false);
        gridListaPacientes.setVisible(false);
        gridListaHistoricoCons.setVisible(true);
        gridListaConsAgendadas.setVisible(false);
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);
        
    }
    
    @FXML
    void abreGridMedicosLista(ActionEvent event) {
        lvMedicos.setVisible(true);
        gridListaMedicos.setVisible(true);
        gridListaPacientes.setVisible(false);
        gridListaHistoricoCons.setVisible(false);
        gridListaConsAgendadas.setVisible(false);
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);

        carregarMedicosLista();
    }

    @FXML
    void abrirSecaoConsulta(ActionEvent event) {
         paneRegistro.setVisible(false);
         paneConsulta.setVisible(true);
         paneListar.setVisible(false);
         lblSaudacao.setVisible(false);
          imvSaudacao.setVisible(false);
         lblSaudacao.setVisible(false);
         

    }

    @FXML
    void abrirSecaoLIstar(ActionEvent event) {
         paneRegistro.setVisible(false);
         paneConsulta.setVisible(false);
         paneListar.setVisible(true);
         lblSaudacao.setVisible(false);
          imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);
         
         carregarConsAgendadaLista();
         carregarMedicosLista();
         carregarPacientesLista();
         carregarHistoricoCons();
    }

    @FXML
    void abrirSecaoRegistro(ActionEvent event) {
         paneRegistro.setVisible(true);
         paneConsulta.setVisible(false);
         paneListar.setVisible(false);
         lblSaudacao.setVisible(false);
          imvSaudacao.setVisible(false);

    }



    @FXML
    void removerConsAgendadas(ActionEvent event) {

    }

    @FXML
    void removerHistoricoConsLista(ActionEvent event) {

    }

    @FXML
    void removerMedicosLista(ActionEvent event) {

    }

    @FXML
    void removerPacientesLista(ActionEvent event) {

    }

    @FXML
    void sair(ActionEvent event) {
        Main.trocarTela("login");

    }
    
    private void exibirDetalhesMedico(String nomeMedico) {
    // Lógica para exibir detalhes do médico selecionado
    String sql = "SELECT m.*, e.nome_esp FROM medicos m " +
                 "JOIN especialidade e ON m.fk_especialidade = e.id_esp " +
                 "WHERE m.nome_medico = ?";

    try (Connection connection = DB.getConnection();
         PreparedStatement pst = connection.prepareStatement(sql)) {

        pst.setString(1, nomeMedico);

        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                String nomeMedicoExibicao = rs.getString("nome_medico");
                String cpfMedico = rs.getString("cpf_medico");
                String telefoneMedico = rs.getString("telefone_medico");
                String crmMedico = rs.getString("crm_medico");
                String emailMedico = rs.getString("email_medico");
                String sexoMedico = rs.getString("sexo_medico");
                String horarioTrabalho = rs.getString("horario_trabalho");
                String dataNascimento = rs.getString("data_nascimento");
                String admissaoMedico = rs.getString("admissao_medico");
                String especialidadeMedico = rs.getString("nome_esp");

                // Agora você pode exibir essas informações conforme necessário
                System.out.println("Nome: " + nomeMedicoExibicao);
                System.out.println("CPF: " + cpfMedico);
                System.out.println("Telefone: " + telefoneMedico);
                System.out.println("CRM: " + crmMedico);
                System.out.println("E-mail: " + emailMedico);
                System.out.println("Sexo: " + sexoMedico);
                System.out.println("Horário de Trabalho: " + horarioTrabalho);
                System.out.println("Data de Nascimento: " + dataNascimento);
                System.out.println("Admissão: " + admissaoMedico);
                System.out.println("Especialidade: " + especialidadeMedico);

                // Aqui você pode implementar a lógica para exibir essas informações
                // Pode usar labels, pop-ups, etc.
                exibirAlerta(nomeMedicoExibicao, 
                        "Especialidade: "+especialidadeMedico+
                        "\nHorário:"+ horarioTrabalho+
                        "\nCRM: "+ crmMedico +
                        "\nAdmissão: "+admissaoMedico, 
                        Alert.AlertType.INFORMATION);
            } else {
                System.out.println("Médico não encontrado");
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        // Adicione a lógica de tratamento de erro aqui
    }
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
    
    private void exibirDetalhesPaciente(String nomePaciente) {
    String sql = "SELECT * FROM paciente WHERE nome_paciente = ?";

    try (Connection connection = DB.getConnection();
         PreparedStatement pst = connection.prepareStatement(sql)) {

        pst.setString(1, nomePaciente);

        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                int idPaciente = rs.getInt("id_paciente");
                String dataNascimento = rs.getString("data_nascimento");
                long cepPaciente = rs.getLong("CEP_paciente");
                String ruaPaciente = rs.getString("rua_paciente");
                int numeroPaciente = rs.getInt("numero_paciente");
                float alturaPaciente = rs.getFloat("altura_paciente");
                float pesoPaciente = rs.getFloat("peso_paciente");
                String complementosPaciente = rs.getString("complementos_paciente");
                long telefonePaciente = rs.getLong("telefone_paciente");
                long cpfPaciente = rs.getLong("cfp_paciente");
                long rgPaciente = rs.getLong("rg_paciente");
                String planoSaude = rs.getString("plano_saude");
                char sexoPaciente = rs.getString("sexo_paciente").charAt(0);
                String emailPaciente = rs.getString("email_paciente");
                long contatoEmergencia = rs.getLong("contato_emergencia");
                String estadoCivil = rs.getString("estado_civil");
                String tipoSanguineoPaciente = rs.getString("tipo_sanguineo_paciente");
                String alergiasPaciente = rs.getString("alergias_paciente");
                String observacaoPaciente = rs.getString("observacao_paciente");

                // Aqui você pode implementar a lógica para exibir essas informações
                // Pode usar labels, pop-ups, etc.
                exibirAlerta("Detalhes do Paciente", "ID: " + idPaciente +
                        "\nNome: " + nomePaciente +
                        "\nData de Nascimento: " + dataNascimento +
                        "\nCEP: " + cepPaciente +
                        "\nRua: " + ruaPaciente +
                        "\nNúmero: " + numeroPaciente +
                        "\nAltura: " + alturaPaciente +
                        "\nPeso: " + pesoPaciente +
                        "\nComplementos: " + complementosPaciente +
                        "\nTelefone: " + telefonePaciente +
                        "\nCPF: " + cpfPaciente +
                        "\nRG: " + rgPaciente +
                        "\nPlano de Saúde: " + planoSaude +
                        "\nSexo: " + sexoPaciente +
                        "\nEmail: " + emailPaciente +
                        "\nContato de Emergência: " + contatoEmergencia +
                        "\nEstado Civil: " + estadoCivil +
                        "\nTipo Sanguíneo: " + tipoSanguineoPaciente +
                        "\nAlergias: " + alergiasPaciente +
                        "\nObservação: " + observacaoPaciente, Alert.AlertType.INFORMATION);

                System.out.println("ID: " + idPaciente +
                        "\nNome: " + nomePaciente +
                        "\nData de Nascimento: " + dataNascimento +
                        "\nCEP: " + cepPaciente +
                        "\nRua: " + ruaPaciente +
                        "\nNúmero: " + numeroPaciente +
                        "\nAltura: " + alturaPaciente +
                        "\nPeso: " + pesoPaciente +
                        "\nComplementos: " + complementosPaciente +
                        "\nTelefone: " + telefonePaciente +
                        "\nCPF: " + cpfPaciente +
                        "\nRG: " + rgPaciente +
                        "\nPlano de Saúde: " + planoSaude +
                        "\nSexo: " + sexoPaciente +
                        "\nEmail: " + emailPaciente +
                        "\nContato de Emergência: " + contatoEmergencia +
                        "\nEstado Civil: " + estadoCivil +
                        "\nTipo Sanguíneo: " + tipoSanguineoPaciente +
                        "\nAlergias: " + alergiasPaciente +
                        "\nObservação: " + observacaoPaciente);
            } else {
                System.out.println("Paciente não encontrado");
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
    
    @FXML
    void verConsAgendadas(ActionEvent event) {
        // Lógica para visualizar consultas agendadas
        // Lógica para visualizar lista de médicos
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

    @FXML
    void verMedicosLista(ActionEvent event) {
    // Lógica para visualizar lista de médicos
    String nomeMedicoSelecionado = lvMedicos.getSelectionModel().getSelectedItem();

    if (nomeMedicoSelecionado != null) {
        exibirDetalhesMedico(nomeMedicoSelecionado);
    }
    }

    @FXML
    void verPacientesLista(ActionEvent event) {
        String nomePaciente = lvPacientes.getSelectionModel().getSelectedItem();

        if (nomePaciente != null) {
            exibirDetalhesPaciente(nomePaciente);
        }
    }
    
     private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

   

   

}
