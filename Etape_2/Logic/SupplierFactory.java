package Logic;
import java.util.List;
import java.util.ArrayList;

class SupplierFactory {

  SupplierFactory(){
   // generate(data);
  }
  public void generate(List<String> data) {
    for(int index=0; index<data.size();index++){
        if(data.get(index)=="fournisseur"){ 
          
          List<String> clientData = new ArrayList<>();
          int i = index + 1;
          while(i<data.size()&&data.get(i) != "fournisseur"){
            clientData.add(data.get(i));
            i++;
          }
          Supplier c = userInfo(clientData);
          Main.addSupplierToList(c);
        }
    }
  }

  Supplier userInfo(List<String> userData){
    List<Panneau> wList = new ArrayList<>();
    int id = (int)controle_data(userData.get(0),"Integer");
    for (int index = 0; index < userData.size() - 1; index++) {
      if(userData.get(index) == "planche" || userData.get(index) == "panneau"){  
        List<String> woodData = new ArrayList<>();
        int i = index + 1;
        while(i<userData.size()&&userData.get(i) != "planche" && userData.get(i) != "panneau"){
          woodData.add(userData.get(i));
          i++;
        }  
        Panneau w =woodInfo(woodData);
        wList.add(w);
      }
    }
    return new Supplier(id, wList);
  }
  Panneau woodInfo(List<String> woodData){
    
    int id = (int)controle_data(woodData.get(0),"Integer");
    int number = (int)controle_data(woodData.get(1),"Integer");
    String date = woodData.get(2);
    double price = (double)controle_data(woodData.get(3),"Double");
    Dimension dimension=new Dimension((double)controle_data(woodData.get(6),"Double"),(double)controle_data(woodData.get(5),"Double"));
    return new Panneau(id, number, date, price, dimension);
  }
  static Object controle_data(String data, String type){
    Object r=0;
    if(type=="Double"){
        try {
                r=Double.parseDouble(data);
        } catch (Exception e) {
          System.out.println("problem in xml file >>Fournisseurs.xml");
            System.exit(1);

        }
        return (double)r;
    }else  if(type=="Integer"){
        try {
                r=Integer.parseInt(data);
        } catch (Exception e) {
          System.out.println("problem in xml file >>Fournisseurs.xml");
               System.exit(1);

        }
        return (int)r;
    }else
        return null;
}
}
