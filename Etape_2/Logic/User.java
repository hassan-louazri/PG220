package Logic;
import java.util.List;

 abstract class User implements IValidate{
    int id;
    List<? extends Wood> listW;
    User(int id, List<? extends Wood>listWood)
    {
        this.id = id;
        this.listW = listWood;
    }

    @Override
    public boolean isValid(Object o){
        return ((Wood)o).price >= 0;
    }

    static boolean existsInList(int userId, int woodId, List <? extends User>userList){
        for(User u : userList){
          if(u.id == userId){
            for (Wood w : u.listW){
              if(woodId == w.id){
                return true;
              }
            }
            return false;
          }
        }
        return false;
    }
}
