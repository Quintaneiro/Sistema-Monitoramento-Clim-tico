package br.com.ufv.sistemamonitoramentoclimatico.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TelaPrincipal extends Application {

    @Override
    public void start(Stage palcoPrincipal) {
        palcoPrincipal.setTitle("Sistema de Monitoramento Climático");

        Label lblTitulo = new Label("Agricultura 4.0 - Monitoramento");
        lblTitulo.getStyleClass().add("titulo");

        Button btnCadastro = new Button("Cadastrar Medição");
        Button btnHistorico = new Button("Ver Histórico");
        btnHistorico.getStyleClass().add("button-secundario"); // Aplica a cor azul do CSS

        // Ações dos botões para abrir as novas janelas
        btnCadastro.setOnAction(e -> new TelaCadastro().abrir());
        btnHistorico.setOnAction(e -> new TelaHistorico().abrir());

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(lblTitulo, btnCadastro, btnHistorico);

        Scene cena = new Scene(layout, 400, 300);
        
        // Linkando o arquivo CSS (certifique-se de que o caminho está correto)
        cena.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());

        palcoPrincipal.setScene(cena);
        palcoPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args); // Inicia o JavaFX
    }
}
