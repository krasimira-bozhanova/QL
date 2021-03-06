package nl.uva.softwcons.ql.ast;

public class LineInfo {
    private final int line;
    private final int positionInLine;

    public LineInfo(final int line, final int positionInLine) {
        this.line = line;
        this.positionInLine = positionInLine;
    }

    public int getLine() {
        return line;
    }

    public int getPositionInLine() {
        return positionInLine;
    }

}
