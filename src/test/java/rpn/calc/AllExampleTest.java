package rpn.calc;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AllExampleTest {

    private RpnCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new RpnCalculator(new Stack<>(), new Stack<>(), new OperandFactory(), CalculatorUtils.provideDecimalFormat());
    }

    @Test
    public void testOne() throws Exception {
        assertThat(calculator.submit("5 2"), is("stack: 5 2"));
    }

    @Test
    public void testTwo() throws Exception {
        assertThat(calculator.submit("2 sqrt"), is("stack: 1.4142135623"));
        assertThat(calculator.submit("clear 9 sqrt"), is("stack: 3"));
    }

    @Test
    public void testThree() throws Exception {
        assertThat(calculator.submit("5 2 -"), is("stack: 3"));
        assertThat(calculator.submit("3 -"), is("stack: 0"));
        assertThat(calculator.submit("clear"), is("stack:"));
    }

    @Test
    public void testFourModified() throws Exception {
        assertThat(calculator.submit("5 4 3 2"), is("stack: 5 4 3 2"));
        assertThat(calculator.submit("undo undo *"), is("stack: 20"));
        assertThat(calculator.submit("5 *"), is("stack: 100"));
        assertThat(calculator.submit("undo"), is("stack: 20 5"));
    }

    @Test
    public void testFourAdditionalUndo() throws Exception {
        assertThat(calculator.submit("5 4 3 2"), is("stack: 5 4 3 2"));
        assertThat(calculator.submit("undo undo *"), is("stack: 20"));
        assertThat(calculator.submit("5 *"), is("stack: 100"));
        assertThat(calculator.submit("undo undo"), is("stack: 20"));
    }

    @Test
    public void testFive() throws Exception {
        assertThat(calculator.submit("7 12 2 /"), is("stack: 7 6"));
        assertThat(calculator.submit("*"), is("stack: 42"));
        assertThat(calculator.submit("4 /"), is("stack: 10.5"));
    }

    @Test
    public void testSix() throws Exception {
        assertThat(calculator.submit("1 2 3 4 5"), is("stack: 1 2 3 4 5"));
        assertThat(calculator.submit("*"), is("stack: 1 2 3 20"));
        assertThat(calculator.submit("clear 3 4 -"), is("stack: -1"));
    }

    @Test
    public void testSeven() throws Exception {
        assertThat(calculator.submit("1 2 3 4 5"), is("stack: 1 2 3 4 5"));
        assertThat(calculator.submit("*"), is("stack: 1 2 3 20"));
        assertThat(calculator.submit("* * *"), is("stack: 120"));
    }

    @Test
    public void testEight() throws Exception {
        assertThat(calculator.submit("1 2 3 * 5 + * * 6 5"), is("operator * (position: 15): insufficient " +
                "parameters\nstack: 11"));
    }
}
