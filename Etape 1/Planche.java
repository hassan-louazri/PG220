public class Planche
{
    int id;
    int number;
    String date;
    double price;
    Dimension dimension;
    Planche(int id, int number,String date, double price,Dimension dimension) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.price =price;
        this.dimension=dimension;

        //String commande = "Read from PlancheClass: Commande n°" + id + " de " + number + " planches à livrer pour le " + date + " au prix maximal de " +price;
       //System.out.println(commande);
       // System.out.println("Read from PlancheClass: dimension L*l=: "+Longeur+"*"+largeur);
    }

}
