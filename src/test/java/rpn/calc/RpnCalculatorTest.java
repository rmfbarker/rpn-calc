package rpn.calc;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RpnCalculatorTest {

    private RpnCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new RpnCalculator(new Stack<>(), new Stack<>(), new OperandFactory(), CalculatorUtils.provideDecimalFormat());
    }

    @Test
    public void testSubmitNumbersWithoutOperator() throws Exception {
        assertThat(calculator.submit("5 2"), is("stack: 5 2"));
    }

    @Test
    public void testSqrt2() throws Exception {
        assertThat(calculator.submit("2 sqrt"), is("stack: 1.4142135623"));
    }

    @Test
    public void testClear() throws Exception {
        assertThat(calculator.submit("2 sqrt"), is("stack: 1.4142135623"));
        assertThat(calculator.submit("clear 9 sqrt"), is("stack: 3"));
    }

    @Test
    public void testUndo() throws Exception {
        assertThat(calculator.submit("5 4 3 2 undo undo"), is("stack: 5 4"));
    }

    @Test
    public void testUndoOverTwoCommands() throws Exception {
        String result = calculator.submit("5 4 3 2");
        assertThat(result, is("stack: 5 4 3 2"));
        result = calculator.submit("undo undo");
        assertThat(result, is("stack: 5 4"));
    }

    @Test
    public void testUndoAndMultiply() throws Exception {
        assertThat(calculator.submit("5 4 3 2"), is("stack: 5 4 3 2"));
        assertThat(calculator.submit("undo undo *"), is("stack: 20"));
    }

    @Test
    public void testDivide() throws Exception {
        assertThat(calculator.submit("7 12 2 /"), is("stack: 7 6"));
        assertThat(calculator.submit("*"), is("stack: 42"));
        assertThat(calculator.submit("4 /"), is("stack: 10.5"));
    }

    @Test
    public void testMultiplyAndAdd() throws Exception {
        assertThat(calculator.submit("1 2 3 * 5 + *"), is("stack: 11"));
    }

    @Test
    public void testInsufficientParams() throws Exception {
        String res = calculator.submit("1 2 3 * 5 + * * 6 5");
        assertThat(res, is("operator * (position: 15): insufficient parameters\nstack: 11"));
    }

    @Test
    public void testInsufficientParamsMultiWhitespace() throws Exception {
        String res = calculator.submit("1  2 3 * 5 + * * 6 5");
        assertThat(res, is("operator * (position: 16): insufficient parameters\nstack: 11"));
    }

    @Test
    public void testInsufficientParamsForAddition() throws Exception {
        String res = calculator.submit("1 2 3 * 5 + * + 6 5");
        assertThat(res, is("operator + (position: 15): insufficient parameters\nstack: 11"));
    }

    @Test
    public void testMinus() throws Exception {
        assertThat(calculator.submit("5 2 -"), is("stack: 3"));
    }

    @Test
    public void testFormatDecimal() throws Exception {
        assertThat(calculator.format(5.0d), is("5"));
    }

    @Test
    public void testFormatLongDecimal() throws Exception {
        assertThat(calculator.format(5.123456789123456d), is("5.1234567891"));
    }

    @Test
    public void testPushNegativeNumber() throws Exception {
        assertThat(calculator.submit("-1"), is("stack: -1"));
    }

    @Test
    public void testPushDecimal() throws Exception {
        assertThat(calculator.submit("3.14159"), is("stack: 3.14159"));
    }

    @Test
    public void testUnknownOp() throws Exception {
        String res = calculator.submit("unknown");
        assertThat(res, is("unknown operator: 'unknown' (position: 7)\nstack:"));
    }

    @Test
    public void testInfinity() throws Exception {
        String res = calculator.submit("1 0 /");
        assertThat(res, is("stack: Infinity"));
    }

    @Test
    public void testNegInfinity() throws Exception {
        String res = calculator.submit("-1 0 /");
        assertThat(res, is("stack: -Infinity"));
    }

    @Test
    public void testSqrtNegativePrintedToScreenNicely() throws Exception {
        String res = calculator.submit("-1 sqrt");
        assertThat(res, is("stack: NaN"));
    }
}
