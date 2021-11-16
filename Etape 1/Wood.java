public abstract class Wood{
    int id;
    int number;
    String date;
    double price;
    Dimension dimension;
    Wood(int id, int number,String date, double price,Dimension dimension) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.price =price;
        this.dimension=dimension;
    }
}