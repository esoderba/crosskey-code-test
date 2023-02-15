package com.example.application.mortagecalc;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class MortageCalculator {

    // TODO: Unit tests
    // TODO: Figure out web interface

    public static void main(String[] args) {

        Path path = Paths.get("META-INF/resources/prospects.txt");
        if (args.length == 0) {
            System.out.println("No file path provided, defaulting to ./prospects.txt");
        } else if (args.length == 1) {
            path = Paths.get(args[0]);
        } else {
            System.out.println("Error: multiple arguments");
            System.out.println("Please provide only a single filepath");
            return;
        }

        int prospectNr = 0;
        ProspectParser parser = new ProspectParser();

        try {
            ArrayList<Prospect> prospects = new ArrayList<Prospect>(parser.parse(path));
            for (Prospect prospect : prospects) {
                prospectNr++;
                String outStr = prospect.getOutputStr(prospectNr);
                System.out.println(prospect.getOutputStr(prospectNr));
            }
        } catch (IOException e) {
            System.out.printf("Error: Could not open file %s %n", path.toString());
            System.exit(1);
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid formatting");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("An unexpected error occurred");
            System.exit(1);
        }
    }
}
