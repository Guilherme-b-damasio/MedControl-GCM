package medcontrol;
import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import db.DB;
import java.io.IOException;
import java.net.URL;
import javafx.scene.image.Image;
import model.Admin;


/**
 *
 * @author luca
 */
public class Main extends Application {
    //INSTANCIAS DAS OUTRAS CLASSES
    private final static Main instance = new Main();
    public static Main getInstance() {
        return instance;
    }
    
    private final Admin admin = new Admin();    
    public Admin admin() {
        return admin;
    }
    //---------------------------------------
    
    private static Stage novoStage;
    
    private static Scene loginScene;
        
    private static Scene adminScene;
    
    private static Scene atendenteScene;
    
    private static Scene medicoScene;
    
    public void start(Stage stage) throws Exception {
        novoStage = stage;       
        
        
        try {
        // Modifique o caminho para o FXML, se necessário
           URL fxmlUrl = getClass().getResource("/view/FXMLTelaLogin.fxml");
            if (fxmlUrl == null) {
                System.err.println("FXML file not found");
                // Trate o erro apropriadamente
                return;
            }
            
        Image icon = new Image(getClass().getResourceAsStream("/images/icon.png"));
         // Ajuste a largura e altura desejadas para o ícone
        double novaLargura = 256; // Substitua pelo tamanho desejado
        double novaAltura = 256;  // Substitua pelo tamanho desejado
        icon = new Image(getClass().getResourceAsStream("/images/icon.png"), novaLargura, novaAltura, true, true);
            
        Parent admin = FXMLLoader.load(getClass().getResource("/view/FXMLTelaAdmin.fxml"));
        adminScene = new Scene(admin);
        
        Parent login = FXMLLoader.load(fxmlUrl);
        loginScene = new Scene(login);
        
        Parent atendente =FXMLLoader.load(getClass().getResource("/view/FXMLTelaAtendente.fxml"));
        atendenteScene = new Scene(atendente);
        
        Parent medico = FXMLLoader.load(getClass().getResource("/view/FXMLTelaMedico.fxml"));
        medicoScene = new Scene(medico);
        
        stage.setResizable(false); // Bloqueia o redimensionamento
        stage.setScene(loginScene); // COMECA COM A TELA DE LOGIN
        stage.setTitle("MedControl");
        stage.getIcons().add(icon);
        stage.show();
         } catch (IOException e) {
            // Exceção ao carregar o FXML, imprima o rastreamento de pilha para depuração
            e.printStackTrace();
         }
    }

    public static void trocarTela(String nomeTela){
        if (novoStage != null) {
        switch(nomeTela){
            case("login"):
                novoStage.setScene(loginScene);
                break;
            case("admin"):
                novoStage.setScene(adminScene);
                break;
            case("atendente"):
                novoStage.setScene(atendenteScene);
                break;
            case("medico"):
                novoStage.setScene(medicoScene);
        }
    } else {
        System.err.println("novoStage não foi inicializado.");
        // Trate o erro apropriadamente, como exibir uma mensagem de erro.
    }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    


    
}
