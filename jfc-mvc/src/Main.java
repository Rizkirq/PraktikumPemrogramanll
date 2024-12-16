import model.MyBatisUtil;
import model.UserMapper;
import org.apache.ibatis.session.SqlSession;
<<<<<<< HEAD
import view.UserPdf;
=======
>>>>>>> 797ff23aeb0406db82a84c10a652d4c59f02e565
import view.UserView;
import controller.UserController;

public class Main {
    public static void main(String[] args){
        SqlSession session = MyBatisUtil.getSqlSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
<<<<<<< HEAD
        UserPdf pdf = new UserPdf();

        UserView view = new UserView();
        new UserController(view, mapper,pdf, session);

        view.setVisible(true);
    }
}
=======

        UserView view = new UserView();
        new UserController(view, mapper, session);

        view.setVisible(true);
    }
}
>>>>>>> 797ff23aeb0406db82a84c10a652d4c59f02e565
