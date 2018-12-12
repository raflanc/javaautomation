package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorAppTest {

    @Test
    public void addTwoPositiveIntegers() {
        CalculatorApp calc = new CalculatorApp();
        int suma = calc.add(1, 2);
        assertEquals(3, suma, "Sprawdź, że dodawanie liczb całkowitych działa");
    }

    @Test
    public void addTwoPositiveDoubles() {
        CalculatorApp calc = new CalculatorApp();
        double suma = calc.add(1.2, 2.4);
        assertEquals(3.6, suma, 0.000000001, "Sprawdź, że dodawanie liczb zmiennoprzecinkowych działa");
    }

    @Test
    public void divide() {
        CalculatorApp calc = new CalculatorApp();
        double division = calc.divide(2, 3);
        assertEquals(0.6666, division, 0.0001, "Sprawdź, że dzielenie liczb całkowitych działa");
    }
}