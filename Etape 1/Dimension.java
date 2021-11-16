public class Dimension implements IValidate{
    double largeur;
    double Longeur;
    Dimension(double largeur,double Longeur){
        this.largeur=largeur;
        this.Longeur=Longeur;
    }
    @Override
    public boolean isValid(Object o){
        if(((Dimension)o).Longeur<((Dimension)o).largeur){
            System.out.print(" --->Longeur:"+Longeur+" > largeur: "+largeur +"?: ");
            return false;
        }
            System.out.print(" --->Longeur:"+Longeur+" > largeur: "+largeur +"?: ");
            return true;
    }
}
