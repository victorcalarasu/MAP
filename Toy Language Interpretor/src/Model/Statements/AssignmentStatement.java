package Model.Statements;

import Model.ADT.IDictionary;
import Model.Exception.MyException;
import Model.Expression.IExpression;
import Model.ProgramState.*;
import Model.Value.IValue;

public class AssignmentStatement implements IStatement{
    String id;
    IExpression expression;

    public AssignmentStatement(String id, IExpression exp){
        this.id=id;
        this.expression=exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        IDictionary<String, IValue> table = state.getSymbolTable();
        if (table.isDefined(this.id)){
            IValue r=this.expression.evaluate(table,state.getHeap());
            if(r.getType().equals(table.lookup(this.id).getType())){
                table.update(this.id,r);
            }
            else
                throw new MyException("Expression and variable type not the same!");
        }
        else
            throw new MyException("Variable id does not exist!");
        return null;
    }

    @Override
    public String toString() {
        return (id + "="+expression.toString());
    }

}
