package Model.Value;
import Model.Type.*;

public class IntValue implements IValue{
    private int v;

    public IntValue(int val){this.v=val;}
    public int getValue(){return this.v;}
    public IType getType(){return new IntType();}
    public String toString(){return String.valueOf(v);}

    public boolean isEqual(IntValue second){
        if (this.v==second.getValue())
            return true;
        else
            return false;
    }

}
