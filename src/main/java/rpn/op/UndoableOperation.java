package rpn.op;

/**
 * Some operations on the calculator can be undone.
 */
public interface UndoableOperation extends Operation {

    /**
     * Revert to previous state before the operation was executed
     */
    void undoOp();


}
