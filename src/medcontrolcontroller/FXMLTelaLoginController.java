package medcontrolcontroller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DB;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import medcontrol.Main;
import model.NomeEsp;

/**
 *
 * @author luca
 */
public class FXMLTelaLoginController implements Initializable {
    Connection conn = null;
    String nm_user;
    
    
    public NomeEsp nome_lbl = new NomeEsp();
    
    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private ImageView imgvLogo;

    @FXML
    private ImageView imgVpattern;
    
   private StringProperty nmUser = new SimpleStringProperty(); 
   
    public void erroLogin(){
    }
    
    public void limparCampos(){
        txtUsuario.setText("");
        txtSenha.setText("");
    }
    
     
    public String getNmUser() {
        return nmUser.get();
    }

    public StringProperty nmUserProperty() {
        return nmUser;
    }

    // Método para atualizar nmUser
    public void setNmUser(String value) {
        nmUser.set(value);
    }
    
    public void verificarLogin() throws SQLException {
        conn = DB.getConnection();

        if (conn == null) {
            System.out.println("BD Não conectado");
            return; // Evitar a execução do restante do método
        }

        String user = txtUsuario.getText();
        String senha = txtSenha.getText();
        

        String nivelAcesso;

        String sql = "select * from ControleAcesso where nome_usuario = ? and senha_usuario = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    
                    nivelAcesso = rs.getString("nivel_acesso");
                    setNmUser(user);
                    nm_user = user;

                    if ("admin".equals(nivelAcesso)) {
                        
                        Main.trocarTela("admin");
                    } else if ("funcionario".equals(nivelAcesso)) {
                        Main.trocarTela("atendente");
                    } else if("medico".equals(nivelAcesso)){
                        Main.trocarTela("medico");
                    }
                } else {
                    System.out.println("Autenticação falhou");
                    setNmUser(user);
                    nm_user = user;
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtUsuario.setText("admin");
        txtSenha.setText("123");
        
        txtUsuario.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                try {
                    verificarLogin();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        
        txtSenha.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                try {
                    verificarLogin();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


    }
    
   
    
}
