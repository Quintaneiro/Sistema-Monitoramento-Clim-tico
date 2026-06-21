package br.com.ufv.sistemamonitoramentoclimatico.view;

import br.com.ufv.sistemamonitoramentoclimatico.controller.ClimaController;
import br.com.ufv.sistemamonitoramentoclimatico.model.Clima;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class TelaHistorico {

    private ClimaController controller = new ClimaController();

    public void abrir() {
        Stage palco = new Stage();
        palco.setTitle("Histórico de Medições");

        TableView<Clima> tabela = new TableView<>();
        
        // Criando as colunas e mapeando para os atributos da classe Clima (getters)
        TableColumn<Clima, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Clima, LocalDate> colData = new TableColumn<>("Data");
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));

        TableColumn<Clima, Double> colTemp = new TableColumn<>("Temp. (°C)");
        colTemp.setCellValueFactory(new PropertyValueFactory<>("temperatura"));

        TableColumn<Clima, Double> colUmid = new TableColumn<>("Umidade (%)");
        colUmid.setCellValueFactory(new PropertyValueFactory<>("umidade"));

        TableColumn<Clima, Double> colPrec = new TableColumn<>("Precip. (mm)");
        colPrec.setCellValueFactory(new PropertyValueFactory<>("precipitacao"));

        TableColumn<Clima, Double> colVento = new TableColumn<>("Vento (km/h)");
        colVento.setCellValueFactory(new PropertyValueFactory<>("vento"));

        tabela.getColumns().addAll(colId, colData, colTemp, colUmid, colPrec, colVento);

        // Buscando dados no banco via Controller
        List<Clima> listaHistorico = controller.buscarHistorico();
        ObservableList<Clima> dadosParaTabela = FXCollections.observableArrayList(listaHistorico);
        tabela.setItems(dadosParaTabela);

        VBox layout = new VBox();
        layout.setPadding(new Insets(15));
        layout.getChildren().add(tabela);

        Scene cena = new Scene(layout, 650, 400);
        cena.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
        palco.setScene(cena);
        palco.show();
    }
}
