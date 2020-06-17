package Model.Expression;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Exception.*;
import Model.Value.IValue;

public interface IExpression {

    IValue evaluate(IDictionary<String,IValue> table, IHeap<IValue> heap) throws MyException;

    @Override
    String toString();
}
