package Model.ADT;

import Model.Exception.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K,V> implements IDictionary<K,V> {
    private HashMap<K,V> dictionary;

    public MyDictionary(){
        dictionary = new HashMap<K,V>();
    }

    @Override
    public V lookup(K key) throws MyException {
        if (!dictionary.containsKey(key))
            throw new MyException("Key doesn't exist.");
        return dictionary.get(key);
    }

    @Override
    public String toString() {
        String result ="{";
        for (K key : dictionary.keySet())
            result+= key.toString() + " -> " + dictionary.get(key).toString() + ";";
        result+="}";
        return result;
    }

    @Override
    public boolean isDefined(Object key) {
        return dictionary.containsKey((K) key);
    }

    @Override
    public void remove(Object key){
        this.dictionary.remove(key);
    }
    @Override
    public void update(Object key, Object value) {
        dictionary.put((K) key, (V) value);
    }

    @Override
    public Map getContent(){return this.dictionary;}

    @Override
    public IDictionary<K,V> copy() {
        IDictionary<K,V> copy = new MyDictionary<>();
        for (K k : dictionary.keySet()) {
            copy.update(k,dictionary.get(k));
        }
        return copy;
    }
}