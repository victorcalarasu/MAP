package Model.Expression;

import Model.ADT.IHeap;
import Model.Exception.MyException;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.ADT.IDictionary;

public class ArithmeticExpression implements IExpression{
    IExpression expression1,expression2;
    char operation;

    public ArithmeticExpression(char operation,IExpression exp1,IExpression exp2){
        this.expression1=exp1;
        this.expression2=exp2;
        this.operation=operation;
    }

    @Override
    public IValue evaluate(IDictionary<String,IValue>table, IHeap<IValue> heap) throws MyException{
       IValue value1,value2;
       value1=expression1.evaluate(table,heap);
       if (value1.getType().equals(new IntType())){
           value2=expression2.evaluate(table,heap);
           if (value2.getType().equals(new IntType())) {
               IntValue i1 = (IntValue) value1;
               IntValue i2 = (IntValue) value2;
               int nr1,nr2;
               nr1=i1.getValue();
               nr2=i2.getValue();
               switch (operation){
                   case '+':
                       return new IntValue(nr1+nr2);
                   case '-':
                       return new IntValue(nr1-nr2);
                   case '*':
                       return new IntValue(nr1*nr2);
                   case ':':
                       if (nr2==0){
                           throw new MyException("Division by zero!");
                       }
                       return new IntValue(nr1/nr2);
                   default:
                       throw new MyException("Operation is not +,-,* or : !");
               }

           }
           else
               throw new MyException("Second operand is not an integer!");

       }
       else
           throw new MyException("First operand is not an integer!");
    }

    @Override
    public String toString(){
        return this.expression1.toString() + " " + operation + " " + this.expression2.toString();
    }
}
