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
      if(isvalid){
        Makesvg(getWood(clients, cut.clientId, cut.idPlanche), getWood(suppliers, cut.supplierId, cut.idPanneau), cut.PositionX, cut.PositionY, filename);
      }
      else{
        System.out.println("Svg file creation aborted.");
      }
    }
    MakeXml(cuts);
  }
  private void MakeXml( List<Cut> cuts){
    try {
    FileWriter myWriter = new FileWriter("decoupez.xml");
    StringBuilder writeBuilder = new StringBuilder();
    writeBuilder.append("<decoupez>\n");
    for(Cut cut : cuts){
      writeBuilder.append("<decoupe>\n<client id=\""+cut.clientId +"\" planche=\""+cut.idPlanche +"."+cut.numPlanche+"\">\n");
      writeBuilder.append("<fournisseur id=\""+cut.supplierId +"\" panneau=\""+cut.idPanneau +"."+cut.numPanneau+"\">\n");
      writeBuilder.append("<position x=\""+cut.PositionX +"\" y=\""+cut.PositionY +"\">\n"+"</decoupe>\n");
    }
    writeBuilder.append("</decoupez>\n");
    String write = writeBuilder.toString();
    myWriter.write(write);
    myWriter.close();
    System.out.println("xml file created Successfully.");
  } catch (IOException e) {
    System.out.println("An error occurred.");
    e.printStackTrace();
  }
  }
  private void Makesvg(Wood planche, Wood panneau, double x, double y, String filename){
    String widthPanneau = panneau.dimension.largeur+"";
    String heightPanneau = panneau.dimension.Longueur+"";
    String widthPlanche = planche.dimension.largeur+"";
    String heightPlanche = planche.dimension.Longueur+"";
    String positionX = x+"";
    String positionY = y+"";
    try {
         FileWriter myWriter = new FileWriter(filename);
         StringBuilder writeBuilder = new StringBuilder();

         writeBuilder.append("<svg width=\""+widthPanneau+"\" height=\""+heightPanneau+"\">"+"\n<rect width=\""+widthPanneau+"\" height=\""+heightPanneau);
         writeBuilder.append("\" style=\"fill:rgb(255,255,255);stroke-width:3;stroke:rgb(255,255,255)\">\n </rect>");
         writeBuilder.append("<rect x=\""+positionX+"\" y=\""+positionY+"\" width=\""+widthPlanche+"\" height=\""+heightPlanche);
         writeBuilder.append("\" style=\"fill:rgb(0,0,255);stroke-width:3;stroke:rgb(0,0,0)\">\n</rect>\n</svg>");

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
