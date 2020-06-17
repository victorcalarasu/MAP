package Model.Statements;

import Model.ADT.IDictionary;
import Model.Exception.MyException;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CloseRFileStatement implements IStatement{
    private IExpression exp;

    public CloseRFileStatement(IExpression e) {
        this.exp = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        IValue v = exp.evaluate(state.getSymbolTable(),state.getHeap());
        if(v.getType().equals(new StringType())){
            StringValue stringValue = (StringValue)v;
            if(state.getFileTable().isDefined(stringValue)){
                BufferedReader bufferedReader = state.getFileTable().lookup(stringValue);
                bufferedReader.close();
                state.getFileTable().remove(stringValue);
            }
            else throw new MyException("File not defined");
        }
        else throw new MyException("Expression not string type");

        return null;
        }


    @Override
    public String toString()
    {
        return "closeRFile";
    }
}
