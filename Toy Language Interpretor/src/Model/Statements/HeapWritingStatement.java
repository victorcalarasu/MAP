package Model.Statements;

import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Exception.MyException;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Value.IValue;
import Model.Value.RefValue;

public class HeapWritingStatement implements IStatement {
    String varname;
    IExpression exp;

    public HeapWritingStatement(String v,IExpression e)
    {
        this.varname=v;
        this.exp=e;
    }

    @Override
    public String toString(){return "wH( " +this.varname +","+ this.exp.toString()+ " )";}

    @Override
    public ProgramState execute(ProgramState state) throws MyException
    {
        if(state.getSymbolTable().isDefined(varname))
        {
            IValue value=state.getSymbolTable().lookup(varname);
            if( value instanceof RefValue)
            {
                int address=((RefValue)value).getAdress();
                if (state.getHeap().get(address)!=null)
                {
                    IValue value1=this.exp.evaluate(state.getSymbolTable(),state.getHeap());
                    if (value1.getType().equals(((RefValue)value).getLocationType()))
                    {
                        state.getHeap().put(address,value1);
                    }
                    else
                        throw new MyException("Incompatible type!");
                }
                else
                    throw new MyException("Address not in table!");
            }
            else
                throw new MyException("Value is not a RefType!");
        }
        else
            throw new MyException("Variable not defined in table!");
        return null;
    }
}
