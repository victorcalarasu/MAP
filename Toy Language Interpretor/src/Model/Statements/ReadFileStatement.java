package Model.Statements;

import Model.ADT.IDictionary;
import Model.Exception.MyException;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement {
    private IExpression exp;
    private String name;

    public ReadFileStatement(IExpression e, String n){
        this.exp=e;
        this.name=n;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        if(state.getSymbolTable().isDefined(name)){
            if(state.getSymbolTable().lookup(name).getType().equals(new IntType())){
                IValue v = exp.evaluate(state.getSymbolTable(),state.getHeap());
                if(v.getType().equals(new StringType())){
                    StringValue stringValue = (StringValue)v;
                    if(state.getFileTable().isDefined(stringValue)){
                        BufferedReader bufferedReader = state.getFileTable().lookup(stringValue);
                        String read = bufferedReader.readLine();
                        IntValue readValue;
                        if(read == null){
                            readValue = new IntValue(0);
                        }
                        else {
                            readValue = new IntValue(Integer.parseInt(read));
                        }
                        state.getSymbolTable().update(name, readValue);
                    }
                    else throw new MyException("Not entry associated with the File Table");
                }
                else throw new MyException("Expression didn't evaluate to a string");
            }
            else throw new MyException("Value type is not int");

        }
        else throw new MyException("File already defined");

        return null;
    }


    @Override
    public String toString(){ return "Reading "+this.exp.toString() + " " + "varname";}

}
