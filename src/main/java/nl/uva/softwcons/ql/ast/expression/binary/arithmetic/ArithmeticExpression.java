package nl.uva.softwcons.ql.ast.expression.binary.arithmetic;

import nl.uva.softwcons.ql.ast.LineInfo;
import nl.uva.softwcons.ql.ast.expression.Expression;
import nl.uva.softwcons.ql.ast.expression.binary.BinaryExpression;
import nl.uva.softwcons.ql.ast.type.NumberType;
import nl.uva.softwcons.ql.ast.type.Type;
import nl.uva.softwcons.ql.ast.type.UndefinedType;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public abstract class ArithmeticExpression extends BinaryExpression {
    private static final Table<Type, Type, Type> ARITHMETIC_OPERATORS_TABLE = HashBasedTable.create();
    static {
        ARITHMETIC_OPERATORS_TABLE.put(NumberType.instance, NumberType.instance, NumberType.instance);
    }
    private final LineInfo lineInfo;

    public ArithmeticExpression(final Expression left, final Expression right, final LineInfo lineInfo) {
        super(left, right);

        this.lineInfo = lineInfo;
    }

    public static Type resolveType(final Type type, final Type otherType) {
        final Type resolvedType = ARITHMETIC_OPERATORS_TABLE.get(type, otherType);
        return resolvedType != null ? resolvedType : UndefinedType.instance;
    }

    @Override
    public LineInfo getLineInfo() {
        return lineInfo;
    }
}