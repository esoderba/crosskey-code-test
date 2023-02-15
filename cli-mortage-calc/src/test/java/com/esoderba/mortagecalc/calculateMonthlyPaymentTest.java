package com.esoderba.mortagecalc;

import org.junit.jupiter.api.Test;

import static com.esoderba.mortagecalc.Utils.calculateMonthlyPayment;
import static com.esoderba.mortagecalc.Utils.formatDouble;

public class calculateMonthlyPaymentTest {

    @Test
    void calculatePayment() {
        double actualPayment0 = calculateMonthlyPayment(100000, 500, 2);
        double actualPayment1 = calculateMonthlyPayment(130055, 867, 2);
        double actualPayment2 = calculateMonthlyPayment(435600, 127, 6);
        double expectedPayment0 = 72.47089959;
        double expectedPayment1 = 130.4987363920348;
        double expectedPayment2 = 92.6763435073476;
        assert formatDouble(actualPayment0).equals(formatDouble(expectedPayment0));
        assert formatDouble(actualPayment1).equals(formatDouble(expectedPayment1));
        assert formatDouble(actualPayment2).equals(formatDouble(expectedPayment2));
    }
}
