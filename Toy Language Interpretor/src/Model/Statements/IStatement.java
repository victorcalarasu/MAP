package Model.Statements;

import Model.Exception.*;
import Model.ProgramState.ProgramState;

import java.io.IOException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException, IOException;
    @Override
    String toString();
}
