package Logic;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math.*;
class CutFactory implements IFactory{
    CutFactory(List<String> data){
        generate(data);
    }

    public void generate(List<String> data){
        for (int index = 0; index < data.size(); index++) {
            if(data.get(index) == "decoupe"){
                List<String> cutData = new ArrayList<>();
                int i = index + 2;
                while (i < data.size() && data.get(i) != "decoupe") {
                    cutData.add(data.get(i));
                    i++;
                }
                Cut c = cutInfo(cutData);
                Main.addCutToList(c);
            }
        }
    }

    Cut cutInfo(List<String> cutData){
        int clientId = Integer.parseInt(cutData.get(0));
        int supplierId = Integer.parseInt(cutData.get(3));
        int x = Integer.parseInt(cutData.get(6));
        int y = Integer.parseInt(cutData.get(7));
        int plankId = (int)Math.round(Double.parseDouble(cutData.get(1)));
        int pannelId = (int)Math.round(Double.parseDouble(cutData.get(4)));
        return new Cut(x, y, clientId, supplierId, plankId, pannelId);
    }
}