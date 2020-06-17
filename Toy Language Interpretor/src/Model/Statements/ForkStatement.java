package Model.Statements;

import Model.ADT.MyDictionary;
import Model.ADT.MyStack;
import Model.Exception.MyException;
import Model.ProgramState.ProgramState;
import Model.Value.IValue;

import java.io.IOException;

public class ForkStatement implements IStatement{
    private IStatement stmt;

    public ForkStatement(IStatement s)
    {
        this.stmt=s;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        return new ProgramState(new MyStack<>(), state.getSymbolTable().copy(),state.getOutput(),stmt,state.getFileTable(),state.getHeap());
    }

    @Override
    public String toString(){return "fork("+this.stmt.toString()+")";}
}
