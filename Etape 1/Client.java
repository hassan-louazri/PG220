import java.util.List;

public class Client extends User 
{
    List<Planche> listP;
    Client(int id, List<Planche> listPlanche)
    {
        super(id);
        this.listP = listPlanche;
    }
}
