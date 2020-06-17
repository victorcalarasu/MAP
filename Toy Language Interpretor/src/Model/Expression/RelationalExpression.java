package Model.Expression;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ADT.MyDictionary;
import Model.ADT.MyHeap;
import Model.Exception.MyException;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

public class RelationalExpression implements IExpression{

    private IExpression expr1, expr2;
    private String string;

    public RelationalExpression(IExpression expr1, IExpression expr2, String string){
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.string = string;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> table, IHeap<IValue> heap) throws MyException {

        IValue v1,v2;

        v1 = expr1.evaluate(table, heap);
        if(v1.getType().equals(new IntType())) {
            v2 = expr2.evaluate(table, heap);
            if(v2.getType().equals(new IntType())){
                int i1 = ((IntValue)v1).getValue();
                int i2 = ((IntValue)v2).getValue();
                switch (string){
                    case "<" :
                        return new BoolValue(i1 < i2);
                    case "<=":
                        return new BoolValue(i1 <= i2);
                    case "==" :
                        return new BoolValue(i1 == i2);
                    case "!=":
                        return new BoolValue(i1 != i2);
                    case ">":
                        return new BoolValue(i1 > i2);
                    case ">=":
                        return new BoolValue(i1 >= i2);
                    default:
                        throw new MyException("Unknown operand!");
                }
            }
            throw new MyException("Second parameter not Int");
        }
        throw new MyException("First parameter not Int");
    }

    @Override
    public String toString(){
        return this.expr1.toString() + string + this.expr2.toString();
    }
}
