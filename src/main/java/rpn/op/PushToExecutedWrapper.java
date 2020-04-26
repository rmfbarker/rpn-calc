package rpn.op;

import java.util.Stack;

/**
 * Wrapper class for undoable operations. Once an operation has been executed, it is pushed to a stack of executed
 * operations by this class. This class merely hides this from the implementer.
 */
public class PushToExecutedWrapper implements UndoableOperation {

    private final UndoableOperation op;
    private final Stack<UndoableOperation> executed;

    public PushToExecutedWrapper(UndoableOperation op, Stack<UndoableOperation> executed) {
        this.op = op;
        this.executed = executed;
    }

    @Override
    public void undoOp() {
        op.undoOp();
    }

    @Override
    public void doOp() throws InsufficientOperandsException, UnknownOperationException {
        op.doOp();
        // push onto the executed list
        executed.push(op);
    }

    @Override
    public int getIndex() {
        return op.getIndex();
    }

    @Override
    public String getName() {
        return op.getName();
    }
}
