package Model.ADT;

import Model.Exception.MyException;

import java.util.HashMap;
import java.util.Map;

public interface IDictionary<K, V> {

    public V lookup(K key) throws MyException;
    boolean isDefined(K key);
    void update(K key, V value);
    void remove(K key);
    public Map getContent();
    public IDictionary<K,V> copy();

    @Override
    String toString();
}