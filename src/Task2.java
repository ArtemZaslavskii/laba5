import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Task2 extends Application {

    @Override
    public void start(Stage stage) {
        // Виджеты
        Button  btn    = new Button("Кнопка");
        Label   lbl    = new Label("Метка с текстом");
        TextField tf   = new TextField("Поле ввода");
        ProgressBar pb = new ProgressBar(0.6);
        Slider  sl     = new Slider(0, 100, 50);

        // Чекбоксы
        CheckBox cb1 = new CheckBox("Кнопка");      cb1.setSelected(true);
        CheckBox cb2 = new CheckBox("Метка");        cb2.setSelected(true);
        CheckBox cb3 = new CheckBox("Поле ввода");   cb3.setSelected(true);
        CheckBox cb4 = new CheckBox("Прогресс бар"); cb4.setSelected(true);
        CheckBox cb5 = new CheckBox("Слайдер");      cb5.setSelected(true);

        // Обработчики — управляем видимостью и занимаемым местом
        cb1.setOnAction(e -> { btn.setVisible(cb1.isSelected()); btn.setManaged(cb1.isSelected()); });
        cb2.setOnAction(e -> { lbl.setVisible(cb2.isSelected()); lbl.setManaged(cb2.isSelected()); });
        cb3.setOnAction(e -> { tf.setVisible(cb3.isSelected());  tf.setManaged(cb3.isSelected()); });
        cb4.setOnAction(e -> { pb.setVisible(cb4.isSelected());  pb.setManaged(cb4.isSelected()); });
        cb5.setOnAction(e -> { sl.setVisible(cb5.isSelected());  sl.setManaged(cb5.isSelected()); });

        // Колонка виджетов
        VBox widgets = new VBox(12, btn, lbl, tf, pb, sl);
        widgets.setAlignment(Pos.CENTER_LEFT);
        widgets.setPadding(new Insets(10));

        // Колонка чекбоксов
        VBox checks = new VBox(12, cb1, cb2, cb3, cb4, cb5);
        checks.setAlignment(Pos.CENTER_LEFT);
        checks.setPadding(new Insets(10));

        HBox root = new HBox(30, checks, widgets);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setPadding(new Insets(20));

        stage.setTitle("Задание 2 — Показать / Скрыть виджеты");
        stage.setScene(new Scene(root, 360, 260));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
