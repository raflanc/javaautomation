package kalkulator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Kocurek")
class KalkulatorAppTest {

    @Test
    public void addTwoPositiveIntegers() {
        KalkulatorApp kalkulatorek = new KalkulatorApp();
        int suma = kalkulatorek.add(1, 2);
        assertEquals(3, suma, "Sprawdź, że dodawanie liczb całkowitych działa");
    }

    @Test
    public void addTwoPositiveDoubles() {
        KalkulatorApp kalkulatorek = new KalkulatorApp();
        double suma = kalkulatorek.add(1.2, 2.4);
        assertEquals(3.6, suma, 0.000000001, "Sprawdź, że dodawanie liczb zmiennoprzecinkowych działa");
    }

    @Test
    public void divide() {
        KalkulatorApp kalkulatorek = new KalkulatorApp();
        double division = kalkulatorek.divide(2, 3);
        assertEquals(0.6666, division, 0.0001, "Sprawdź, że dzielenie liczb całkowitych działa");
    }
}