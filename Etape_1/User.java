import java.util.List;

public abstract class User implements IValidate{
    int id;
    User(int id)
    {
        this.id=id;
    }
    @Override
    public boolean isValid(Object o){
        return ((Wood)o).price >= 0;
    }
}
