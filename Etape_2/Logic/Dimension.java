package Logic;
 class Dimension{
    double largeur;
    double Longueur;
    Dimension(double largeur,double Longueur){
        this.largeur=largeur;
        this.Longueur = Longueur;
    }
    // @Override
    // public boolean isValid(Object o){
    //     Dimension dim = (Dimension)o;
    //     return dim.Longueur > 0 && dim.largeur > 0 && dim.Longueur > dim.largeur;
    // }
}
