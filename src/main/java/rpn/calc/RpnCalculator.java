package rpn.calc;

import rpn.op.InsufficientOperandsException;
import rpn.op.Operation;
import rpn.op.UndoableOperation;
import rpn.op.UnknownOperationException;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Stack;

/**
 * Reverse Polish Notation calculator. Takes user input and converts it to executable RPN commands. These commands can
 * be undone. After each command the current state of the stack is returned.
 */
public class RpnCalculator implements Calculator {

    private static final String ERROR_FMT = "operator %s (position: %d): insufficient parameters\n%s";
    private static final String UNKNOWN_FMT = "unknown operator: '%s' (position: %d)\n%s";

    private final Stack<Double> stack;
    private final Stack<UndoableOperation> executed;
    private final OperandFactory operandFactory;
    private final DecimalFormat decimalFormat;

    public RpnCalculator(Stack<Double> stack, Stack<UndoableOperation> executed, OperandFactory operandFactory,
                         DecimalFormat decimalFormat) {
        this.stack = stack;
        this.executed = executed;
        this.operandFactory = operandFactory;
        this.decimalFormat = decimalFormat;
    }

    /**
     * Submit user input to the calculator
     *
     * @param input user input string
     * @return string representation of the current state of the calculator stack
     */
    @Override
    public String submit(String input) {
        List<Operation> operations = operandFactory.createOperations(input, stack, executed);
        for (Operation op : operations) {
            try {
                op.doOp();
            } catch (InsufficientOperandsException e) {
                return String.format(ERROR_FMT, op.getName(), op.getIndex(), printStack(stack));
            } catch (UnknownOperationException e) {
                return String.format(UNKNOWN_FMT, op.getName(), op.getIndex(), printStack(stack));
            }
        }

        return printStack(stack);
    }

    /**
     * Return a string representation of the current state of the calculator stack
     *
     * @param stack calculator stack
     * @return string representation
     */
    private String printStack(Stack<Double> stack) {
        StringBuilder builder = new StringBuilder();
        builder.append("stack: ");
        for (Object tok : stack.toArray()) {
            builder.append(format((Double) tok)).append(" ");
        }
        return builder.toString().trim();
    }

    /**
     * Format any floating point numbers to 10dp, removing any trailing zeroes.
     *
     * @param number to format
     * @return formatted number to 10dp, without trailing zeroes
     */
    String format(double number) {
        if (Double.isNaN(number) || Double.isInfinite(number)) {
            return Double.toString(number);
        }
        return decimalFormat.format(number);
    }
}
