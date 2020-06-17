package Model.Expression;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Exception.MyException;
import Model.Value.IValue;

public class ValueExpression implements IExpression{
    IValue value;

    public ValueExpression(IValue x){
        this.value=x;
    }

    @Override
    public IValue evaluate(IDictionary<String,IValue> table, IHeap<IValue> heap) throws MyException {
        return this.value;
    }

    @Override
    public String toString(){ return this.value.toString();}
}
