package rpn.op;

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PushOpTest {
    @Test
    public void testPushOne() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        PushOp op = new PushOp(stack, -1, 55);
        op.doOp();
        assertThat(stack.size(), is(1));
        assertThat(stack.peek(), is(55d));
    }

    @Test
    public void testPushOneAndUndo() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        PushOp op = new PushOp(stack, -1, 55);
        op.doOp();
        op.undoOp();
        assertThat(stack.empty(), is(true));
    }

    @Test
    public void testPushTwoAndUndoAndPushAndUndoTwice() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        PushOp op = new PushOp(stack, -1, 1);
        PushOp op2 = new PushOp(stack, -1, 2);
        PushOp op3 = new PushOp(stack, -1, 3);
        PushOp op4 = new PushOp(stack, -1, 4);
        op.doOp();
        op2.doOp();
        op2.undoOp();
        op3.doOp();
        op4.doOp();
        op4.undoOp();
        op3.undoOp();
        assertThat(stack.size(), is(1));
        assertThat(stack.peek(), is(1d));
    }
}
