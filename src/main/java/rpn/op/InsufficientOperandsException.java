package rpn.op;

/**
 * Exception for the case that there are not enough operands on the calculator stack for a given operator, or in the
 * case of an 'undo' operation, that there are no executed operations to reverse.
 */
public class InsufficientOperandsException extends Exception {

}
