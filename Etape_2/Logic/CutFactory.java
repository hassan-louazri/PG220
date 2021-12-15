package Logic;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math.*;
class CutFactory {
  

    CutFactory(){
        //generate();
    }
    public List<Cut> generate( List<Client> clients,List<Supplier> suppliers ){

        List<Cut> cuts = new ArrayList<>();
        //List<Supplier> tmp = suppliers;
        
        for (int i = 0; i < clients.size(); i++) {
            List<String> cutList = new ArrayList<>();
            for (int j = 0; j < clients.get(i).listW.size(); j++) {
                Wood w = clients.get(i).listW.get(j);
                for (int k = w.number; k >0 ; k--) {
                    String[] cutTab = FindSupplier(clients.get(i).id, w.id, k,w.dimension,w.date, suppliers);
                    List <String> cutData = new ArrayList<>();

                    for (int t = 0; t < cutTab.length; t++) {
                        cutData.add(cutTab[t]);
                    }
                    
                    cuts.add(cutInfo(cutData));    
                }
                
            }
        }
        // for (int index = 0; index < data.size(); index++) {
        //     if(data.get(index) == "decoupe"){
        //         List<String> cutData = new ArrayList<>();
        //         int i = index + 2;
        //         while (i < data.size() && data.get(i) != "decoupe") {
        //             cutData.add(data.get(i));
        //             i++;
        //         }
        //         Cut c = cutInfo(cutData);
        //         Main.addCutToList(c);
        //     }
        // }
        return cuts;
    }

    String[] FindSupplier(int clientId, int PlankId, int PlankNumber, Dimension dim, String date, List<Supplier> suppliers){
        String[] cutList = {"-1", "-1", "-1", "-1", "-1", "-1"};
        cutList[0] = clientId+"";
        cutList[1] =  PlankId+"";
        cutList[2]  = PlankNumber+"";
        for (int i = 0; i < suppliers.size(); i++) { //Parcours des fournisseurs
            Supplier s = suppliers.get(i);
            for (int j = 0; j < s.listW.size(); j++) { //Parcours des panneaux pour un seul fournisseur
                Panneau p = (Panneau)s.listW.get(j); 
                if(p.dimension.Longueur >= dim.Longueur && p.dimension.largeur >= dim.largeur){
                    int id = s.id;
                    int idp = p.id;
                    int pnumber = p.number;
                    cutList[3] = id+"";
                    cutList[4] = idp+"";
                    cutList[5] = pnumber+"";
                    if(p.number > 0) p.setnumber(p.number-1);   
                
                if(p.number == 0){
                    s.listW.remove(j);
                }
            } else{ 
                continue;
            }
                break; 
                
            }
            if(s.listW.size() == 0) suppliers.remove(i); 
            if(cutList[4]!="-1")
                break;
        }   
        //System.out.println("cutlist size = "+ cutList.length);
        return cutList;
    }    
    Cut cutInfo(List<String> cutData){
        int x = 0;
        int y = 0;
        int clientId =(int)controleData(cutData.get(0),"Integer");
        int supplierId = (int)controleData(cutData.get(3),"Integer");
        //int x = Integer.parseInt(cutData.get(6));
        //int y = Integer.parseInt(cutData.get(7));
        //String plank = cutData.get(1);
        // int plankId = Integer.parseInt(plank.substring(0, plank.indexOf(".")));
        // int numPlank = Integer.parseInt(plank.substring(1 + plank.indexOf(".")));
        int plankId = (int)controleData(cutData.get(1), "Integer");
        int pannelId = (int)controleData(cutData.get(4), "Integer");
        // String pannel = cutData.get(4);
        // int pannelId = Integer.parseInt(pannel.substring(0, pannel.indexOf(".")));
        // int numPannel = Integer.parseInt(pannel.substring(1 + pannel.indexOf(".")));
        int numPlank = (int)controleData(cutData.get(2), "Integer");
        int numPannel = (int)controleData(cutData.get(5), "Integer");
        return new Cut(x, y, clientId, supplierId, plankId, pannelId, numPlank, numPannel);
    }

    static Object controleData(String data, String type){
        Object r=0;
        if(type=="Double"){
            try {
                    r=Double.parseDouble(data);
            } catch (Exception e) {
              System.out.println("problem in xml file>>");
                System.exit(1);
    
            }
            return (double)r;
        }else  if(type=="Integer"){
            try {
                    r=Integer.parseInt(data);
            } catch (Exception e) {
              System.out.println("problem in xml file");
                   System.exit(1);
    
            }
            return (int)r;
        }else
            return null;
    }
}
