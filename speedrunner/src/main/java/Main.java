import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui(scanner);
        ui.start();
    }
}
