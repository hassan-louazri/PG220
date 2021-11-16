import java.util.List;

public class Client implements IValidate
{
    int id;
    List<Planche> listP;
    Client(int id, List<Planche> listPlanche)
    {
        this.id = id;
        this.listP = listPlanche;
        System.out.println("Read from ClientClass: Client n°"+id);
        for (int i = 0; i< this.listP.size();i++){
            String commande = "Read from ClientClass: Commande n°" + this.listP.get(i).id + " de " + this.listP.get(i).number + " planches à livrer pour le " + this.listP.get(i).date + " au prix maximal de " +this.listP.get(i).price;
            System.out.println(commande);
            System.out.println(isValid(this.listP.get(i)));
            System.out.println(this.listP.get(i).dimension.isValid(this.listP.get(i).dimension));
       
        }

    }
    @Override
    public boolean isValid(Object o){
            if(((Planche)o).price<=0){
                System.out.print("price >=0:?");
                 return false;
             }
                System.out.print("price >=0:?");
        return true;
    }
}
