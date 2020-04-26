package rpn.op;

import java.util.Stack;

/**
 * Implements square root operations.
 */
public class SqrtOp implements UndoableOperation {

    private final Stack<Double> stack;
    private final int index;
    private double op1;

    public SqrtOp(Stack<Double> stack, int index) {
        this.stack = stack;
        this.index = index;
    }

    @Override
    public void doOp() throws InsufficientOperandsException {
        if (stack.empty()) {
            throw new InsufficientOperandsException();
        }
        this.op1 = stack.pop();
        stack.push(Math.sqrt(op1));
    }

    @Override
    public void undoOp() {
        stack.pop();
        stack.push(op1);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getName() {
        return "sqrt";
    }
}
