package Logic;
import java.util.List;

 abstract class User{
    int id;
    List<? extends Wood> listW;
    User(int id, List<? extends Wood>listWood)
    {
        this.id = id;
        this.listW = listWood;
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

    static Dimension getUserDimensions(int userId, int woodId, List<? extends User>userList){
      Dimension dim = new Dimension(-1, -1);
      for (User user : userList){
        if (user.id == userId){
          for (Wood wood : user.listW){
            if (wood.id == woodId){
              dim = wood.dimension;
              return dim;
            }
          }
        }
      }
      return dim;
    }
}
