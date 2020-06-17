package Model.Type;
import Model.Value.*;

public class IntType implements IType{

    public IntValue defaultValue() {return new IntValue(0);}
    public String toString() {return "int ";}
    public boolean equals(Object x){
        if (x instanceof IntType)
            return true;
        else
            return false;

    }
}
