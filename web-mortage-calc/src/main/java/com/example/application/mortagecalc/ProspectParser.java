package com.example.application.mortagecalc;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProspectParser {
    private final Pattern namePattern = Pattern.compile("\"[^\"]*\"|[^0-9.,\n]+");
    private final String validHeader = "Customer,Total loan,Interest,Years";

    private static Scanner initScanner(Path path) throws IOException {
        Scanner scanner = new Scanner(path, "UTF-8");
        scanner.useDelimiter("[,\\n]");
        // Scanner needs to use an english locale to parse floats using . as the decimal sign
        scanner.useLocale(Locale.ENGLISH);
        return scanner;
    }

    /**
     * @param path Path object for input file
     * @return ArrayList of Prospects
     * @throws IOException
     * @throws InputMismatchException
     */
    public ArrayList<Prospect> parse(Path path) throws IOException, InputMismatchException {
        Scanner scanner = initScanner(path);
        ArrayList<Prospect> prospects = new ArrayList<>();
        try {
            // Read header line
            String header = scanner.nextLine();
            if (!header.equals(validHeader)) {
                throw new InputMismatchException("Invalid header");
            }

            while (scanner.hasNextLine()) {
                // Search for next occurrence of a valid name in the file, exit if none is found
                String name = scanner.findWithinHorizon(namePattern, 0);
                if (name == null) {
                    break;
                }
                int total = (int) (scanner.nextFloat() * 100);
                int interest = (int) (scanner.nextFloat() * 100);
                // We are assuming years will always be provided as integers
                int years = scanner.nextInt();
                prospects.add(new Prospect(name, total, interest, years));
            }
        } finally {
            scanner.close();
        }
        return prospects;
    }
}
