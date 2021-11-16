import java.util.List;

public class Supplier extends User
{
    List<Panneau> listP;
    Supplier(int id, List<Panneau> listPanneau)
    {
       super(id);
        this.listP = listPanneau;

    }
    
}
