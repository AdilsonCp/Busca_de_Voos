module projeto.projetointerdisciplinar {
    requires javafx.controls;
    requires javafx.fxml;

    opens projeto.projetointerdisciplinar to javafx.fxml;
    exports projeto.projetointerdisciplinar;
}
