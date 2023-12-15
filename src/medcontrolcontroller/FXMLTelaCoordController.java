package medcontrolcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import db.DB;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import medcontrol.Main;
import model.Admin;
import model.Atendente;
import model.Consulta;
import model.Medico;
import model.Paciente;
import model.NomeEsp;
import medcontrolcontroller.FXMLTelaLoginController;

public class FXMLTelaCoordController implements Initializable {

    String nm_userr;

    ObservableList<String> opcoesSexo = FXCollections.observableArrayList(
                "M", "F"
        );
    
    ObservableList<String> opcoesHorarioTra = FXCollections.observableArrayList(
                "08:00:00", "06:00:00"
    );
    
    ObservableList<String> opcoesSalaCon = FXCollections.observableArrayList(
                "SalaA", "SalaB" , "SalaC"
    );
    
    
    ObservableList<String> opcoesHorarioCon = FXCollections.observableArrayList(
                "08:00", "10:00", "14:00", "16:00", "18:00"
    );

    
    ObservableList<String> opcoesEspecialidade = FXCollections.observableArrayList(
                "Cardiologia", "Dermatologia", "Ortopedia"
    );
    
    ObservableList<String> opcoesEstCivil = FXCollections.observableArrayList(
                "Casado", "Solteiro", "Viuvo"
    );
    
    ObservableList<String> opcoesTipoSangue = FXCollections.observableArrayList(
                "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"
    );
  
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView imgvUser;

    @FXML
    private Label lblmatricula;

    @FXML
    private Pane paneRegistro;

    @FXML
    private JFXRadioButton rbtnAtendente;

    @FXML
    private ToggleGroup registros;

    @FXML
    private JFXRadioButton rbtnPaciente;

    @FXML
    private JFXRadioButton rbtnMedico;

    @FXML
    private ScrollPane scrollAtendente;

    @FXML
    private JFXTextField nome_func;

    @FXML
    private JFXTextField cpf_func;

    @FXML
    private JFXTextField telefone_func;

    @FXML
    private JFXTextField email_func;

    @FXML
    private JFXComboBox<String> sexo_func;

    @FXML
    private JFXDatePicker data_nascimento_func;

    @FXML
    private JFXComboBox<String> horario_trabalho_func;

    @FXML
    private JFXTextField usuario_func;
    
    @FXML
    private JFXPasswordField senha_func;

    @FXML
    private JFXDatePicker admissao_func;

    @FXML
    private JFXTextField id_func;
    

    @FXML
    private JFXButton btnRegistrarAtendente;

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
    private JFXTextField numero_paciente;

    @FXML
    private JFXTextField complementos_paciente;

    @FXML
    private JFXButton btnRegistrarPaciente;

    @FXML
    private ScrollPane scrollMedico;
    
    @FXML
    private JFXTextField usuario_medico;

    @FXML
    private JFXPasswordField senha_medico;

    @FXML
    private JFXTextField nome_medico;

    @FXML
    private JFXTextField cpf_medico;

    @FXML
    private JFXTextField crm_medico;

    @FXML
    private JFXTextField id_medico;

    @FXML
    private JFXTextField telefone_medico;

    @FXML
    private JFXTextField email_medico;

    @FXML
    private JFXDatePicker data_nascimento_medico;

    @FXML
    private JFXComboBox<String> sexo_medico;

    @FXML
    private JFXDatePicker admissao_medico;

    @FXML
    private JFXComboBox<String> horario_trabalho;

    @FXML
    private JFXComboBox<String> nome_esp;

    @FXML
    private JFXPasswordField txtSenhaMedico;

    @FXML
    private JFXButton btnRegistrarMedico;

    @FXML
    private Pane paneConsulta;

    @FXML
    private ScrollPane scrollConsulta;

    @FXML
    private JFXDatePicker data_consulta;

    @FXML
    private JFXComboBox<String> sala_consulta;
    
    @FXML
    private JFXTextField id_paciente_cons;

    @FXML
    private JFXTextField nome_paciente_cons;
    
    
    @FXML
    private JFXTextField nome_medico_cons;

    @FXML
    private JFXComboBox<String> horario_consulta;
    
    @FXML
    private JFXComboBox<String> nome_esp_cons;

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
    private JFXButton btnEditAtendente;

    @FXML
    private Label lblSaudacao;

    @FXML
    private ImageView imvSaudacao;

    @FXML
    private Pane telaDeLoading1;

    @FXML
    private ImageView imgvLogo1;
    
    @FXML
    private Button botaoSair;

    @FXML
    private ProgressIndicator indicadorCarregamento;
   



    @FXML
    void abrirSecaoLIstar(ActionEvent event) {       
        paneListar.setVisible(true);
        paneConsulta.setVisible(false);
        paneRegistro.setVisible(false);
        lblSaudacao.setVisible(false);
        imvSaudacao.setVisible(false);
        
        carregarConsAgendadaLista();
        carregarMedicosLista();
        carregarPacientesLista();
        carregarHistoricoCons();
    }

    
    Admin admin = Main.getInstance().admin();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*Image image1 = new Image("/MedControl/images/user.png");
        imgvUser.setImage(image1);
        Image image2 = new Image("/MedControl/images/administrador.boasvindas.png");
        imvSaudacao.setImage(image2);*/
        
      
        
        

        lblmatricula.setVisible(true);
        telaDeLoading1.setVisible(true);
        
        //MOSTRANDO COMO TELA DE INICIO
        imvSaudacao.setVisible(true);
        lblSaudacao.setVisible(true);
        
        //DEIXANDO INVISIVEL OS MENUS NAO ATIVOS
        paneConsulta.setVisible(false);
        paneRegistro.setVisible(false); 
        paneListar.setVisible(false);
        
        //DEFININDO INVISIVEL 
        scrollAtendente.setVisible(false);
        scrollMedico.setVisible(false);
        scrollPaciente.setVisible(false);
        
        sexo_func.setItems(opcoesSexo);
        horario_trabalho_func.setItems(opcoesHorarioTra);
        
        sala_consulta.setItems(opcoesSalaCon);
        horario_consulta.setItems(opcoesHorarioCon);
        
       
        
        System.out.println(opcoesTipoSangue);
       
        
        horario_trabalho.setItems(opcoesHorarioTra);
        nome_esp.setItems(opcoesEspecialidade);
        sexo_medico.setItems(opcoesSexo);
        sexo_paciente.setItems(opcoesSexo);
        estado_civil.setItems(opcoesEstCivil);
        tipo_sanguineo_paciente.setItems(opcoesTipoSangue);
        nome_esp_cons.setItems(opcoesEspecialidade);
       
        
    }
     
     
    private FXMLTelaLoginController telaLoginController;
    
    public void setTelaLoginController(FXMLTelaLoginController telaLoginController) {
        if (telaLoginController != null) {
                this.telaLoginController = telaLoginController;
            telaLoginController.nmUserProperty().addListener((observable, oldValue, newValue) -> {
                // Faça algo com newValue...
                nm_userr = newValue;
            });
        } else {
            System.err.println("O objeto telaLoginController é nulo!");
        }
    }
    
    
    
    @FXML
    void RegistrarConsulta(ActionEvent event) throws SQLException {
        Consulta consulta = new Consulta();
        Paciente paciente = new Paciente();
        Medico medico = new Medico();
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);
        

        int idPaciente;

        try {
            idPaciente = Integer.parseInt(id_paciente_cons.getText());
        } catch (NumberFormatException e) {
            exibirAlerta("Erro de entrada", "O ID do paciente deve ser um número inteiro.", AlertType.ERROR);
            return;
        }
        
        String nomeMedicod = nome_medico_cons.getText();

        // Consulta ao banco de dados para obter informações do paciente
        String sqlConsultaPaciente = "SELECT nome_paciente FROM paciente WHERE id_paciente = ?";

        try (Connection connection = DB.getConnection();
             PreparedStatement pstConsultaPaciente = connection.prepareStatement(sqlConsultaPaciente)) {

            pstConsultaPaciente.setInt(1, idPaciente);
            ResultSet rsPaciente = pstConsultaPaciente.executeQuery();

            if (rsPaciente.next()) {
                nome_paciente_cons.setText(rsPaciente.getString("nome_paciente"));

                // Consulta ao banco de dados para obter o ID do médico pelo nome
                String nomeMedico = nomeMedicod;
                String sqlConsultaMedico = "SELECT id_medico FROM medicos WHERE nome_medico = ?";

                try (PreparedStatement pstConsultaMedico = connection.prepareStatement(sqlConsultaMedico)) {
                    pstConsultaMedico.setString(1, nomeMedico);
                    ResultSet rsMedico = pstConsultaMedico.executeQuery();

                    if (rsMedico.next()) {
                        int idMedico = rsMedico.getInt("id_medico");
                        medico.setId_medico(idMedico);
                        
                        // Continue com o restante do código para registrar a consulta
                        String especialidade = nome_esp_cons.getSelectionModel().getSelectedItem().toString();
                        LocalDate dataConsulta = data_consulta.getValue();
                        consulta.setData_Consulta(dataConsulta.toString());
                        String sala_consulta_new = sala_consulta.getSelectionModel().getSelectedItem();
                        LocalTime horarioConsulta = LocalTime.parse(horario_consulta.getSelectionModel().getSelectedItem());
                        paciente.setId_paciente(idPaciente);
                        int id_especialidade = 0;
                        
                        switch(especialidade){
                            case"Cardiologia":id_especialidade = 1;break;
                            case"Dermatologia":id_especialidade = 2;break;
                            case"Ortopedia":id_especialidade = 3;break;
                            default: id_especialidade = 0;
                        }
                        

                        String sqlRegistroConsulta = "INSERT INTO consulta (data_consulta, sala_consulta, horario_consulta, fk_paciente, fk_medico, fk_especialidade) VALUES (?, ?, ?, ?, ?, ?)";

                        try (PreparedStatement pstRegistroConsulta = connection.prepareStatement(sqlRegistroConsulta)) {
                            pstRegistroConsulta.setDate(1, java.sql.Date.valueOf(dataConsulta));
                            pstRegistroConsulta.setString(2, sala_consulta_new);
                            pstRegistroConsulta.setTime(3, java.sql.Time.valueOf(horarioConsulta));
                            pstRegistroConsulta.setInt(4, paciente.getId_paciente());
                            pstRegistroConsulta.setInt(5, medico.getId_medico());
                            pstRegistroConsulta.setInt(6, id_especialidade);

                            int rowsAffected = pstRegistroConsulta.executeUpdate();

                            if (rowsAffected > 0) {
                                exibirAlerta("Sucesso", "Consulta registrada com sucesso.", AlertType.INFORMATION);
                                data_consulta.setValue(null);
                                sala_consulta.setValue(null);
                                horario_consulta.setValue(null);
                            } else {
                                exibirAlerta("Falha", "Falha ao inserir o registro da consulta.", AlertType.ERROR);
                            }
                            
                        } catch (SQLException e) {
                            exibirAlerta("Erro", "Erro ao criar consulta: " + e.getMessage(), AlertType.ERROR);
                        }

                    } else {
                        exibirAlerta("Médico não encontrado", "Não foi possível encontrar um médico com o nome fornecido.", AlertType.ERROR);
                    }

                } catch (SQLException e) {
                    exibirAlerta("Erro", "Erro ao consultar médico: " + e.getMessage(), AlertType.ERROR);
                }

            } else {
                exibirAlerta("Paciente não encontrado", "Não foi possível encontrar um paciente com o ID fornecido.", AlertType.ERROR);
            }

        } catch (SQLException e) {
            exibirAlerta("Erro", "Erro ao consultar paciente: " + e.getMessage(), AlertType.ERROR);
        }
    }
    
    void carregarPacientesLista(){
        ObservableList<String> listaPacientes = FXCollections.observableArrayList();
        int contador = 0;
        String sql = "SELECT nome_paciente FROM paciente"; // Pode ajustar a query conforme necessário

        try (Connection connection = DB.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nomePaciente = rs.getString("nome_paciente");
                listaPacientes.add(nomePaciente);
                contador++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Adicione a lógica de tratamento de erro aqui
        }
        contadorPacientes.setText(""+contador);
        lvPacientes.setItems(listaPacientes);    
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
    
    void carregarMedicosLista(){
        ObservableList<String> listaMedicos = FXCollections.observableArrayList();
        int contador = 0;
        String sql = "SELECT nome_medico FROM medicos"; // Pode ajustar a query conforme necessário

        try (Connection connection = DB.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nomeMedico = rs.getString("nome_medico");
                listaMedicos.add(nomeMedico);
                contador++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Adicione a lógica de tratamento de erro aqui
        }
        contadorMedicos.setText(""+contador);
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
        carregarPacientesLista();
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);
    
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
    void abreScrollAtendente(ActionEvent event) {
        // Lógica para abrir a seção de registro de atendente
        scrollAtendente.setVisible(true);
        scrollMedico.setVisible(false);
        scrollPaciente.setVisible(false);
        
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);

        btnRegistrarAtendente.setVisible(true);
        btnRegistrarMedico.setVisible(false);
        btnRegistrarPaciente.setVisible(false);
    }

    @FXML
    void abreScrollMedico(ActionEvent event) {
        // Lógica para abrir a seção de registro de médico
        scrollPaciente.setVisible(false);
        scrollAtendente.setVisible(false);
        scrollMedico.setVisible(true);
        
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);
        
        btnRegistrarAtendente.setVisible(false);
        btnRegistrarMedico.setVisible(true);
        btnRegistrarPaciente.setVisible(false);
        
        

    }

    @FXML
    void abreScrollPaciente(ActionEvent event) {
        // Lógica para abrir a seção de registro de paciente
        scrollPaciente.setVisible(true);
        scrollMedico.setVisible(false);
        scrollAtendente.setVisible(false);
        
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);

        btnRegistrarAtendente.setVisible(false);
        btnRegistrarMedico.setVisible(false);
        btnRegistrarPaciente.setVisible(true);
    }

    @FXML
    void abrirSecaoConsulta(ActionEvent event) {
        // Lógica para abrir a seção de consulta
        paneConsulta.setVisible(true);
        paneRegistro.setVisible(false); 
        paneListar.setVisible(false);
        
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);
    }
    

    @FXML
    void abrirSecaoRegistro(ActionEvent event) {
        // Lógica para abrir a seção de registro
        paneRegistro.setVisible(true);
        paneConsulta.setVisible(false);
        paneListar.setVisible(false);
        scrollAtendente.setVisible(true);
        imvSaudacao.setVisible(false);
        lblSaudacao.setVisible(false);
    }


    @FXML
    void registrarAtendente(ActionEvent event) throws SQLException {
        // Initialize Atendente object
        Atendente atendente = new Atendente();
        int chavePrimaria = 0;
        String sql2 = "select * from ControleAcesso where nome_usuario = ?;";
        String sql3 = "insert into ControleAcesso(nome_usuario, senha_usuario, nivel_acesso) values (?,?,?)";
        try(Connection connection = DB.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql2)){
            
            String user = usuario_func.getText();
            String senha = senha_func.getText();
            String acesso = "funcionario";
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                exibirAlerta("Login já existente", "Login de usuário já existente", AlertType.INFORMATION);

            } else {
                 PreparedStatement stmt2 = connection.prepareStatement(sql3,Statement.RETURN_GENERATED_KEYS);
                stmt2.setString(1, user);
                stmt2.setString(2, senha);
                stmt2.setString(3,acesso);
                int rowsAffected2 = stmt2.executeUpdate();
                
                //Código para recuperar a chave 
                if (rowsAffected2 > 0) {
                // Recupera as chaves geradas
                    ResultSet generatedKeys = stmt2.getGeneratedKeys();
                    if (generatedKeys.next()) {
                    chavePrimaria = generatedKeys.getInt(1);
                    // Faça o que precisar com a chave primária gerada
                    System.out.println("ID gerado: " + chavePrimaria);
                    } else {
                    // Tratar caso as chaves não tenham sido geradas
                    System.out.println("Nenhuma chave gerada.");
                }
       
       } else {
           // Tratar caso nenhum registro tenha sido afetado
           System.out.println("Nenhum registro afetado.");
       }
                 // Lógica para registrar atendente
                atendente.setNome(nome_func.getText());
                atendente.setCpf(cpf_func.getText());
                atendente.setEmail(email_func.getText());
                //atendente.setEmail(email_atendente.getText());

                // Verifica se o item está selecionado antes de chamar toString()
                Object selectedSexo = sexo_func.getSelectionModel().getSelectedItem();
                atendente.setSexo(selectedSexo != null ? selectedSexo.toString() : null);

                // Verifica se o item está selecionado antes de chamar toString()
                Object selectedHorario = horario_trabalho_func.getSelectionModel().getSelectedItem();
                atendente.setHr_trab_atendente(selectedHorario != null ? selectedHorario.toString() : null);

                LocalDate dataNascimento = data_nascimento_func.getValue();

                if (dataNascimento != null) {
                    atendente.setData_nasc(dataNascimento.toString());
                } else {
                    exibirAlerta("Falha", "Data de nasciemnto vazio.", AlertType.ERROR);
                }

                LocalDate admissao = admissao_func.getValue();
                if (admissao != null) {
                    atendente.setAdmissao_atendente(admissao.toString());
                } else {
                    exibirAlerta("Falha", "data admissao_func", AlertType.ERROR);
                }
                atendente.setTelefone(telefone_func.getText());

                String sql = "insert into funcionario (nome_func, cpf_func, telefone_func, email_func, sexo_func, horario_trabalho, data_nascimento, admissao_func, fk_acesso) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement pst = connection.prepareStatement(sql)) {

                    pst.setString(1, atendente.getNome());
                    pst.setString(2, atendente.getCpf());
                    pst.setString(3, atendente.getTelefone());
                    pst.setString(4, atendente.getEmail());
                    pst.setString(5, atendente.getSexo());
                    pst.setString(6, atendente.getHr_trab_atendente());
                    pst.setInt(9, chavePrimaria);

                    // Assuming getData_nasc() and getAdmissao() return LocalDate

                    // Additional logging for debugging
                    System.out.println("dataNascimento: " + dataNascimento);
                    System.out.println("admissao: " + admissao);

                    pst.setDate(7, java.sql.Date.valueOf(dataNascimento));
                    pst.setDate(8, java.sql.Date.valueOf(admissao));

                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0 && rowsAffected2 > 0) {
                       exibirAlerta("Sucesso", "Atendente registrado com sucesso.", AlertType.INFORMATION);
                        // Adicione aqui qualquer lógica adicional necessária após a inserção bem-sucedida.
                        nome_func.setText("");
                        cpf_func.setText("");
                        sexo_func.setValue(null);
                        data_nascimento_func.setValue(null);
                        admissao_func.setValue(null);
                        telefone_func.setText("");
                        usuario_func.setText("");
                        senha_func.setText("");



                    } else {
                       exibirAlerta("Falha", "Falha ao inserir o registro do atendente.", AlertType.ERROR);
                        // Adicione aqui qualquer lógica de tratamento de erro necessária.
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Falha ao inserir o registro do atendente. Exceção: " + e.getMessage());
                    exibirAlerta("Falha", "Falha ao inserir o registro do atendente." + e.getMessage(), AlertType.ERROR);
                    // Adicione aqui qualquer lógica de tratamento de erro necessária.
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                    System.out.println("Falha ao converter datas. Exceção: " + e.getMessage());
                    // Adicione aqui qualquer lógica de tratamento de erro necessária.
                }
                
            }
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
            exibirAlerta("Falha", "Data de nascimento vazia.", AlertType.ERROR);
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
        paciente.setNumero_paciente(numero_paciente.getText());
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
                exibirAlerta("Sucesso", "Paciente registrado com sucesso.", AlertType.INFORMATION);
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
                numero_paciente.setText("");
                complementos_paciente.setText("");
            } else {
                exibirAlerta("Falha", "Falha ao inserir o registro do paciente.", AlertType.ERROR);
                // Adicione aqui qualquer lógica de tratamento de erro necessária.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Falha ao inserir o registro do paciente. Exceção: " + e.getMessage());
            exibirAlerta("Falha", "Falha ao inserir o registro do paciente." + e.getMessage(), AlertType.ERROR);
            // Adicione aqui qualquer lógica de tratamento de erro necessária.
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            System.out.println("Falha ao converter datas. Exceção: " + e.getMessage());
            // Adicione aqui qualquer lógica de tratamento de erro necessária.
        }
    }

    
  @FXML
    void registrarMedico(ActionEvent event) throws SQLException {
        // Inicializar objeto Médico
        Medico medico = new Medico();
        int chavePrimaria = 0;
        String sql2 = "SELECT * FROM ControleAcesso WHERE nome_usuario = ?;";
        String sql3 = "INSERT INTO ControleAcesso(nome_usuario, senha_usuario, nivel_acesso) VALUES (?,?,?)";

        try (Connection connection = DB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql2)) {

            String user = usuario_medico.getText();
            String senha = senha_medico.getText();
            String acesso = "medico";
            stmt.setString(1, user);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                exibirAlerta("Login já existente", "Login de usuário já existente", AlertType.INFORMATION);
            } else {
                PreparedStatement stmt2 = connection.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
                stmt2.setString(1, user);
                stmt2.setString(2, senha);
                stmt2.setString(3, acesso);
                int rowsAffected2 = stmt2.executeUpdate();

                // Código para recuperar a chave
                if (rowsAffected2 > 0) {
                    // Recupera as chaves geradas
                    ResultSet generatedKeys = stmt2.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        chavePrimaria = generatedKeys.getInt(1);
                        // Faça o que precisar com a chave primária gerada
                        System.out.println("ID gerado: " + chavePrimaria);
                    } else {
                        // Tratar caso as chaves não tenham sido geradas
                        System.out.println("Nenhuma chave gerada.");
                    }
                } else {
                    // Tratar caso nenhum registro tenha sido afetado
                    System.out.println("Nenhum registro afetado.");
                }

                // Lógica para registrar médico
                String nomeMedico = nome_medico.getText();
                String cpfMedico = cpf_medico.getText();
                String telefoneMedico = telefone_medico.getText();
                String crmMedico = crm_medico.getText();
                String emailMedico = email_medico.getText();
                String sexoMedico = sexo_medico.getSelectionModel().getSelectedItem().toString();
                String horarioTrabalho = horario_trabalho.getSelectionModel().getSelectedItem().toString();
                LocalDate dataNascimento = data_nascimento_medico.getValue();
                LocalDate admissao = admissao_medico.getValue();
                String especialidade = nome_esp.getSelectionModel().getSelectedItem().toString();
                
                int id_especialidade;
                
                switch(especialidade){
                    case"Cardiologia":id_especialidade = 1;break;
                    case"Dermatologia":id_especialidade = 2;break;
                    case"Ortopedia":id_especialidade = 3;break;
                    default: id_especialidade = 0;
                }
                

                String sql = "INSERT INTO medicos (nome_medico, cpf_medico, telefone_medico, crm_medico, email_medico, sexo_medico, horario_trabalho, data_nascimento, admissao_medico, fk_especialidade, fk_acesso) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement pst = connection.prepareStatement(sql)) {

                    pst.setString(1, nomeMedico);
                    pst.setString(2, cpfMedico);
                    pst.setString(3, telefoneMedico);
                    pst.setString(4, crmMedico);
                    pst.setString(5, emailMedico);
                    pst.setString(6, sexoMedico);
                    pst.setString(7, horarioTrabalho);
                    pst.setDate(8, java.sql.Date.valueOf(dataNascimento));
                    pst.setDate(9, java.sql.Date.valueOf(admissao));
                    pst.setInt(10, id_especialidade);
                    pst.setInt(11, chavePrimaria); // Substitua 1 pelo ID da especialidade correspondente

                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0 && rowsAffected2 > 0) {
                        exibirAlerta("Sucesso", "Médico registrado com sucesso.", AlertType.INFORMATION);

                        // Adicione aqui qualquer lógica adicional necessária após a inserção bem-sucedida.
                        nome_medico.setText("");
                        cpf_medico.setText("");
                        telefone_medico.setText("");
                        crm_medico.setText("");
                        email_medico.setText("");
                        sexo_medico.setValue(null);
                        horario_trabalho.setValue(null);
                        data_nascimento_medico.setValue(null);
                        admissao_medico.setValue(null);
                        usuario_medico.setText("");
                        senha_medico.setText("");
                        nome_esp.setValue(null);

                    } else {
                        exibirAlerta("Falha", "Falha ao inserir o registro do médico.", AlertType.ERROR);
                        // Adicione aqui qualquer lógica de tratamento de erro necessária.
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Falha ao inserir o registro do médico. Exceção: " + e.getMessage());
                    exibirAlerta("Falha", "Falha ao inserir o registro do médico." + e.getMessage(), AlertType.ERROR);
                    // Adicione aqui qualquer lógica de tratamento de erro necessária.
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                    System.out.println("Falha ao converter datas. Exceção: " + e.getMessage());
                    // Adicione aqui qualquer lógica de tratamento de erro necessária.
                }
            }
        }
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

                /* Agora você pode exibir essas informações conforme necessário
                System.out.println("Nome: " + nomeMedicoExibicao);
                System.out.println("CPF: " + cpfMedico);
                System.out.println("Telefone: " + telefoneMedico);
                System.out.println("CRM: " + crmMedico);
                System.out.println("E-mail: " + emailMedico);
                System.out.println("Sexo: " + sexoMedico);
                System.out.println("Horário de Trabalho: " + horarioTrabalho);
                System.out.println("Data de Nascimento: " + dataNascimento);
                System.out.println("Admissão: " + admissaoMedico);
                System.out.println("Especialidade: " + especialidadeMedico);*/

                // Aqui você pode implementar a lógica para exibir essas informações
                // Pode usar labels, pop-ups, etc.
                exibirAlerta(nomeMedicoExibicao, 
                        "Especialidade: "+especialidadeMedico+
                        "\nHorário: "+ horarioTrabalho+
                        "\nCRM: "+ crmMedico +
                        "\nAdmissão: "+admissaoMedico, AlertType.INFORMATION);
            } else {
                System.out.println("Médico não encontrado");
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
                        "\nTipo Sanguíneo: " + tipoSanguineoPaciente, Alert.AlertType.INFORMATION);

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
    
  private void exibirDetalhesConsulta(String nomePaciente) {
    String sql = "SELECT c.*, m.nome_medico, e.nome_esp " +
                 "FROM consulta c " +
                 "JOIN medicos m ON c.fk_medico = m.id_medico " +
                 "JOIN especialidade e ON c.fk_especialidade = e.id_esp " +
                 "JOIN paciente p ON c.fk_paciente = p.id_paciente " +
                 "WHERE p.nome_paciente = ?";

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
    


    @FXML
    void removerConsAgendadas(ActionEvent event) {
        // Lógica para remover consultas agendadas
    }

    @FXML
    void removerHistoricoConsLista(ActionEvent event) {
        // Lógica para remover histórico de consultas
    }

    @FXML
    void removerMedicosLista(ActionEvent event) {
        // Lógica para remover médicos
    }

    @FXML
    void removerPacientesLista(ActionEvent event) {
        // Lógica para remover pacientes
    }

    @FXML
    void sair(ActionEvent event) {
        // Lógica para sair
         Main.trocarTela("login");
    }

    @FXML
    void sairLoading() {
        setTelaLoginController(telaLoginController);
        lblmatricula.setText(nm_userr);
        telaDeLoading1.setVisible(false);
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
    void verConsAgendadas(ActionEvent event) {
        // Lógica para visualizar consultas agendadas
        // Lógica para visualizar lista de médicos
        String nomeConsAgendada = lvConsAgendadas.getSelectionModel().getSelectedItem();

        if (nomeConsAgendada != null) {
            exibirDetalhesConsulta(nomeConsAgendada);
        }
    }
    

    @FXML
    void verPacientesLista(ActionEvent event) {
    // Lógica para visualizar lista de pacientes
    String nomePacienteSelecionado = lvPacientes.getSelectionModel().getSelectedItem();
        
    if (nomePacienteSelecionado != null){
        exibirDetalhesPaciente(nomePacienteSelecionado);
    }     
    }
    
    private void exibirAlerta(String titulo, String mensagem, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }


    
}
   