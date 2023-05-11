import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        long x = scanner.nextLong();
        String sign = scanner.next();
        long y = scanner.nextLong();

        if (y == 0) {
            System.out.println("Division by 0!");
        } else {
            switch (sign) {
                case "+" -> System.out.println(x + y);
                case "-" -> System.out.println(x - y);
                case "/" -> System.out.println(x / y);
                case "*" -> System.out.println(x * y);
                default -> System.out.println("Unknown operator");
            }
        }
    }
}