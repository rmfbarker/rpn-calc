package rpn.op;

import org.junit.Test;

import java.util.Stack;

import static org.mockito.Mockito.*;

public class UndoOpTest {

    @Test
    public void testUndo() throws Exception {
        UndoableOperation undoableOperation = mock(UndoableOperation.class);
        Stack<UndoableOperation> executedStack = mock(Stack.class);
        when(executedStack.pop()).thenReturn(undoableOperation);
        Operation undoOp = new UndoOp(executedStack, -1);
        undoOp.doOp();
        verify(executedStack).pop();
        verify(undoableOperation).undoOp();
    }

    @Test(expected = InsufficientOperandsException.class)
    public void testUndoWhenNoOpsHaveBeenExecuted() throws Exception {
        Operation undoOp = new UndoOp(new Stack<>(), -1);
        undoOp.doOp();
    }
}
