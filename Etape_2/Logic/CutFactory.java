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

        String plank = cutData.get(1);
        int plankId = Integer.parseInt(plank.substring(0, plank.indexOf(".")));
        int numPlank = Integer.parseInt(plank.substring(1 + plank.indexOf(".")));

        String pannel = cutData.get(4);
        int pannelId = Integer.parseInt(pannel.substring(0, pannel.indexOf(".")));
        int numPannel = Integer.parseInt(pannel.substring(1 + pannel.indexOf(".")));

        return new Cut(x, y, clientId, supplierId, plankId, pannelId, numPlank, numPannel);
    }
}
