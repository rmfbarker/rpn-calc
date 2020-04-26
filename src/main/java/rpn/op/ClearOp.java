package rpn.op;

import java.util.Stack;

/**
 * Clears the calculator stack. This operation is reversible. When executing it saves a snapshot of the calculator stack
 * and restores the stack when 'undo' is called.
 */
public class ClearOp implements UndoableOperation {

    private final Stack<Double> stack;
    private final int index;
    private Double[] snapshot;

    public ClearOp(Stack<Double> stack, int index) {
        this.stack = stack;
        this.index = index;
    }

    @Override
    public void doOp() {
        // take a snapshot of the stack so that we can revert back
        snapshot = stack.toArray(new Double[0]);
        // clear
        stack.clear();
    }

    @Override
    public void undoOp() {
        // clear the stack and push contents back onto the stack
        stack.clear();
        for (double numb : snapshot) {
            stack.push(numb);
        }
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getName() {
        return "clear";
    }
}
