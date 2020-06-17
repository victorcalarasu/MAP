package Model.ADT;

import Model.Exception.MyException;

public interface IStack<T> {
    T pop() throws MyException;
    void push(T value);
    boolean isEmpty();

    @Override
    String toString();
}