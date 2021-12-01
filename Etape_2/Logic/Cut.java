package Logic;
class Cut implements IValidate{
    double PositionX;
    double PositionY;
    int clientId, supplierId, numPlanche, numPanneau;
    Cut(double PositionX, double PositionY, int cid, int sid, int npl, int npan) {
        this.PositionX = PositionX;
        this.PositionY = PositionY;
        this.clientId = cid;
        this.supplierId = sid;
        this.numPlanche = npl;
        this.numPanneau = npan;
    }
    @Override
    public boolean isValid(Object o){
        Cut cut =(Cut)o;

        // if(cut.PositionX <= 0 || cut.PositionY <= 0){
        //   return false;
        // }
        // if(!User.existsInList(cut.clientId, cut.numPlanche, Test.listClient) || !User.existsInList(cut.supplierId, cut.numPanneau, Test.listSupplier)){
        //   return false;
        // }

        return true;
    }
}
