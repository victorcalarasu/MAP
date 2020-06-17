package Model.Type;

import Model.Value.IValue;
import Model.Value.RefValue;

public class RefType implements IType {
    private IType inner;

    public RefType(IType i)
    {
        this.inner=i;
    }

    public IType getInner(){
        return this.inner;
    }

    public boolean equals(Object second){
        if (second instanceof RefType)
            return inner.equals(((RefType) second).getInner());
        return false;

    }

    @Override
    public String toString(){ return "Ref( "+this.inner.toString() + " )";}

    @Override
    public IValue defaultValue(){return new RefValue(0,this.inner);}
}
