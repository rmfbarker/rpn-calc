package rpn.calc;

import rpn.op.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Factory class used to convert user input string to executable commands. The input string is manually parsed instead
 * of using a StringTokenizer so that we can keep an index position to the command's position within the user's original
 * input string for error messages.
 */
public class OperandFactory {

    public List<Operation> createOperations(String userInput, Stack<Double> stack, Stack<UndoableOperation> executed) {
        List<Operation> result = new ArrayList<>();

        List<Token> tokens = getTokensFrom(userInput);
        for (Token token : tokens) {
            String command = token.getCommand();
            int index = token.getIndex();

            if (command.equals("undo")) {
                result.add(new UndoOp(executed, index));
                continue;
            }
            UndoableOperation undoableOp;
            switch (command) {
                case "*":
                    undoableOp = new MultiplyOp(stack, index);
                    break;
                case "+":
                    undoableOp = new AdditionOp(stack, index);
                    break;
                case "/":
                    undoableOp = new DivisionOp(stack, index);
                    break;
                case "-":
                    undoableOp = new SubtractionOp(stack, index);
                    break;
                case "clear":
                    undoableOp = new ClearOp(stack, index);
                    break;
                case "sqrt":
                    undoableOp = new SqrtOp(stack, index);
                    break;
                default:
                    try {
                        undoableOp = new PushOp(stack, index, Double.parseDouble(command));
                    } catch (NumberFormatException e) {
                        result.add(new UnknownOp(index, command));
                        continue;
                    }
                    break;
            }
            result.add(new PushToExecutedWrapper(undoableOp, executed));
        }

        return result;
    }

    List<Token> getTokensFrom(String input) {
        List<Token> result = new ArrayList<>();

        int strtIdx = 0, endIdx;
        while (strtIdx < input.length()) {
            if (input.charAt(strtIdx) == ' ') {
                // gobble whitespace
                strtIdx++;
                continue;
            }

            // find next whitespace
            endIdx = input.indexOf(" ", strtIdx);
            if (endIdx == -1) {
                // reached the end of the string.
                endIdx = input.length();
            }

            String command = input.substring(strtIdx, endIdx);
            result.add(new Token(command, endIdx));
            strtIdx = endIdx + 1;
        }
        return result;

    }

    static class Token {
        private final String command;
        private final int index;

        private Token(String command, int index) {
            this.command = command;
            this.index = index;
        }

        String getCommand() {
            return command;
        }

        int getIndex() {
            return index;
        }
    }
}
