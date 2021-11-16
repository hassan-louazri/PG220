import java.util.List;

public class Supplier implements IValidate
{
    int id;
    List<Panneau> listP;
    Supplier(int id, List<Panneau> listPanneau)
    {
        this.id = id;
        this.listP = listPanneau;
        System.out.println("Read from SupplierClass: Supplier n°"+id);
        for (int i = 0; i< this.listP.size();i++){
            String commande = "Read from SupplierClass: Commande n°" + this.listP.get(i).id + " de " + this.listP.get(i).number + " Panneau à livrer pour le " + this.listP.get(i).date + " au prix maximal de " +this.listP.get(i).price;
            System.out.println(commande);
            System.out.println(isValid(this.listP.get(i)));
            System.out.println(this.listP.get(i).dimension.isValid(this.listP.get(i).dimension));
       
        }

    }
    @Override
    public boolean isValid(Object o){
            if(((Panneau)o).price<=0){
                System.out.print("price >=0:?");
                 return false;
             }
             System.out.print("price >=0:?");
        return true;
    }
}
