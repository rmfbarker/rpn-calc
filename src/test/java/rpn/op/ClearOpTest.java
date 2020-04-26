package rpn.op;

import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ClearOpTest {
    @Test
    public void testClear() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        stack.push(3d);
        stack.push(55d);
        UndoableOperation clear = new ClearOp(stack, -1);
        clear.doOp();
        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void testClearAndUndo() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        stack.push(3d);
        stack.push(55d);
        UndoableOperation clear = new ClearOp(stack, -1);
        clear.doOp();
        clear.undoOp();
        assertThat(stack.pop(), is(55d));
        assertThat(stack.pop(), is(3d));
    }
}
