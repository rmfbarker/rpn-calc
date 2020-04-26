package rpn.op;

public class UnknownOp implements Operation {

    private final int index;
    private final String command;

    public UnknownOp(int index, String command) {
        this.index = index;
        this.command = command;
    }

    @Override
    public void doOp() throws UnknownOperationException {
        throw new UnknownOperationException();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getName() {
        return command;
    }
}
