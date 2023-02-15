package com.esoderba.mortagecalc;

import static com.esoderba.mortagecalc.Utils.*;

public class Prospect {
    String name;
    double total;
    int interest;
    int years;

    double monthlyPayment;

    /**
     * @param name
     * @param total    in cents (€ * 100)
     * @param interest in basis points (percent * 100)
     * @param years
     */
    Prospect(String name, double total, int interest, int years) {
        this.name = formatName(name);
        this.total = total;
        this.interest = interest;
        this.years = years;
        this.monthlyPayment = calculateMonthlyPayment(total, interest, years);
    }

    /**
     * @param nr, Prospect number
     * @return Formatted output string
     */
    public String getOutputStr(int nr) {
        String separator = "****************************************************************************************************\n";
        String info = String.format(
                "Prospect %d: %s wants to borrow %s € for a period of %d years and pay %s € each month\n",
                nr, name, formatDouble(total / 100), years, formatDouble(monthlyPayment)
        );
        return separator + info + separator;
    }

    public String toString(int nr) {
        return String.format(
                "Prospect %d: %s wants to borrow %s € for a period of %d years and pay %s € each month\n",
                nr, name, formatDouble(total / 100), years, formatDouble(monthlyPayment)
        );
    }
}
