package Model.Statements;

import Model.ADT.IDictionary;
import Model.ADT.MyDictionary;
import Model.Exception.MyException;
import Model.Expression.IExpression;
import Model.ProgramState.ProgramState;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.IValue;

import java.io.IOException;

public class WhileStatement implements IStatement{
    private IExpression exp;
    private IStatement statement;

    public WhileStatement(IExpression e,IStatement s) {
        this.exp = e;
        this.statement = s;
    }

    @Override
    public String toString(){return "(while( " + exp.toString() + " )" + statement.toString();}
    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        IDictionary<String, IValue> symbolTable = state.getSymbolTable();
        IValue result = exp.evaluate(symbolTable, state.getHeap());
        if(result.getType().equals(new BoolType())){
            BoolValue dResult = (BoolValue)result;
            if(dResult.getValue()){
                state.getExecutionStack().push(new WhileStatement(exp,statement));
                state.getExecutionStack().push(statement);
            }
        }
        else throw new MyException("Not boolean condition");
        return null;
    }
}
