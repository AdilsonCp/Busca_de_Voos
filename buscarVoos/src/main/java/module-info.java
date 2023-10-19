module projeto.buscarvoos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens projeto.buscarvoos to javafx.fxml;
    exports projeto.buscarvoos;
}
