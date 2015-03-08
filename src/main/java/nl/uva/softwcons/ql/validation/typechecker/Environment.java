package nl.uva.softwcons.ql.validation.typechecker;

import java.util.HashMap;
import java.util.Map;

import nl.uva.softwcons.ql.ast.expression.identifier.Identifier;
import nl.uva.softwcons.ql.ast.type.Type;
import nl.uva.softwcons.ql.ast.type.UndefinedType;

public class Environment {

    private final Map<Identifier, Type> identifiers;

    public Environment() {
        this.identifiers = new HashMap<>();
    }

    /**
     * Returns the type of the given variable or UNDEFINED if the variable is
     * not present in the environment.
     * 
     * @param variableName
     * @return the type of the given variable
     */
    public Type resolveVariable(final Identifier variableName) {
        return this.identifiers.getOrDefault(variableName, UndefinedType.instance);
    }

    public void defineVariable(final Identifier variableName, final Type variableType) {
        this.identifiers.put(variableName, variableType);
    }
}