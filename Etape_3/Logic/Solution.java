package Logic;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
class Solution{
  private Wood getWood(List<? extends User> users,int userid,int woodid){
    Wood wood=null;
      for(User u : users){
          if(u.id==userid){
              for(Wood w : u.listW){
                  if(w.id==woodid)
                    wood=w;
              }
          }
      }
    return wood;
  }

  Solution(List<Client> clients, List<Supplier> suppliers, List<Cut> cuts){
    String filename;
    int file_index=0;
    List<List<Cut>> panneauCuts = new ArrayList<List<Cut>>();
    int refsup = cuts.get(0).supplierId;
    int refpan = cuts.get(0).idPanneau;
    int refnumpan = cuts.get(0).numPanneau;

    List<Cut> cutslist = new ArrayList<>();
    for (Cut cut : cuts){
      if((cut.numPanneau == refnumpan) && (cut.idPanneau == refpan) && (cut.supplierId == refsup)){
        cutslist.add(cut);
      }
      else{
        panneauCuts.add(cutslist);
        refnumpan = cut.numPanneau;
        refpan = cut.idPanneau;
        refsup = cut.supplierId;
        cutslist = new ArrayList<>();
        cutslist.add(cut);
      }
    }
    panneauCuts.add(cutslist);


    for(List<Cut> cutPanneau : panneauCuts){
      file_index++;
      filename="solution_"+file_index+".svg";
      Makesvg(clients, suppliers, cutPanneau, filename);
    }
    MakeXml(cuts);
  }
  private void MakeXml( List<Cut> cuts){
    try {
      FileWriter myWriter = new FileWriter("decoupes.xml");
      StringBuilder writeBuilder = new StringBuilder();
      writeBuilder.append("<decoupes>\n");
      for(Cut cut : cuts){
        writeBuilder.append("<decoupe>\n<client id=\""+cut.clientId +"\" planche=\""+cut.idPlanche +"."+cut.numPlanche+"\">\n");
        writeBuilder.append("<fournisseur id=\""+cut.supplierId +"\" panneau=\""+cut.idPanneau +"."+cut.numPanneau+"\">\n");
        writeBuilder.append("<position x=\""+cut.PositionX +"\" y=\""+cut.PositionY +"\">\n"+"</decoupe>\n");
      }
      writeBuilder.append("</decoupes>\n");
      String write = writeBuilder.toString();
      myWriter.write(write);
      myWriter.close();
      System.out.println("xml file created Successfully.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
  private void Makesvg(List<Client> clients, List<Supplier> suppliers, List<Cut> cutPanneau, String filename){
    Panneau panneau = (Panneau)getWood(suppliers, cutPanneau.get(0).supplierId, cutPanneau.get(0).idPanneau);
    String widthPanneau = panneau.dimension.largeur+"";
    String heightPanneau = panneau.dimension.Longueur+"";
    try {
         FileWriter myWriter = new FileWriter(filename);
         StringBuilder writeBuilder = new StringBuilder();

         writeBuilder.append("<svg width=\""+widthPanneau+"\" height=\""+heightPanneau+"\">"+"\n<rect width=\""+widthPanneau+"\" height=\""+heightPanneau);
         writeBuilder.append("\" style=\"fill:rgb(255,0,0);stroke-width:3;stroke:rgb(255,255,255)\">\n</rect>\n");
         for(Cut cut : cutPanneau){
           Planche planche = (Planche)getWood(clients, cut.clientId, cut.idPlanche);
           writeBuilder.append("<rect x=\""+cut.PositionX+"\" y=\""+cut.PositionY+"\" width=\""+planche.dimension.largeur+"\" height=\""+planche.dimension.Longueur);
           writeBuilder.append("\" style=\"fill:rgb(0,0,255);stroke-width:3;stroke:rgb(0,0,0)\">\n</rect>\n");
         }
         writeBuilder.append("</svg>");

         String write = writeBuilder.toString();

         myWriter.write(write);
         myWriter.close();
         System.out.println("Svg file created Successfully.");
       } catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
       }

  }
}
