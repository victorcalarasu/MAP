package Model.Type;
import Model.Value.*;

public class BoolType implements IType{

    public BoolValue defaultValue(){return new BoolValue(false);}
    public String toString() {return "bool ";}

    public boolean equals(Object x) {
        if (x instanceof BoolType)
            return true;
        else
            return false;
    }
}
