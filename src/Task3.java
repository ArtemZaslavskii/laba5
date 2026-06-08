import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Task3 extends Application {

    // Данные меню
    private static final String[] NAMES  = {"Борщ", "Паста", "Стейк", "Салат", "Сок"};
    private static final double[] PRICES = {5.50,    8.00,    15.00,   4.50,   2.00};

    private CheckBox[] checks;
    private Spinner<Integer>[] spinners;
    private TextArea receipt;

    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage stage) {
        checks  = new CheckBox[NAMES.length];
        spinners = new Spinner[NAMES.length];

        GridPane menu = new GridPane();
        menu.setHgap(10);
        menu.setVgap(8);
        menu.setPadding(new Insets(10));

        // Заголовки
        menu.add(new Label("Блюдо"),       0, 0);
        menu.add(new Label("Цена (€)"),    1, 0);
        menu.add(new Label("Кол-во"),      2, 0);

        for (int i = 0; i < NAMES.length; i++) {
            CheckBox cb = new CheckBox(NAMES[i]);
            Spinner<Integer> sp = new Spinner<>(1, 20, 1);
            sp.setMaxWidth(70);
            sp.setDisable(true);

            // Когда отмечаем блюдо — активируем спиннер
            final int idx = i;
            cb.setOnAction(e -> spinners[idx].setDisable(!cb.isSelected()));

            checks[i]  = cb;
            spinners[i] = sp;

            menu.add(cb,                           0, i + 1);
            menu.add(new Label(String.format("%.2f", PRICES[i])), 1, i + 1);
            menu.add(sp,                           2, i + 1);
        }

        Button orderBtn = new Button("Сформировать чек");
        orderBtn.setMaxWidth(Double.MAX_VALUE);

        receipt = new TextArea();
        receipt.setEditable(false);
        receipt.setPrefHeight(180);

        orderBtn.setOnAction(e -> buildReceipt());

        VBox root = new VBox(10, menu, orderBtn, receipt);
        root.setPadding(new Insets(15));

        stage.setTitle("Задание 3 — Ресторан");
        stage.setScene(new Scene(root, 380, 460));
        stage.show();
    }

    private void buildReceipt() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-16s %5s %8s%n", "Блюдо", "Кол.", "Сумма"));
        sb.append("-".repeat(32)).append("\n");
        double total = 0;
        boolean anySelected = false;

        for (int i = 0; i < NAMES.length; i++) {
            if (checks[i].isSelected()) {
                anySelected = true;
                int qty = spinners[i].getValue();
                double sum = PRICES[i] * qty;
                total += sum;
                sb.append(String.format("%-16s %5d %8.2f€%n", NAMES[i], qty, sum));
            }
        }

        if (!anySelected) {
            receipt.setText("Вы ничего не выбрали!");
            return;
        }

        sb.append("-".repeat(32)).append("\n");
        sb.append(String.format("%-21s %8.2f€%n", "ИТОГО:", total));
        receipt.setText(sb.toString());
    }

    public static void main(String[] args) { launch(args); }
}
