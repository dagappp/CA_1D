import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj wielkość przestrzeni: ");
        int caSpace = scanner.nextInt();

        if (caSpace <= 0) {
            System.out.println("Błąd");
            return;
        }

        System.out.print("Podaj liczbę iteracji: ");
        int iterations = scanner.nextInt();
        scanner.nextLine();

        CA cellular = new CA();
        cellular.caSpace = caSpace;
        cellular.setInitial();

        System.out.println("Wybierz regułę 41, 18, 75 lub 190: ");
        String selectedRule = scanner.nextLine();

        switch (selectedRule) {
            case "41":
                cellular.setRule41();
                break;
            case "18":
                cellular.setRule18();
                break;
            case "75":
                cellular.setRule75();
                break;
            case "190":
                cellular.setRule190();
                break;
            case "90":
                cellular.setRule90();
                break;
            case "30":
                cellular.setRule30();
                break;
            case "60":
                cellular.setRule60();
                break;
            case "250":
                cellular.setRule250();
                break;
            default:
                System.out.println("Błąd.");
                return;
        }

        int caArrayEx[] = new int[cellular.caSpace];

        for (int i = 0; i < cellular.caSpace; i++) {
            caArrayEx[i] = 0;
        }

        caArrayEx[cellular.caSpace / 2] = 1;

        FileWriter fw = new FileWriter("reguła" + selectedRule + ".txt");
        BufferedWriter bw = new BufferedWriter(fw);

        for (int state = 0; state < iterations; state++) {
            cellular.periodic(caArrayEx);
            StringBuilder stateString = new StringBuilder();

            for (int cell : caArrayEx) {
                if (cell == 1) {
                    stateString.append("1");
                } else {
                    stateString.append("0");
                }
            }

            System.out.println(stateString);

            bw.write(stateString.toString());
            bw.newLine();

            caArrayEx = cellular.simulate(caArrayEx);
        }

        bw.close();
        scanner.close();
    }
}
