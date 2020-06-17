package Model.Value;

import Model.Type.IType;
import Model.Type.StringType;

public class StringValue implements IValue{
    private String s;

    public StringValue(String x){this.s=x;}
    public String getValue(){return this.s;}
    public IType getType(){return new StringType();}
    public String toString(){return s;}
}
