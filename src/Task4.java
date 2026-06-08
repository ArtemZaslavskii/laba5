import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Task4 extends Application {

    private TextField display = new TextField("0");
    private double    memory  = 0;
    private String    pendingOp = "";
    private boolean   newInput  = true;

    @Override
    public void start(Stage stage) {
        display.setEditable(false);
        display.setStyle("-fx-font-size:20; -fx-alignment: CENTER_RIGHT;");
        display.setMaxWidth(Double.MAX_VALUE);

        // Кнопки
        String[][] labels = {
            {"7","8","9","/"},
            {"4","5","6","*"},
            {"1","2","3","-"},
            {"±","0",".","+" },
            {"C","","","="}
        };

        GridPane grid = new GridPane();
        grid.setHgap(6);
        grid.setVgap(6);
        grid.setPadding(new Insets(10));

        for (int r = 0; r < labels.length; r++) {
            for (int c = 0; c < labels[r].length; c++) {
                String text = labels[r][c];
                if (text.isEmpty()) continue;
                Button btn = new Button(text);
                btn.setMinSize(60, 50);
                btn.setMaxWidth(Double.MAX_VALUE);
                btn.setStyle("-fx-font-size:16;");
                final String t = text;
                btn.setOnAction(e -> handleButton(t));
                if (text.equals("=")) {
                    GridPane.setColumnSpan(btn, 3);
                    grid.add(btn, 1, r);
                } else {
                    grid.add(btn, c, r);
                }
            }
        }

        VBox root = new VBox(8, display, grid);
        root.setPadding(new Insets(12));

        stage.setTitle("Задание 4 — Калькулятор");
        stage.setScene(new Scene(root, 280, 360));
        stage.setResizable(false);
        stage.show();
    }

    private void handleButton(String t) {
        switch (t) {
            case "C"  -> { display.setText("0"); memory = 0; pendingOp = ""; newInput = true; }
            case "±"  -> {
                double v = Double.parseDouble(display.getText());
                display.setText(format(-v));
            }
            case "+"  , "-", "*", "/" -> {
                memory = Double.parseDouble(display.getText());
                pendingOp = t;
                newInput = true;
            }
            case "="  -> calculate();
            case "."  -> {
                if (newInput) { display.setText("0."); newInput = false; return; }
                if (!display.getText().contains("."))
                    display.setText(display.getText() + ".");
            }
            default -> {   // цифры
                if (newInput) { display.setText(t); newInput = false; }
                else display.setText(display.getText().equals("0") ? t : display.getText() + t);
            }
        }
    }

    private void calculate() {
        if (pendingOp.isEmpty()) return;
        double current = Double.parseDouble(display.getText());
        double result;
        try {
            result = switch (pendingOp) {
                case "+" -> memory + current;
                case "-" -> memory - current;
                case "*" -> memory * current;
                case "/" -> {
                    if (current == 0) throw new ArithmeticException("Деление на ноль!");
                    yield memory / current;
                }
                default  -> current;
            };
            display.setText(format(result));
        } catch (ArithmeticException ex) {
            display.setText("Ошибка: " + ex.getMessage());
        }
        pendingOp = "";
        newInput  = true;
    }

    private String format(double v) {
        return (v == Math.floor(v) && !Double.isInfinite(v))
               ? String.valueOf((long) v)
               : String.valueOf(v);
    }

    public static void main(String[] args) { launch(args); }
}
