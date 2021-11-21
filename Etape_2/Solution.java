import java.util.List;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
class Solution{

  static void solution(List<Client> clients, List<Supplier> suppliers,List<Cut> cuts){
    String filename;
    int file_index=0;
    for(Cut cut : cuts){
      file_index++;
      filename="solution_"+file_index+".svg";
      Makesvg(getWood(clients,cut.clientid,cut.numplanche),getWood(suppliers,cut.supplierid,cut.numpanneau),filename);
    }
  }
  private static void Makesvg(Wood planche, Wood panneau,String filename ){
    String widthPanneau=panneau.dimension.largeur+"";
    String heightPanneau=panneau.dimension.Longueur+"";
    String widthPlanche=planche.dimension.largeur+"";
    String heightPlanche=planche.dimension.Longueur+"";
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
         myWriter.write("<rect width=\"");
         myWriter.write(widthPlanche);
         myWriter.write("\" height=\"");
         myWriter.write(heightPlanche);
         myWriter.write("\" style=\"fill:rgb(0,0,255);stroke-width:3;stroke:rgb(0,0,0)\">");
         myWriter.write("</rect>");
          myWriter.write("</svg>");
         myWriter.close();
         System.out.println("The Svg file created Successfully.");
       } catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
       }

  }
  private static Wood getWood(List<? extends User> users,int userid,int woodid){
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
}
