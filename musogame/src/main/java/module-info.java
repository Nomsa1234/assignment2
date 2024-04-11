module com.example.musogame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.musogame to javafx.fxml;
    exports com.example.musogame;
}