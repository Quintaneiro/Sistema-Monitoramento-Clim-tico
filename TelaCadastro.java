package br.com.ufv.sistemamonitoramentoclimatico.view;

import br.com.ufv.sistemamonitoramentoclimatico.controller.ClimaController;
import br.com.ufv.sistemamonitoramentoclimatico.model.Clima;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class TelaCadastro {

    private ClimaController controller = new ClimaController();

    public void abrir() {
        Stage palco = new Stage();
        palco.initModality(Modality.APPLICATION_MODAL); // Bloqueia a tela anterior até fechar essa
        palco.setTitle("Nova Medição Climática");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Componentes do formulário
        TextField txtTemperatura = new TextField();
        TextField txtUmidade = new TextField();
        TextField txtPrecipitacao = new TextField();
        TextField txtVento = new TextField();

        grid.add(criarLabel("Temperatura (°C):"), 0, 0);
        grid.add(txtTemperatura, 1, 0);
        grid.add(criarLabel("Umidade (%):"), 0, 1);
        grid.add(txtUmidade, 1, 1);
        grid.add(criarLabel("Precipitação (mm):"), 0, 2);
        grid.add(txtPrecipitacao, 1, 2);
        grid.add(criarLabel("Vento (km/h):"), 0, 3);
        grid.add(txtVento, 1, 3);

        Button btnSalvar = new Button("Salvar Medição");
        grid.add(btnSalvar, 1, 4);

        // Ação de salvar e comunicar com o Controller
        btnSalvar.setOnAction(e -> {
            try {
                double temp = Double.parseDouble(txtTemperatura.getText());
                double umid = Double.parseDouble(txtUmidade.getText());
                double prec = Double.parseDouble(txtPrecipitacao.getText());
                double vento = Double.parseDouble(txtVento.getText());

                Clima novaMedicao = new Clima(LocalDate.now(), temp, umid, prec, vento);

                if (controller.salvarMedicao(novaMedicao)) {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Medição cadastrada com sucesso!");
                    palco.close();
                }
            } catch (NumberFormatException ex) {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro de Validação", "Por favor, digite apenas números válidos.");
            }
        });

        Scene cena = new Scene(grid, 350, 300);
        cena.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
        palco.setScene(cena);
        palco.showAndWait();
    }

    private Label criarLabel(String texto) {
        Label lbl = new Label(texto);
        lbl.getStyleClass().add("label-form");
        return lbl;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
