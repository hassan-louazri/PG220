import java.util.List;

public abstract class User implements IValidate{
    int id;
    User(int id)
    {
        this.id=id;
  
    }
    @Override
    public boolean isValid(Object o){
            if(((Wood)o).price<=0){
                System.out.print("price >=0:?");
                 return false;
             }
              System.out.print("price >=0:?");
        return true;
    }
}