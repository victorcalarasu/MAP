package Model.Statements;

import Model.ADT.IList;
import Model.Exception.MyException;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Value.IValue;

public class PrintStatement implements IStatement{
    IExpression exp;

    public PrintStatement(IExpression e)
    {
        this.exp=e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        IList<IValue> o=state.getOutput();
        o.add(this.exp.evaluate(state.getSymbolTable(),state.getHeap()));
        return null;
    }

    @Override
    public String toString(){return "print( " + this.exp.toString()+ " )";}
}

