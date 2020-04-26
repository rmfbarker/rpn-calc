package rpn.op;

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AdditionOpTest {
    @Test
    public void testDivide() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        stack.push(12d);
        stack.push(2d);
        DualOperandOperator divOp = new AdditionOp(stack, -1);
        divOp.doOp();
        assertThat(stack.size(), is(1));
        assertThat(stack.peek(), is(14d));
    }

    @Test
    public void testDivideAndUndo() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        stack.push(21d);
        stack.push(7d);
        DualOperandOperator divOp = new AdditionOp(stack, -1);
        divOp.doOp();
        assertThat(stack.size(), is(1));
        assertThat(stack.peek(), is(28d));
        divOp.undoOp();
        assertThat(stack.size(), is(2));
        assertThat(stack.pop(), is(7d));
        assertThat(stack.pop(), is(21d));
    }
}
