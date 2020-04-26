package rpn.op;

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SubtractionOpTest {
    @Test
    public void testDivide() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        stack.push(12d);
        stack.push(2d);
        DualOperandOperator divOp = new SubtractionOp(stack, -1);
        divOp.doOp();
        assertThat(stack.size(), is(1));
        assertThat(stack.peek(), is(10d));
    }

    @Test
    public void testDivideAndUndo() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        stack.push(21d);
        stack.push(7d);
        DualOperandOperator divOp = new SubtractionOp(stack, -1);
        divOp.doOp();
        assertThat(stack.size(), is(1));
        assertThat(stack.peek(), is(14d));
        divOp.undoOp();
        assertThat(stack.size(), is(2));
        assertThat(stack.pop(), is(7d));
        assertThat(stack.pop(), is(21d));
    }
}
