import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import javax.sql.rowset.spi.XmlReader;

public class Test {

    static List<Client> listClient;
    static List<Supplier> listSupplier;
    private static Cut cut;
    public static void main(String[] argvs) throws Exception {
        listClient=new ArrayList<>();
        listSupplier=new ArrayList<>();
        XMLReader.readXml("clients.xml", "client", "planche");
        XMLReader.readXml("fournisseurs.xml", "fournisseur", "panneau");

        for(int j=0;j<listClient.size();j++){
          System.out.print("\n\n=============Client n°:"+listClient.get(j).id+"=============\n");
          for (int i = 0; i< listClient.get(j).listW.size();i++){
              String demande = "\nRead from Main: Commande n°" + listClient.get(j).listW.get(i).id + " de " + listClient.get(j).listW.get(i).number + " Planches à livrer pour le " + listClient.get(j).listW.get(i).date + " au prix maximal de " +listClient.get(j).listW.get(i).price + " de Longueur " + listClient.get(j).listW.get(i).dimension.Longueur +" et de largueur " + listClient.get(j).listW.get(i).dimension.largeur + "\n";
              System.out.println(demande);
              //Price control
              System.out.println("Price is valid : " + listClient.get(j).isValid(listClient.get(j).listW.get(i)));
              //Dimension control
              System.out.println("Dimension is valid : " + listClient.get(j).listW.get(i).dimension.isValid(listClient.get(j).listW.get(i).dimension));
              //Date control
              System.out.println("Date is valid : " + listClient.get(j).listW.get(i).isValid(listClient.get(j).listW.get(i)));
          }
        }
        System.out.print("\n\n=================================================\n\n");

        for(int j=0;j<listSupplier.size();j++){
          System.out.print("\n\n=============Supplier n°:"+listSupplier.get(j).id+"=============\n");
          for (int i = 0; i< listSupplier.get(j).listW.size();i++){
              String supply = "\nRead from Main: Panneau n°" + listSupplier.get(j).listW.get(i).id + " de " + listSupplier.get(j).listW.get(i).number + " Panneaux à fournir pour le " + listSupplier.get(j).listW.get(i).date + " au prix minimal de " +listSupplier.get(j).listW.get(i).price + " de Longueur " + listSupplier.get(j).listW.get(i).dimension.Longueur +" et de largueur " + listSupplier.get(j).listW.get(i).dimension.largeur + "\n";
              System.out.println(supply);
              //Price control
              System.out.println("Price is valid : " + listSupplier.get(j).isValid(listSupplier.get(j).listW.get(i)));
              //Dimension control
              System.out.println("Dimension is valid : " + listSupplier.get(j).listW.get(i).dimension.isValid(listSupplier.get(j).listW.get(i).dimension));
              //Date control
              System.out.println("Date is valid : " + listSupplier.get(j).listW.get(i).isValid(listSupplier.get(j).listW.get(i)));
          }
       }
    }
}
