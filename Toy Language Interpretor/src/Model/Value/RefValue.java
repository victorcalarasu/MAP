package Model.Value;

import Model.Type.IType;
import Model.Type.RefType;

public class RefValue implements IValue{
    private int adress;
    private IType locationType;

    public RefValue(int a, IType l)
    {
        this.adress=a;
        this.locationType=l;
    }

    public int getAdress(){return this.adress;}
    public IType getLocationType(){return this.locationType;}

    @Override
    public IType getType(){return new RefType(locationType); }

    public String toString(){return "("+ adress +","+ locationType.toString() + ")";}


}
