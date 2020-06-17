package Model.Statements;

import Model.ADT.IDictionary;
import Model.Exception.MyException;
import Model.ProgramState.ProgramState;
import Model.Type.IType;
import Model.Value.IValue;

public class VariableDeclarationStatement implements IStatement{
    String name;
    IType type;

    public VariableDeclarationStatement(String n, IType t){
        this.name=n;
        this.type=t;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IDictionary<String, IValue> table=state.getSymbolTable();
        if (table.isDefined(this.name))
            throw new MyException("Variable already exists!");
        else
            table.update(this.name,this.type.defaultValue());
        return null;
    }

    @Override
    public String toString(){return  this.type.toString()+ this.name;}
}
