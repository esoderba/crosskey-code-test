package com.example.application.mortagecalc;

public final class Utils {
    private Utils() {
    }

    public static String formatDouble(double total) {
        if (total % 1.0 == 0) {
            return String.format("%d", (int) total);
        }
        return String.format("%.2f", total);
    }

    public static String formatName(String name) {
        name = name.replaceAll("\"", "");
        name = name.replaceAll(",", " ");
        return name;
    }

    /**
     * @param total
     * @param interest in percent
     * @param years
     * @return Monthly payment required to pay of loan in provided amount of years
     */
    public static double calculateMonthlyPayment(double total, double interest, int years) {
        // Formula given: U[b(1 + b)^p]/[(1 + b)^p - 1]
        // U = Total, b = interest, p = months
        // Substituting (1 + b)^p with x gives us (U * b * x)/(x - 1)

        double b = interest / 10000;
        int p = years * 12;
        double x = pow(b + 1, p);
        return ((total * b * x) / (x - 1)) / 100;
    }

    private static double pow(double base, int exp) {
        if (exp == 0) {
            return 1;
        }

        if (exp == 1) {
            return base;
        }

        if (exp % 2 == 0) {
            double half = pow(base, exp / 2);
            return half * half;
        } else {
            double half = pow(base, (exp - 1) / 2);
            return base * half * half;
        }
    }
}
