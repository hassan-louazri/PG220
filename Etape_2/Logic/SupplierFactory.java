package Logic;
import java.util.List;
import java.util.ArrayList;

class SupplierFactory implements IFactory{

  SupplierFactory(List<String> data){
    generate(data);
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
    int id = Integer.parseInt(userData.get(0));
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
    
    int id = Integer.parseInt(woodData.get(0));
    int number = Integer.parseInt(woodData.get(1));
    String date = woodData.get(2);
    double price = Double.parseDouble(woodData.get(3));
    Dimension dimension=new Dimension(Double.parseDouble(woodData.get(5)), Double.parseDouble(woodData.get(5)));
    return new Panneau(id, number, date, price, dimension);
  }
}
