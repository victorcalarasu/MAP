package Model.Expression;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Exception.MyException;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;


public class HeapReadingExpression implements IExpression {
    IExpression exp;

    public HeapReadingExpression(IExpression e)
    {
        this.exp=e;
    }

    @Override
    public IValue evaluate(IDictionary<String,IValue> table, IHeap<IValue> heap) throws MyException
    {
        IValue value=exp.evaluate(table,heap);
        if (value instanceof RefValue)
        {
            if (heap.get(((RefValue) value).getAdress())!=null)
                return heap.get(((RefValue)value).getAdress());
            else
                throw new MyException("Address is not a key in HeapTable!");
        }
        else
            throw new MyException("Value not of type RefType!");
    }

    @Override
    public String toString(){return "rH( "+this.exp.toString()+ " )";}
}
