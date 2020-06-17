package Repository;

import Model.Exception.MyException;
import Model.ProgramState.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    public void add(ProgramState state);
    public void logPrgStateExec(ProgramState p) throws IOException;
    public List<ProgramState> getPrgList();
    public void setPrgList(List<ProgramState> l);
}
