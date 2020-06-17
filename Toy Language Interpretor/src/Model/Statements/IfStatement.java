package Model.Statements;

import Model.Exception.MyException;
import Model.ProgramState.ProgramState;
import Model.Value.IValue;
import Model.Expression.IExpression;
import Model.Value.BoolValue;

import java.beans.Expression;
import java.io.IOException;

public class IfStatement implements IStatement{
    IStatement st1,st2;
    IExpression exp;

    public IfStatement(IExpression e, IStatement s1, IStatement s2){
        this.st1=s1;
        this.st2=s2;
        this.exp=e;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        IValue r =this.exp.evaluate(state.getSymbolTable(),state.getHeap());
        if(((BoolValue)r).getValue())
            this.st1.execute(state);
        else
            this.st2.execute(state);
        return null;
    }

    @Override
    public String toString(){return "if " + exp.toString() + " then " + this.st1.toString() + " else " + this.st2.toString();}


}
