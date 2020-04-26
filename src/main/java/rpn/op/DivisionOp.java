package rpn.op;

import java.util.Stack;

public class DivisionOp extends DualOperandOperator {

    public DivisionOp(Stack<Double> stack, int index) {
        super(stack, index);
    }

    @Override
    double doArithmetic(double op1, double op2) {
        return op2 / op1;
    }


    @Override
    public String getName() {
        return "/";
    }
}
