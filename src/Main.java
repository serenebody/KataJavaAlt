import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение");
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();
        char action = action(exp);

        if (action == ' ') throw new Exception("Некорректный знак действия");
        String[] data = exp.split(" \\" + action + " ");
        if (data[1].length() > 10) throw new Exception("Введено больше 10 символов");
        if (action == '*' || action == '/') {
            if (data[1].contains("\"")) throw new Exception("Строку можно делить или умножать только на число");
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }

        String result = calcString(action, data);
        outString(result);
    }

    static String calcString(char action, String[] data) {
        return switch (action) {
            case '+' -> data[0] + data[1];
            case '*' -> {
                int multiplier = Integer.parseInt(data[1]);
                String r = "";
                for (int i = 0; i < multiplier; i++) {
                    r += data[0];
                }
                yield r;
            }
            case '-' -> {
                int index = data[0].indexOf(data[1]);
                if (index == -1) yield data[0];
                String r = data[0].substring(0, index);
                r += data[0].substring(index + data[1].length());
                yield r;
            }
            case '/' -> {
                int newLen = data[0].length() / Integer.parseInt(data[1]);
                yield data[0].substring(0, newLen);
            }
            default -> throw new IllegalStateException("Неккоректное действие");
        };
    }

    static char action(String text) {
        String[] a = new String[]{"+", "-", "*", "/"};
        char action = ' ';
        for (int i = 0; i < a.length; i++) {
            if (text.contains(a[i])) {
                action = a[i].charAt(0);
                break;
            }
        }
        return action;
    }

    static void outString(String text) {
        if (text.length() > 40) text = text.substring(0, 40) + "...";
        System.out.println(text);
    }
}