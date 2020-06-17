package Model.Value;
import Model.Type.*;
public class BoolValue implements IValue{
    private boolean b;

    public BoolValue(boolean val){this.b=val;}
    public boolean getValue(){return b;}
    public String toString(){return String.valueOf(b);}
    public IType getType(){return new BoolType();}
}
