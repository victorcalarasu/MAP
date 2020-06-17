package Model.Statements;

import Model.ADT.IDictionary;
import Model.Exception.MyException;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFileStatement implements IStatement {
    private IExpression exp;

    public OpenRFileStatement(IExpression e) {
        this.exp = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, FileNotFoundException {
        IValue v;
        v = this.exp.evaluate(state.getSymbolTable(),state.getHeap());
        if(v.getType().equals(new StringType())){
            StringValue string = (StringValue)v;
            if(!(state.getFileTable().isDefined(string))){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(string.getValue()));
                state.getFileTable().update(string,bufferedReader);
            }
            else throw new MyException("File already exists");
        }
        else throw new MyException("Not string type");
        return null;
        }

    @Override
    public String toString()
    {
        return "openRFile ";
    }
}






