package Model.Statements;

import Model.Exception.MyException;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Value.IValue;
import Model.Value.RefValue;

import java.io.IOException;

public class HeapAllocationStatement implements IStatement{
    private String varname;
    private IExpression exp;

    public HeapAllocationStatement(String s, IExpression e)
    {
        this.varname=s;
        this.exp=e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException{
        if(state.getSymbolTable().isDefined(varname))
        {
            IValue value=state.getSymbolTable().lookup(varname);
            if (value instanceof RefValue)
            {
                IValue expression=exp.evaluate(state.getSymbolTable(),state.getHeap());
                if (expression.getType().equals(((RefValue) value).getLocationType()))
                {
                    int location = state.getHeap().allocate(expression);
                    state.getSymbolTable().update(varname,new RefValue(location,expression.getType()));

                }
                else
                    throw new MyException("Expression value and locationType not the same Type!");
            }
            else
                throw new MyException("Value is not a RefType!");
        }
        else
            throw new MyException("VarName is not a variable name!");

        return null;
    }

    @Override
    public String toString(){return "new( " + varname + " " + exp.toString()+ " )";}

}
