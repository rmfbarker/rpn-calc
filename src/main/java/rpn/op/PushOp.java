package rpn.op;

import java.util.Stack;

/**
 * Pushes a number onto the calculator stack.
 */
public class PushOp implements UndoableOperation {

    private final Stack<Double> stack;
    private final int index;
    private final double number;

    public PushOp(Stack<Double> stack, int index, double number) {
        this.stack = stack;
        this.index = index;
        this.number = number;
    }

    @Override
    public void doOp() {
        stack.push(number);
    }

    @Override
    public void undoOp() {
        // simply remove the number that we previously pushed onto the stack
        stack.pop();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getName() {
        return "" + number;
    }
}
