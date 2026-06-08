import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Task1 extends Application {

    private boolean leftToRight = true;

    @Override
    public void start(Stage stage) {
        TextField leftField  = new TextField();
        TextField rightField = new TextField();
        leftField.setPromptText("Введите текст");
        rightField.setEditable(false);

        Button btn = new Button("→");
        btn.setMinWidth(50);

        btn.setOnAction(e -> {
            if (leftToRight) {
                rightField.setText(leftField.getText());
                leftField.clear();
                btn.setText("←");
            } else {
                leftField.setText(rightField.getText());
                rightField.clear();
                btn.setText("→");
            }
            leftToRight = !leftToRight;
        });

        HBox root = new HBox(10, leftField, btn, rightField);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        stage.setTitle("Задание 1 — Перекидыватель слов");
        stage.setScene(new Scene(root, 400, 80));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
