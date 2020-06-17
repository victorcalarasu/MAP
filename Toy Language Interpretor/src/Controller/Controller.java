package Controller;

import Model.ADT.IStack;
import Model.Exception.MyException;
import Model.ProgramState.ProgramState;
import Model.Statements.IStatement;
import Model.Value.IValue;
import Model.Value.RefValue;
import Repository.IRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private IRepository repository;
    private String programState;
    private ExecutorService executor;

    public Controller(IRepository r) {
        this.repository = r;
    }

    public IRepository getRepository() {
        return this.repository;
    }

    public void addProgram(ProgramState p) {
        this.repository.add(p);
    }

    Map<Integer, IValue> safeGarbageCollector(List<Integer> addressesFromSymbolTable, Map<Integer, IValue> heap) {
        return heap.entrySet()
                .stream()
                .filter(e -> addressesFromSymbolTable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues, Collection<IValue> heap) {
        return Stream.concat(
                heap.stream()
                        .filter(v -> v instanceof RefValue)
                        .map(v -> {
                            RefValue v1 = (RefValue) v;
                            return v1.getAdress();
                        }),
                symTableValues.stream()
                        .filter(v -> v instanceof RefValue)
                        .map(v -> {
                            RefValue v1 = (RefValue) v;
                            return v1.getAdress();
                        })
        )
                .collect(Collectors.toList());
    }

    public List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList) {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrg(List<ProgramState> programStates) throws InterruptedException, MyException {
        programStates.forEach(p-> {
            try {
                repository.logPrgStateExec(p);
            } catch (IOException e) {

            }
        });
        List<Callable<ProgramState>> callableList = programStates.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(()-> p.executeOneStep()))
                .collect(Collectors.toList());
        List<ProgramState> newProgramStates = executor.invokeAll(callableList)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(e -> e != null)
                .collect(Collectors.toList());


        programStates.addAll(newProgramStates);
        programStates.forEach(prog -> {
            try {
                repository.logPrgStateExec(prog);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        repository.setPrgList(programStates);
    }

    public void executeAllStep() throws InterruptedException, MyException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStates = removeCompletedPrg(repository.getPrgList());
        while(programStates.size()>0){
            callGarbageCollector(programStates);
            oneStepForAllPrg(programStates);
            programStates = removeCompletedPrg(repository.getPrgList());
        }
        executor.shutdownNow();
        programStates = removeCompletedPrg(repository.getPrgList());
        repository.setPrgList(programStates);
    }

    public void callGarbageCollector(List<ProgramState> programStates){
        programStates.forEach(
                p-> {p.getHeap().setContent(safeGarbageCollector(getAddrFromSymTable(p.getSymbolTable().getContent().values(),p.getHeap().getContent().values()),p.getHeap().getContent()));}
        );
    }

}
