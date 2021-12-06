package Logic;
import Reader.*;
import java.util.ArrayList;
import java.util.List;

class Main {
   private static List<Client> listClient;
   private static List<Supplier> listSupplier;
   private static List<Cut> cuts;

   public static void main(String[] argvs) throws Exception {
      
      listClient = new ArrayList<>();
      listSupplier = new ArrayList<>();
      cuts = new ArrayList<>();
      
      IRead r = new XMLReader();
      
      r.readData("clients.xml");
      new ClientFactory(IRead.data);
      
      r.readData("fournisseurs.xml");
      new SupplierFactory(IRead.data);
      
      r.readData("decoupes.xml");
      new CutFactory(IRead.data);

      for(int j=0;j<listClient.size();j++){
         System.out.print("\n\n=============Client n°:"+listClient.get(j).id+"=============\n");
         for (int i = 0; i< listClient.get(j).listW.size();i++){
            String demande = "\nRead from Main: Commande n°" + listClient.get(j).listW.get(i).id + " de " + listClient.get(j).listW.get(i).number + " Planches à livrer pour le " + listClient.get(j).listW.get(i).date + " au prix maximal de " +listClient.get(j).listW.get(i).price + " de Longueur " + listClient.get(j).listW.get(i).dimension.Longueur +" et de largueur " + listClient.get(j).listW.get(i).dimension.largeur + "\n";
            System.out.println(demande);
            boolean[] ClientIsValid = listClient.get(j).listW.get(i).isValid();
            //Price control
            System.out.println("Price is valid : " + ClientIsValid[0]);
            //Dimension control
            System.out.println("Dimension is valid : " + ClientIsValid[1]);
            //Date control
            System.out.println("Date is valid : " + ClientIsValid[2]);
         }
      }
      System.out.print("\n\n=================================================\n\n");
     
      for(int j=0;j<listSupplier.size();j++){
         System.out.print("\n\n=============Supplier n°:"+listSupplier.get(j).id+"=============\n");
         for (int i = 0; i< listSupplier.get(j).listW.size();i++){
            String supply = "\nRead from Main: Panneau n°" + listSupplier.get(j).listW.get(i).id + " de " + listSupplier.get(j).listW.get(i).number + " Panneaux à fournir pour le " + listSupplier.get(j).listW.get(i).date + " au prix minimal de " +listSupplier.get(j).listW.get(i).price + " de Longueur " + listSupplier.get(j).listW.get(i).dimension.Longueur +" et de largueur " + listSupplier.get(j).listW.get(i).dimension.largeur + "\n";
            System.out.println(supply);
            boolean[] SupplierIsValid = listSupplier.get(j).listW.get(i).isValid();
            //Price control
            System.out.println("Price is valid : " + SupplierIsValid[0]);
            //Dimension control
            System.out.println("Dimension is valid : " + SupplierIsValid[1]);
            //Date control
            System.out.println("Date is valid : " + SupplierIsValid[2]);
         }
      }
      System.out.print("\n\n=================================================\n\n");
     
      for (int j=0;j<cuts.size() ;j++ ) {
         String jobDone = "\nDécoupe de la Planche n°" + cuts.get(j).idPlanche + " pour le client n°" + cuts.get(j).clientId + " du panneau n°"+ cuts.get(j).idPanneau + " du fournisseur n°" + cuts.get(j).supplierId + " dont la position du départ est (x, y) = (" + cuts.get(j).PositionX +", "+ cuts.get(j).PositionY + ").\n";
         System.out.println(jobDone);
         boolean[] CutIsValid = cuts.get(j).isValid();
         //Positions Control
         System.out.println("Positions are valid : " + CutIsValid[0]);       
         //Existing Plank/Pannel Control
         System.out.println("Pannel and Plank exist : " + CutIsValid[1]);
         //Dimensions Control
         System.out.println("Dimensions are valid : " + CutIsValid[2]);
      }

      System.out.println("=== SVG files:");
      new Solution(listClient, listSupplier, cuts);
      System.out.print("\n=================================================\n\n");
   }

   static List<Client> getClientsList(){
      return listClient;
   }
   static void addClientToList(Client newClient){
      listClient.add(newClient);
   }

   static List<Supplier> getSuppliersList(){
      return listSupplier;
   }
   static void addSupplierToList(Supplier newSupplier){
      listSupplier.add(newSupplier);
   }

   static List<Cut> getCutsList(){
      return cuts;
   }
   static void addCutToList(Cut newCut){
      cuts.add(newCut);
   }
}
