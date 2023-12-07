module projeto.projetobuscavoos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens projeto.projetobuscavoos to javafx.fxml;
    opens controller to javafx.fxml;
    exports projeto.projetobuscavoos;
}
