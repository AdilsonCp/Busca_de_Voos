module projeto.projetointerdisciplinar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens projeto.projetointerdisciplinar to javafx.fxml;
    exports projeto.projetointerdisciplinar;
}
