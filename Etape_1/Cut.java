
public class Cut implements IValidate{
    double PositionX;
    double PositionY;
int clientid,supplierid;
int numplanche,numpanneau;
Cut(double PositionX, double PositionY,int cid,int sid, int npl,int npan) {
        this.PositionX=PositionX;
        this.PositionY=PositionY;
        this.clientid=cid;
        this.supplierid=sid;
        this.numplanche=npl;
        this.numpanneau=npan;
    }
    @Override
    public boolean isValid(Object o){
        Cut cut =(Cut)o;
        return cut.PositionX>=0 && cut.PositionY>=0;
    }
}