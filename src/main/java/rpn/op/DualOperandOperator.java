package rpn.op;

import java.util.Stack;

/**
 * Base class for all operations which take two operands. Only the actual arithmetic is left to be implemented by the
 * base class.
 */
public abstract class DualOperandOperator implements UndoableOperation {

    private final Stack<Double> stack;
    private final int index;
    private double op1;
    private double op2;

    DualOperandOperator(Stack<Double> stack, int index) {
        this.stack = stack;
        this.index = index;
    }

    @Override
    public void doOp() throws InsufficientOperandsException {
        // ensure that we have enough operands
        if (stack.size() < 2) {
            throw new InsufficientOperandsException();
        }

        // pop the operands off the stack
        this.op1 = stack.pop();
        this.op2 = stack.pop();
        // push the result onto the stack
        stack.push(doArithmetic(op1, op2));
    }

    abstract double doArithmetic(double op1, double op2);

    @Override
    public void undoOp() {
        // pop the result of the operation off the stack
        stack.pop();
        // put the operands back onto the stack
        stack.push(op2);
        stack.push(op1);
    }

    @Override
    public int getIndex() {
        return index;
    }
}
