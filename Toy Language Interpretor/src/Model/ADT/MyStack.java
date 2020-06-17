package Model.ADT;

import Model.Exception.MyException;

import java.util.Stack;

public class MyStack<T> implements IStack {
    private Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<T>();
    }

    @Override
    public Object pop() throws MyException {
        return stack.pop();
    }

    @Override
    public String toString() {
        String result = "{";
        for (T el:this.stack) {
            result += el.toString()+"|";
        }
        result+="}";
        return result.toString();
    }

    @Override
    public void push(Object value) {
        stack.push((T) value);
    }

    @Override
    public boolean isEmpty() {
        return stack.empty();
    }
}