package Logic;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

 abstract class Wood implements IValidate{
    int id;
    int number;
    String date;
    double price;
    Dimension dimension;
    Wood(int id, int number,String date, double price,Dimension dimension) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.price = price;
        this.dimension = dimension;
    }


    @Override
    public boolean[] isValid() {
      Wood o = this;
      boolean[] TrueList = {true, true, true};
      //Date
      String DeadlineText = ((Wood)o).date;

      DateTimeFormatter formatterDeadline = DateTimeFormatter.ofPattern("dd.MM.yy");
      LocalDate Deadline;
      try{
        Deadline = LocalDate.parse(DeadlineText, formatterDeadline);
      }catch(Exception e){
        System.out.println("Date format invalid");
        Deadline = LocalDate.parse("01.01.21", formatterDeadline);
      }
      //currentDate
      LocalDate currentDate = LocalDate.now();
      if(!Deadline.isAfter(currentDate)) TrueList[2] = false;
      if(o.price <= 0) TrueList[0] = false;
      if(o.dimension.Longueur < 0 || o.dimension.largeur < 0 || o.dimension.Longueur < o.dimension.largeur) TrueList[1] = false;
      return TrueList;
    }
}
