import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Task5 extends Application {

    private static final String[] COLORS = {"Красный", "Синий", "Зелёный", "Белый", "Жёлтый"};

    @Override
    public void start(Stage stage) {
        // Три группы RadioButton для каждой полосы
        ToggleGroup[] groups = new ToggleGroup[3];
        VBox[] columns = new VBox[3];
        String[] stripNames = {"1-я полоса", "2-я полоса", "3-я полоса"};

        for (int s = 0; s < 3; s++) {
            groups[s] = new ToggleGroup();
            VBox col = new VBox(6);
            col.setPadding(new Insets(8));
            col.getChildren().add(new Label(stripNames[s]));
            for (String color : COLORS) {
                RadioButton rb = new RadioButton(color);
                rb.setToggleGroup(groups[s]);
                col.getChildren().add(rb);
            }
            // Выбираем первый по умолчанию
            ((RadioButton) col.getChildren().get(1)).setSelected(true);
            columns[s] = col;
        }

        Label resultLabel = new Label("");
        resultLabel.setStyle("-fx-font-size:16; -fx-font-weight:bold;");

        Button drawBtn = new Button("Нарисовать");
        drawBtn.setOnAction(e -> {
            String[] selected = new String[3];
            for (int s = 0; s < 3; s++) {
                Toggle t = groups[s].getSelectedToggle();
                selected[s] = (t != null) ? ((RadioButton) t).getText() : "—";
            }
            resultLabel.setText(selected[0] + ", " + selected[1] + ", " + selected[2]);
        });

        HBox radioRow = new HBox(20, columns[0], new Separator(), columns[1], new Separator(), columns[2]);
        radioRow.setAlignment(Pos.TOP_CENTER);

        VBox root = new VBox(15, radioRow, drawBtn, resultLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setTitle("Задание 5 — Текстовый флаг");
        Scene scene = new Scene(root, 420, 320);
        stage.setScene(scene);
        stage.setResizable(false);   // нельзя менять размер
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
