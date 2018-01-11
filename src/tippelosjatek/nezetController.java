package tippelosjatek;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class nezetController implements Initializable {

    String name;
    String highScoreName;
    boolean status;
    int actualNumber = 0;
    int playerScore = 0;
    int highScore = 0;

//<editor-fold defaultstate="collapsed" desc="fxml import">
    @FXML
    private Button higherButton;
    @FXML
    private Button lowerButton;
    @FXML
    private Button newGame;
    @FXML
    private Button exitButton;
    @FXML
    private Button startButton;
    @FXML
    private Label label;
    @FXML
    private Label nameLabel;
    @FXML
    private Label highScoreLabel;
    @FXML
    private Label playerScoreLabel;
    @FXML
    private Label actualNumberLabel;
    @FXML
    private Label gameOverLabel;
    @FXML
    private Label highScoreNameLabel;
    @FXML
    private TextField nameInput;
    @FXML
    private Pane startPane;
    @FXML
    private Pane gamePane;
    @FXML
    private Pane gameOverPane;
//</editor-fold>

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }

    @FXML
    private void startGame(ActionEvent event) {
        System.out.println("A játék elkezdődött!");
        name = nameInput.getText();
        actualNumber = numGenerator();
        nameLabel.setText(name);
        highScoreLabel.setText("" + highScore);
        playerScoreLabel.setText("" + playerScore);
        actualNumberLabel.setText("" + actualNumber);
        closeStartPane();
        openGamePane();
    }

    @FXML
    private void higher() {
        int number = numGenerator();
        System.out.println(number);
        if (number > actualNumber) {
            actualNumber = number;
            playerScore++;
            playerScoreLabel.setText("" + playerScore);
            actualNumberLabel.setText("" + actualNumber);
        } else {
            gameOver();
            actualNumber = number;
            actualNumberLabel.setText("" + actualNumber);
        }
    }

    @FXML
    private void lower() {
        int number = numGenerator();
        System.out.println(number);
        if (number < actualNumber) {
            actualNumber = number;
            playerScore++;
            playerScoreLabel.setText("" + playerScore);
            actualNumberLabel.setText("" + actualNumber);
        } else {
            gameOver();
            actualNumber = number;
            actualNumberLabel.setText("" + actualNumber);
        }

    }

    @FXML
    private void newGame() {
        playerScore = 0;
        playerScoreLabel.setText("" + playerScore);
        highScoreLabel.setText("" + highScore);
        openGamePane();
        closeGameOverPane();
    }

    @FXML
    private void exitGame() {
        closeGameOverPane();
        closeGamePane();
        openStartPane();
    }

    private int numGenerator() {
        int number = (int) (Math.random() * 10) + 1;
        if (number == actualNumber) {
            return numGenerator();
        }
        return number;
    }

    private void gameOver() {
        System.out.println("Vége a játéknak!!!!");
        openGameOverPane();
        gameOverMessage(playerScore);
        highScoreCheck(playerScore);
        playerScore = 0;

    }

    public void openStartPane() {
        startPane.setDisable(false);
        startPane.setOpacity(1);
        startPane.setVisible(true);
    }

    public void closeStartPane() {
        startPane.setDisable(true);
        startPane.setVisible(false);
    }
    
    public void openGamePane(){
        gamePane.setDisable(false);
        gamePane.setOpacity(1);
        gamePane.setVisible(true);
    }
    
    public void closeGamePane(){
        gamePane.setDisable(true);
        gamePane.setVisible(false);
    }
    
    public void openGameOverPane(){
        gamePane.setOpacity(0.4);
        gamePane.setDisable(true);
        gameOverPane.setDisable(false);
        gameOverPane.setVisible(true);
    }
    
    public void closeGameOverPane(){
        gameOverPane.setDisable(true);
        gameOverPane.setVisible(false);
    }

    private void gameOverMessage(int result) {
        if (result == 0) {
            gameOverLabel.setText("Most nem volt szerencséd......!");
        } else if (result < 3) {
            gameOverLabel.setText("Ne csüggedj, lesz majd jobb is!");
        } else if (result > 2 && result < 6) {
            gameOverLabel.setText("Alakul ez! Ne add fel!");
        } else if (result > 5 && result < 10) {
            gameOverLabel.setText("Az elért pontod: " + result + " ! Szép volt!");
        } else if (result >= 10) {
            gameOverLabel.setText("Az elért pontod: " + result + " ! Ez egy szép eredmény! Gratulálok!");
        }
    }

    private void highScoreCheck(int num) {
        if (num > highScore) {
            highScore = num;
            highScoreName = name;
            highScoreNameLabel.setText(highScoreName);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
