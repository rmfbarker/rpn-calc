package rpn.op;

/**
 * All operations on the calculator implement this interface (operand pushes and operators).
 */
public interface Operation {

    /**
     * Execute the operation
     */
    void doOp() throws InsufficientOperandsException, UnknownOperationException;

    /**
     * Index is the position of the operand/operator within the input string. Used for error messages.
     *
     * @return index
     */
    int getIndex();

    /**
     * The name or symbol of the operation
     *
     * @return name
     */
    String getName();
}
