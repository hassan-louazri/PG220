import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.spi.XmlReader;

public class Test {

    static List<Client> listClient;
    static List<Supplier> listSupplier;
    public static void main(String[] argvs)
    {
        listClient=new ArrayList<>();
        listSupplier=new ArrayList<>();
     XMLReader.readXml("clients.xml","client","planche");
     XMLReader.readXml("fournisseurs.xml","fournisseur","panneau");

     for(int j=0;j<listClient.size();j++){
        System.out.println("-------------------------------------------Client n°:"+listClient.get(j).id);
        for (int i = 0; i< listClient.get(j).listP.size();i++){
            String commande = "Read from Main: Commande n°" + listClient.get(j).listP.get(i).id + " de " + listClient.get(j).listP.get(i).number + " Panneau à livrer pour le " + listClient.get(j).listP.get(i).date + " au prix maximal de " +listClient.get(j).listP.get(i).price;
            System.out.println(commande);
            System.out.println(listClient.get(j).isValid(listClient.get(j).listP.get(i)));
            System.out.println(listClient.get(j).listP.get(i).dimension.isValid(listClient.get(j).listP.get(i).dimension));
       
        }
     }
     System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
     
     for(int j=0;j<listSupplier.size();j++){
        System.out.println("-------------------------------------------Supplier n°:"+listSupplier.get(j).id);
        for (int i = 0; i< listSupplier.get(j).listP.size();i++){
            String commande = "Read from Main: Commande n°" + listSupplier.get(j).listP.get(i).id + " de " + listSupplier.get(j).listP.get(i).number + " Panneau à livrer pour le " + listSupplier.get(j).listP.get(i).date + " au prix maximal de " +listSupplier.get(j).listP.get(i).price;
            System.out.println(commande);
            System.out.println(listSupplier.get(j).isValid(listSupplier.get(j).listP.get(i)));
            System.out.println(listSupplier.get(j).listP.get(i).dimension.isValid(listSupplier.get(j).listP.get(i).dimension));
       
        }
     }
    
    }
    
    

}

