package Logic;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math.*;
class algo2 {


    algo2(){
        //generate();
    }
    public List<Cut> generate( List<Client> clients,List<Supplier> suppliers ){

        List<Cut> cuts = new ArrayList<>();

        //List<Supplier> tmp = suppliers;
        List<Client> ClientsOrdonne=new ArrayList<>();
        Client refc=clients.get(0);
        Planche refw=(Planche)refc.listW.get(0);
        List<String> IPused=new ArrayList<>();
        int nombreplanche=0;
        for(Client c: clients){
            nombreplanche+=c.listW.size();
        }
        while(ClientsOrdonne.size()!=nombreplanche){

            for(Client c : clients ){
                for(Wood w: c.listW){
                    if(w.dimension.largeur > refw.dimension.largeur ){
                        Boolean b=IPused.contains(c.id+"."+w.id);
                        if(!b){
                            refw=new Planche(w.id, w.number,w.date, w.price, w.dimension) ;
                            refc=c;
                        }
                    }
                    if(w.dimension.largeur == refw.dimension.largeur ){
                        if(w.dimension.Longueur > refw.dimension.Longueur ){
                            Boolean b=IPused.contains(c.id+"."+w.id);
                        if(!b){
                            refw=new Planche(w.id, w.number,w.date, w.price, w.dimension) ;
                            refc=c;
                        }
                        }
                    }
                }
            }
            List<Planche> w=new ArrayList<>();
            w.add((Planche)refw);
            Client ref=new Client(refc.id, w);
            IPused.add(refc.id+"."+refw.id);
            Boolean br=false;


            ClientsOrdonne.add(ref);
            for(Client cc : clients ){
                for(Wood ww: cc.listW){
                    if(!(IPused.contains(cc.id+"."+ww.id))){
                        refw=new Planche(ww.id, ww.number,ww.date, ww.price, ww.dimension) ;
                        refc=cc;
                        br=true;
                        break;
                    }
                }
                if(br)
                    break;
            }
        }
        // System.out.println(ClientsOrdonne.size());
        // for(Client c : ClientsOrdonne){
        //     for (Wood w :c.listW)
        //     System.out.println(w.id);
        // }
        for (int i = 0; i < ClientsOrdonne.size(); i++) {
           // List<String> cutList = new ArrayList<>();
            for (int j = 0; j < ClientsOrdonne.get(i).listW.size(); j++) {
                Wood w = ClientsOrdonne.get(i).listW.get(j);
                for (int k = w.number; k >0 ; k--) {
                    String[] cutTab = FindSupplier(ClientsOrdonne.get(i).id, w.id, k,w.dimension,w.date, suppliers);
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
int nextOrdonnée=0;
int nextabscisse=0;
    String[] FindSupplier(int clientId, int PlankId, int PlankNumber, Dimension dim, String date, List<Supplier> suppliers){
        String[] cutList = {"-1", "-1", "-1", "-1", "-1", "-1","-1","-1"};
        cutList[0] = clientId+"";
        cutList[1] =  PlankId+"";
        cutList[2]  = PlankNumber+"";
        for (int i = 0; i < suppliers.size(); i++) { //Parcours des fournisseurs
            Supplier s = suppliers.get(i);
            int j=0;
            while (j < s.listW.size()) { //Parcours des panneaux pour un seul fournisseur
                Panneau p = (Panneau)s.listW.get(j);

                if(p.dimension.Longueur >= dim.Longueur+nextOrdonnée && p.dimension.largeur >= dim.largeur+nextabscisse){
                    int id = s.id;
                    int idp = p.id;
                    int pnumber = p.number;
                    cutList[3] = id+"";
                    cutList[4] = idp+"";
                    cutList[5] = pnumber+"";
                    cutList[6] = nextabscisse+"";
                    cutList[7] = nextOrdonnée+"";
                    nextabscisse+=dim.largeur;
                    if( p.dimension.largeur < dim.largeur+nextabscisse)
                      nextOrdonnée+=dim.Longueur;
            } else{
                    if(p.dimension.Longueur < dim.Longueur+nextOrdonnée){
                        nextOrdonnée=0;
                        nextabscisse=0;
                        p.setnumber(p.number-1);
                    }else if( p.dimension.largeur < dim.largeur+nextabscisse){
                          //System.out.println("Java zamle-----"+dim.Longueur);
                          nextabscisse=0;
                          //nextOrdonnée += dim.Longueur;
                    }
                    if(p.getnumber()==0)
                      j++;
                    if(p.dimension.Longueur < dim.Longueur || p.dimension.largeur < dim.largeur){
                      j++;
                      nextOrdonnée=0;
                      nextabscisse=0;
                    }
                     continue;
                  }

                  break;

              }
              if(s.listW.size() == 0) suppliers.remove(i);
              if(cutList[4]!="-1")
                  break;
        }
        return cutList;
    }
    Cut cutInfo(List<String> cutData){
        int x = 0;
        int y = 0;
        int clientId =(int)controleData(cutData.get(0),"Integer");
        int supplierId = (int)controleData(cutData.get(3),"Integer");
         x = Integer.parseInt(cutData.get(6));
         y = Integer.parseInt(cutData.get(7));
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
