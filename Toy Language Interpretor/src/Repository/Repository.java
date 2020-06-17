package Repository;

import Model.Exception.MyException;
import Model.ProgramState.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private List<ProgramState> programStates;
    private int idx;
    private String logFilePath;
    private boolean first;

    public Repository(String file){
        this.programStates= new ArrayList<>();
        this.logFilePath=file;
        this.idx=0;
        this.first=true;
    }
    @Override
    public void logPrgStateExec(ProgramState p) throws IOException {
        PrintWriter writer;
        if(first)
        {
            writer=new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath,false)));
            first=false;
        }
        else
            writer= new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath,true)));
            writer.print(p);
            writer.close();
    }

    @Override
    public List<ProgramState> getPrgList() {
        return programStates;
    }

    @Override
    public void setPrgList(List<ProgramState> l) {
        this.programStates=l;
    }

    @Override
    public void add(ProgramState p){
        this.programStates.add(p);
    }


}
