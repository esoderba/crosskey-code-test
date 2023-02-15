package com.esoderba.mortagecalc;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("[Unit Test] Prospect parser")
public class ProspectParserTest {

    private final ProspectParser parser = new ProspectParser();

    private static boolean prospectEquals(Prospect p1, Prospect p2) {
        return (p1.name.equals(p2.name) &&
                p1.total == p2.total &&
                p1.interest == p2.interest &&
                p1.years == p2.years);
    }

    @Test
    void prospectsParsed() {
        Path validPath = Paths.get("src", "test", "resources", "valid_prospects.txt");
        Prospect expectedProspect0 = new Prospect("Juha", 100000, 500, 2);
        Prospect expectedProspect1 = new Prospect("Karvinen", 435600, 127, 6);
        Prospect expectedProspect2 = new Prospect("Claes Månsson", 130055, 867, 2);
        Prospect expectedProspect3 = new Prospect("Clarencé Andersson", 200000, 600, 4);
        ArrayList<Prospect> expectedProspects = new ArrayList<Prospect>(
                Arrays.asList(
                        expectedProspect0,
                        expectedProspect1,
                        expectedProspect2,
                        expectedProspect3
                ));
        try {
            ArrayList<Prospect> actualProspects = new ArrayList<Prospect>(parser.parse(validPath));
            assert (prospectEquals(actualProspects.get(0), expectedProspect0));
            assert prospectEquals(actualProspects.get(1), expectedProspect1);
            assert prospectEquals(actualProspects.get(2), expectedProspect2);
            assert prospectEquals(actualProspects.get(3), expectedProspect3);
        } catch (IOException e) {
            fail("Failed to read file");
        }
    }

    void shouldThrowException(Path path) {
        assertThrows(Exception.class, () -> parser.parse(path));
    }

    // [C, l, a, r, e, n, c, é,  , A, n, d, e, r, s, s, o, n]
    // [C, l, a, r, e, n, c, é,  , A, n, d, e, r, s, s, o, n]

    // Clarencé Andersson
    @Test
    void emptyProspects() {
        shouldThrowException(Paths.get("src", "test", "resources", "invalid_empty_prospects.txt"));
    }

    @Test
    void invalidHeader() {
        shouldThrowException(Paths.get("src", "test", "resources", "invalid_header_prospects.txt"));
    }

    @Test
    void invalidInterest() {
        shouldThrowException(Paths.get("src", "test", "resources", "invalid_interest_prospects.txt"));
    }

    @Test
    void invalidLength() {
        shouldThrowException(Paths.get("src", "test", "resources", "invalid_length_prospects.txt"));
    }

    @Test
    void invalidName() {
        shouldThrowException(Paths.get("src", "test", "resources", "invalid_name_prospects.txt"));
    }

    @Test
    void invalidTotal() {
        shouldThrowException(Paths.get("src", "test", "resources", "invalid_total_prospects.txt"));
    }

    @Test
    void invalidYear() {
        shouldThrowException(Paths.get("src", "test", "resources", "invalid_year_prospects.txt"));
    }

}
