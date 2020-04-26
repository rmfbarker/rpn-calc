package rpn.op;

import java.util.Stack;

/**
 * Implements addition.
 */
public class AdditionOp extends DualOperandOperator {

    public AdditionOp(Stack<Double> stack, int index) {
        super(stack, index);
    }

    @Override
    double doArithmetic(double op1, double op2) {
        return op1 + op2;
    }

    @Override
    public String getName() {
        return "+";
    }
}
