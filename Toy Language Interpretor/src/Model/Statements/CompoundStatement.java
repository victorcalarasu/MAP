package Model.Statements;

import Model.ADT.IStack;
import Model.ProgramState.ProgramState;

public class CompoundStatement implements IStatement{
    IStatement st1,st2;

    public CompoundStatement(IStatement first, IStatement second){
        this.st1=first;
        this.st2=second;
    }

    public IStatement getFirst(){return this.st1;}
    public IStatement getSecond(){return this.st2;}

    @Override
    public ProgramState execute(ProgramState state){
        IStack<IStatement> s=state.getExecutionStack();
        s.push(st2);
        s.push(st1);
        return null;
    }
    @Override
    public String toString(){return "( " + this.st1.toString() + ";" + this.st2.toString() + " )";}
}
