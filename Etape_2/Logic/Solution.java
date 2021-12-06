package Logic;
import java.util.List;
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
    for(Cut cut : cuts){
      file_index++;
      filename="solution_"+file_index+".svg";
      boolean[] vlist = cut.isValid();
      boolean isvalid = vlist[0] && vlist[1] && vlist[2]; 
      // System.out.println("cut in solution x = "+cut.PositionX+"y = "+cut.PositionY);
      // System.out.println("cut in solution cut.supplierId: "+ cut.supplierId +" cut.numPanneau"+cut.numPanneau+" widthPanneau = "+ getWood(suppliers, cut.supplierId, cut.numPanneau).dimension.Longueur +" heightPanneau = "+ getWood(suppliers, cut.supplierId, cut.numPanneau).dimension.largeur);
      if(isvalid){
        Makesvg(getWood(clients, cut.clientId, cut.idPlanche), getWood(suppliers, cut.supplierId, cut.idPanneau), cut.PositionX, cut.PositionY, filename);
      }
      else{
        System.out.println("Svg file creation aborted.");
      }
    }
  }
  
  private void Makesvg(Wood planche, Wood panneau, double x, double y, String filename){
    String widthPanneau = panneau.dimension.largeur+"";
    String heightPanneau = panneau.dimension.Longueur+"";
    String widthPlanche = planche.dimension.largeur+"";
    String heightPlanche = planche.dimension.Longueur+"";
    String positionX = x+"";
    String positionY = y+"";
    // System.out.println("cut in Makesvg x = "+positionX+"y = "+positionY);
    // System.out.println("cut in Makesvg widthPanneau = "+ widthPanneau +" heightPanneau = "+ heightPanneau);
    try {
         FileWriter myWriter = new FileWriter(filename);

         myWriter.write("<svg width=\"");
         myWriter.write(widthPanneau);
         myWriter.write("\" height=\"");
         myWriter.write(heightPanneau);
         myWriter.write("\">");

         myWriter.write("<rect width=\"");
         myWriter.write(widthPanneau);
         myWriter.write("\" height=\"");
         myWriter.write(heightPanneau);
         myWriter.write("\" style=\"fill:rgb(255,255,255);stroke-width:3;stroke:rgb(0,0,0)\">");
         myWriter.write("</rect>");

         myWriter.write("<rect x=\"");
         myWriter.write(positionX);
         myWriter.write("\" y=\"");
         myWriter.write(positionY);
         myWriter.write("\" width=\"");
         myWriter.write(widthPlanche);
         myWriter.write("\" height=\"");
         myWriter.write(heightPlanche);
         myWriter.write("\" style=\"fill:rgb(0,0,0);stroke-width:3;stroke:rgb(0,0,0)\">");
         myWriter.write("</rect>");
         myWriter.write("</svg>");
         myWriter.close();
         System.out.println("Svg file created Successfully.");
       } catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
       }

  }
}
