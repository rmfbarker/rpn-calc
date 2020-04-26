package rpn.op;

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SqrtOpTest {
    @Test
    public void testBasicSquare() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        stack.push(9d);
        UndoableOperation op = new SqrtOp(stack, -1);
        op.doOp();
        assertThat(stack.peek(), is(3d));
    }

    @Test(expected = InsufficientOperandsException.class)
    public void testInsufficientOperands() throws Exception {
        UndoableOperation op = new SqrtOp(new Stack<Double>(), -1);
        op.doOp();
    }

    @Test
    public void testUndo() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        stack.push(9d);
        UndoableOperation op = new SqrtOp(stack, -1);
        op.doOp();
        op.undoOp();
        assertThat(stack.peek(), is(9d));
    }
}
