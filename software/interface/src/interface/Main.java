import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage stage) {
        stage.setTitle("Exemplo de Gráfico JavaFX");

        // Eixos
        CategoryAxis eixoX = new CategoryAxis();
        eixoX.setLabel("Mês");

        NumberAxis eixoY = new NumberAxis();
        eixoY.setLabel("Vendas");

        // Tipo do gráfico
        BarChart<String, Number> grafico = new BarChart<>(eixoX, eixoY);
        grafico.setTitle("Vendas Mensais");

        // Dados
        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("2025");
        serie.getData().add(new XYChart.Data<>("Janeiro", 120));
        serie.getData().add(new XYChart.Data<>("Fevereiro", 200));
        serie.getData().add(new XYChart.Data<>("Março", 150));

        grafico.getData().add(serie);

        // Layout
        VBox root = new VBox(grafico);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        // Salvar gráfico como imagem (PNG)
        salvarGraficoComoImagem(grafico, "grafico_vendas.png");
    }

    private void salvarGraficoComoImagem(BarChart<String, Number> grafico, String nomeArquivo) {
        WritableImage imagem = grafico.snapshot(new SnapshotParameters(), null);
        File arquivo = new File(nomeArquivo);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(imagem, null), "png", arquivo);
            System.out.println("✅ Gráfico salvo em: " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
