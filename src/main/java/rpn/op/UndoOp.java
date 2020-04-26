package rpn.op;

import java.util.Stack;

/**
 * Implements undo. This is the only calculator operation that cannot be undone, otherwise 'undo undo' would do nothing
 * instead of undoing the last two operations.
 */
public class UndoOp implements Operation {

    private final Stack<UndoableOperation> executedOps;
    private final int index;

    public UndoOp(Stack<UndoableOperation> executedOps, int index) {
        this.executedOps = executedOps;
        this.index = index;
    }

    /**
     * Undo the last executed operation on the calculator.
     *
     * @throws InsufficientOperandsException if there are no executed operations to undo.
     */
    @Override
    public void doOp() throws InsufficientOperandsException {
        if (executedOps.isEmpty()) {
            throw new InsufficientOperandsException();
        }
        executedOps.pop().undoOp();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getName() {
        return "undo";
    }
}
