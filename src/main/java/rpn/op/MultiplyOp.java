package rpn.op;

import java.util.Stack;

/**
 * Implements multiplication.
 */
public class MultiplyOp extends DualOperandOperator {

    public MultiplyOp(Stack<Double> stack, int index) {
        super(stack, index);
    }

    @Override
    double doArithmetic(double op1, double op2) {
        return op1 * op2;
    }

    @Override
    public String getName() {
        return "*";
    }
}
