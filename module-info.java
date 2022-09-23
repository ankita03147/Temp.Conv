module com.example.hellofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.company.hellofx to javafx.fxml;
    exports com.company.hellofx;
}