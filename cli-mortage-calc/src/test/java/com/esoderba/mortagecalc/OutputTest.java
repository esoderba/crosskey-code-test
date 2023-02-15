package com.esoderba.mortagecalc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.esoderba.mortagecalc.Utils.calculateMonthlyPayment;
import static com.esoderba.mortagecalc.Utils.formatDouble;

public class OutputTest {

    @Test
    void printProspect() {
        Prospect prospect0 = new Prospect("Juha", 100000, 500, 2);
        Prospect prospect1 = new Prospect("Karvinen", 435600, 127, 6);
        Prospect prospect2 = new Prospect("Claes Månsson", 130055, 867, 2);
        Prospect prospect3 = new Prospect("Clarencé Andersson", 200000, 600, 4);
        ArrayList<Prospect> prospects = new ArrayList<Prospect>(
                Arrays.asList(
                        prospect0,
                        prospect1,
                        prospect2,
                        prospect3
                ));
        String separator = "****************************************************************************************************\n";
        String expectedOutput0 = separator + "Prospect 1: Juha wants to borrow 1000 € for a period of 2 years and pay 72,47 € each month\n" + separator;
        String expectedOutput1 = separator + "Prospect 2: Karvinen wants to borrow 4356 € for a period of 6 years and pay 92,68 € each month\n" + separator;
        String expectedOutput2 = separator + "Prospect 3: Claes Månsson wants to borrow 1300,55 € for a period of 2 years and pay 130,50 € each month\n" + separator;
        String expectedOutput3 = separator + "Prospect 4: Clarencé Andersson wants to borrow 2000 € for a period of 4 years and pay 127,80 € each month\n" + separator;
        assert expectedOutput0.equals(prospect0.getOutputStr(1));
        assert expectedOutput1.equals(prospect1.getOutputStr(2));
        assert expectedOutput2.equals(prospect2.getOutputStr(3));
        assert expectedOutput3.equals(prospect3.getOutputStr(4));
    }
}
