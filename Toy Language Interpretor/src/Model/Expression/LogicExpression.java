package Model.Expression;

import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.ADT.*;
import Model.Exception.MyException;
import Model.Type.BoolType;


public class LogicExpression implements IExpression{
    IExpression exp1,exp2;
    String operation;

    public LogicExpression(IExpression exp1, IExpression exp2, String operation)
    {
        this.exp1=exp1;
        this.exp2=exp2;
        this.operation=operation;

    }

    @Override
    public IValue evaluate(IDictionary<String,IValue> table, IHeap<IValue> heap) throws MyException{
        IValue value1,value2;
        value1=exp1.evaluate(table,heap);
        if (value1.getType().equals(new BoolType())){
            value2=exp2.evaluate(table,heap);
            if(value2.getType().equals(new BoolType())){
                BoolValue i1 = (BoolValue) value1;
                BoolValue i2= (BoolValue) value2;
                boolean n1,n2;
                n1=i1.getValue();
                n2=i2.getValue();
                if(operation=="and"){
                    return new BoolValue(n1 && n2);
                }
                if(operation=="or"){
                    return new BoolValue(n1 || n2);
                }
                else throw new MyException("Operand should be and/or!");
            }
            else throw new MyException("Second operand is not a boolean variable!");
        }
        else throw new MyException("First operand is not a boolean variable!");
    }

    @Override
    public String toString() {return exp1.toString() + " " + operation + " "+exp2.toString();}
}
