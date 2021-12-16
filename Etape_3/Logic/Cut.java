package Logic;
import java.util.List;
class Cut implements IValidate{
    int PositionX;
    int PositionY;
    int clientId, supplierId, idPlanche, idPanneau, numPlanche, numPanneau;
    Cut(int PositionX, int PositionY, int cid, int sid, int idPl, int idPan, int numPl, int numPan) {
        this.PositionX = PositionX;
        this.PositionY = PositionY;
        this.clientId = cid;
        this.supplierId = sid;
        this.idPlanche = idPl;
        this.idPanneau = idPan;
        this.numPlanche = numPl;
        this.numPanneau = numPan;
    }
    @Override
    public boolean[] isValid(){
        List<Client> clients = Main.getClientsList();
        List<Supplier> suppliers = Main.getSuppliersList();
        Cut cut =this;
        boolean[] TrueList = {true, true, true};
        if(cut.PositionX < 0 || cut.PositionY < 0){
          System.out.println("Error in cut: Positions must be positive integers.");
          TrueList[0] = false;
        }
        Dimension dimPlanche = User.getUserDimensions(cut.clientId, cut.idPlanche, clients);
        Dimension dimPanneau = User.getUserDimensions(cut.supplierId, cut.idPanneau, suppliers);
        if ( (cut.PositionY + dimPlanche.Longueur > dimPanneau.Longueur) || (cut.PositionX + dimPlanche.largeur > dimPanneau.largeur) ){
          TrueList[2] = false;
        }
        return TrueList;
    }
}
