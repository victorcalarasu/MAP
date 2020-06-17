package Model.Type;

import Model.Value.IValue;
import Model.Value.StringValue;

public class StringType implements IType{

    public StringValue defaultValue(){return new StringValue("");}
    public boolean equals(Object second){
        if (second instanceof StringType)
            return true;
        else
            return false;
    }
    @Override
    public String toString(){return "string ";}

}
