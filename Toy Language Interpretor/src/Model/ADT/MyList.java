package Model.ADT;

import Model.Exception.MyException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements IList{
    private List<T> list;

    public MyList() {
        this.list = new ArrayList<T>();
    }

    @Override
    public void add(Object item) {
        this.list.add((T) item);
    }

    @Override
    public void remove(Object item) throws MyException {
        if (!this.list.contains((T)item))
            throw new MyException("Object doesn't exist!");
        this.list.remove((T)item);
    }

    @Override
    public String toString() {
        String s = "{";

        for (T i:this.list) {
            s+= i.toString()+" ";
        }
        s+="}\n";
        return s;
    }

    @Override
    public Object get(int index) throws MyException {
        if (index<0 || index>=list.size())
            throw new MyException("Invalid index!");
        return this.list.get(index);
    }

    @Override
    public int size() {
        return this.list.size();
    }
}