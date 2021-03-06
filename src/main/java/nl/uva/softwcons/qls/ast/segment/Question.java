package nl.uva.softwcons.qls.ast.segment;

import nl.uva.softwcons.ql.ast.LineInfo;
import nl.uva.softwcons.ql.ast.expression.identifier.Identifier;
import nl.uva.softwcons.qls.ast.widgetstyle.StyledWidget;

public class Question extends PageSegment {
    private final Identifier id;
    private final StyledWidget widget;

    public Question(final Identifier id) {
        this.id = id;
        this.widget = new StyledWidget();
    }

    public Question(final Identifier id, final StyledWidget widget) {
        this.id = id;
        this.widget = widget;
    }

    public LineInfo getLineInfo() {
        return id.getLineInfo();
    }

    public Identifier getId() {
        return id;
    }

    public StyledWidget getStyledWidget() {
        return widget;
    }

    public boolean hasWidget() {
        return this.widget.getWidgetType().isPresent();
    }

    @Override
    public <T> T accept(final SegmentVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public <T, V> T accept(final SegmentValueVisitor<T, V> visitor, final V value) {
        return visitor.visit(this, value);
    }

}
