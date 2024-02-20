//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class SignupApp extends Application {
//
//    private TextField usernameField;
//    private TextField emailField;
//    private TextField addressField;
//    private PasswordField passwordField;
//    private Label signupStatus;
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Signup");
//
//        usernameField = new TextField();
//        usernameField.setPromptText("Username");
//
//        emailField = new TextField();
//        emailField.setPromptText("Email");
//
//        addressField = new TextField();
//        addressField.setPromptText("Address");
//
//        passwordField = new PasswordField();
//        passwordField.setPromptText("Password");
//
//        Button signupButton = new Button("Signup");
//        signupButton.setOnAction(event -> signupUser());
//
//        signupStatus = new Label();
//
//        VBox layout = new VBox(10);
//        layout.getChildren().addAll(usernameField, emailField, addressField, passwordField, signupButton, signupStatus);
//
//        Scene scene = new Scene(layout, 300, 250);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private void signupUser() {
//        String username = usernameField.getText();
//        String email = emailField.getText();
//        String address = addressField.getText();
//        String password = passwordField.getText();
//
//        // Call your signup logic here using JDBC and MySQL
//        // For simplicity, just display signup status in the label
//        if (isValid(username, email, address, password)) {
//            signupStatus.setText("Signup successful");
//        } else {
//            signupStatus.setText("Invalid input. Please fill in all fields.");
//        }
//    }
//
//    private boolean isValid(String username, String email, String address, String password) {
//        return !username.isEmpty() && !email.isEmpty() && !address.isEmpty() && !password.isEmpty();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
