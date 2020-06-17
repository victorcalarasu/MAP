package Model.ProgramState;

import Model.ADT.*;
import Model.Exception.MyException;
import Model.Statements.IStatement;
import Model.Value.IValue;
import Model.Value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ProgramState {
    private IStack<IStatement> executionStack;
    private IDictionary<String, IValue> symbolTable;
    private IList<IValue> output;
    private IStatement originalProgram;
    private IDictionary<StringValue, BufferedReader> fileTable;
    private IHeap<IValue> heap;
    private int id;
    private static int globalID = 1;

    public ProgramState(IStatement originalProgram) {
        this.executionStack = new MyStack<>();
        this.symbolTable = new MyDictionary<>();
        this.output = new MyList<>();
        this.fileTable = new MyDictionary<>();
        this.heap = new MyHeap<>();
        this.executionStack.push(originalProgram);
        id = 1;
    }

    public ProgramState(IStack<IStatement> es, IDictionary<String, IValue> st, IList<IValue> o, IStatement op, IDictionary<StringValue, BufferedReader> ft, IHeap<IValue> h) {
        this.executionStack = es;
        this.symbolTable = st;
        this.output = o;
        this.originalProgram = op;
        this.fileTable = ft;
        this.heap = h;
        this.executionStack.push(originalProgram);
        id = getGlobalID();

    }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(IStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public IDictionary<String, IValue> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(IDictionary<String, IValue> table) {
        this.symbolTable = table;
    }

    public IList<IValue> getOutput() {
        return this.output;
    }

    void setOutput(IList<IValue> o) {
        this.output = o;
    }

    public IDictionary<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public IHeap<IValue> getHeap() {
        return this.heap;
    }

    public void setHeap(IHeap<IValue> heap) {
        this.heap = heap;
    }

    @Override
    public String toString() {
        return "ProgramState:{" + "ID: " + this.id + "\n" +
                "executionStack= " + executionStack.toString() + "\n" + "symbolTable= " + symbolTable.toString() + "\n" + "heap = " + heap.toString() + "\n" + "output= " + output.toString() + '}'+ "\n";
    }

    public boolean isNotCompleted() {
        return !this.executionStack.isEmpty();
    }

    public ProgramState executeOneStep() throws MyException, IOException {
        if (executionStack.isEmpty())
            throw new MyException("Stack is empty!");
        IStatement current = executionStack.pop();
        return current.execute(this);

    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public synchronized static int getGlobalID(){
        globalID*=10;
        return globalID;
    }


}
