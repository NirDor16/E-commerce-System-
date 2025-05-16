package InbarSarIsrael323101485NirDor212779243;

import java.util.Scanner;

public class UserInputUtils {
    static Scanner s = new Scanner(System.in);

    public static String getString() {
        String str;
        do {
            str = s.nextLine();
            if (str.isEmpty()) {
                System.out.println("Invalid input\nPlease enter again:");
            }
        } while (str.isEmpty());
        return str;
    }

    public static Double getPositiveDouble() {
        double num;
        while (true) {
            String strNum = getString();
            try {
                num = Double.parseDouble(strNum);
                if (num > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid double value.");
            }
            System.out.println("Invalid number, please enter a positive number");
        }
        return num;
    }

    public static int getPositiveInt() {
        int num;
        while (true) {
            String strNum = getString();
            try {
                num = Integer.parseInt(strNum);
                if (num > 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid int value.");
            }
            System.out.println("Invalid number, please enter a numeric number");
        }
        return num;
    }
}


