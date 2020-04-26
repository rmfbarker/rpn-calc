package rpn.calc;

import rpn.op.Operation;
import rpn.op.UndoableOperation;
import org.junit.Test;

import java.util.List;
import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OperandFactoryTest {

    private final OperandFactory factory = new OperandFactory();

    @Test
    public void testPushOneNumber() throws Exception {
        List<Operation> operationList = factory.createOperations("5", null, null);
        assertThat(operationList.size(), is(1));
        assertThat(operationList.get(0).getIndex(), is(1));
    }

    @Test
    public void testPushTwoNumbers() throws Exception {
        List<Operation> operationList = factory.createOperations("5 20", null, null);
        assertThat(operationList.size(), is(2));
        assertThat(operationList.get(0).getIndex(), is(1));
        assertThat(operationList.get(1).getIndex(), is(4));
    }

    @Test
    public void testPushTwoNumbersAndAdd() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        List<Operation> operationList = factory.createOperations("5 20 +", stack, new Stack<UndoableOperation>());
        for (Operation op : operationList) {
            op.doOp();
        }
        assertThat(stack.size(), is(1));
        assertThat(stack.peek(), is(25d));
    }

    @Test
    public void testPushTwoNumbersAndAddAndClear() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        List<Operation> operationList = factory.createOperations("5 20 + clear", stack, new Stack<UndoableOperation>());
        for (Operation op : operationList) {
            op.doOp();
        }
        assertThat(stack.isEmpty(), is(true));
    }

    @Test
    public void testPushTwoNumbersAndAddAndClearAndUndo() throws Exception {
        Stack<Double> stack = new Stack<Double>();
        List<Operation> operationList = factory.createOperations("5 20 + clear undo", stack,
                new Stack<UndoableOperation>());
        for (Operation op : operationList) {
            op.doOp();
        }
        assertThat(stack.size(), is(1));
        assertThat(stack.peek(), is(25d));
    }

    @Test
    public void testLongInputString() throws Exception {
        List<Operation> operationList = factory.createOperations("1 2 3 * 5 + * * 6 5", null, null);
        assertThat(operationList.size(), is(10));
        assertThat(operationList.get(7).getIndex(), is(15));
    }

    @Test
    public void testLongInputStringWithAdditionalWhitespace() throws Exception {
        List<Operation> operationList = factory.createOperations("1 2 3  * 5 + * * 6 5", null, null);
        assertThat(operationList.size(), is(10));
        assertThat(operationList.get(7).getIndex(), is(16));
    }

    @Test
    public void testLongInputStringWithAdditionalWhitespaceAndExtraAtEnd() throws Exception {
        List<Operation> operationList = factory.createOperations("1 2 3  * 5 + * * 6 5  ", null, null);
        assertThat(operationList.size(), is(10));
        assertThat(operationList.get(7).getIndex(), is(16));
    }

    @Test
    public void testGetTokens() throws Exception {
        List<OperandFactory.Token> tokens = factory.getTokensFrom("55  8 7 * / sqrt");
        assertThat(tokens.size(), is(6));
        assertThat(tokens.get(0).getCommand(), is("55"));
        assertThat(tokens.get(0).getIndex(), is(2));

        assertThat(tokens.get(1).getCommand(), is("8"));
        assertThat(tokens.get(1).getIndex(), is(5));

        assertThat(tokens.get(2).getCommand(), is("7"));
        assertThat(tokens.get(2).getIndex(), is(7));

        assertThat(tokens.get(3).getCommand(), is("*"));
        assertThat(tokens.get(3).getIndex(), is(9));

        assertThat(tokens.get(4).getCommand(), is("/"));
        assertThat(tokens.get(4).getIndex(), is(11));

        assertThat(tokens.get(5).getCommand(), is("sqrt"));
        assertThat(tokens.get(5).getIndex(), is(16));
    }

    @Test
    public void testGetTokensWithExtraWhiteSpaceAtEnds() throws Exception {
        List<OperandFactory.Token> tokens = factory.getTokensFrom("  55  8 7 * / sqrt  ");
        assertThat(tokens.size(), is(6));
        assertThat(tokens.get(0).getCommand(), is("55"));
        assertThat(tokens.get(0).getIndex(), is(4));

        assertThat(tokens.get(1).getCommand(), is("8"));
        assertThat(tokens.get(1).getIndex(), is(7));

        assertThat(tokens.get(2).getCommand(), is("7"));
        assertThat(tokens.get(2).getIndex(), is(9));

        assertThat(tokens.get(3).getCommand(), is("*"));
        assertThat(tokens.get(3).getIndex(), is(11));

        assertThat(tokens.get(4).getCommand(), is("/"));
        assertThat(tokens.get(4).getIndex(), is(13));

        assertThat(tokens.get(5).getCommand(), is("sqrt"));
        assertThat(tokens.get(5).getIndex(), is(18));
    }
}
