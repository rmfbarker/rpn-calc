package rpn.op;

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MultiplyOpTest {

    @Test
    public void testMultiply() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        stack.push(5d);
        stack.push(3d);
        DualOperandOperator op = new MultiplyOp(stack, -1);
        op.doOp();
        assertThat(stack.size(), is(1));
        assertThat(stack.peek(), is(15d));
    }

    @Test
    public void testUndo() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        stack.push(5d);
        stack.push(3d);
        DualOperandOperator op = new MultiplyOp(stack, -1);
        op.doOp();
        op.undoOp();
        assertThat(stack.size(), is(2));
        assertThat(stack.pop(), is(3d));
        assertThat(stack.pop(), is(5d));
    }
}
