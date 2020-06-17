package Model.Expression;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Exception.MyException;
import Model.Value.IValue;

public class VariableExpression implements IExpression{
    String v;

    public VariableExpression(String x){
        this.v=x;
    }

    @Override
    public IValue evaluate(IDictionary<String,IValue> table, IHeap<IValue> heap) throws MyException {
        return table.lookup(v);
    }

    @Override
    public String toString(){ return this.v;}
}
