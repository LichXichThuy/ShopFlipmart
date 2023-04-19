package groupthree.shopflipmart.service.Imp;

import groupthree.shopflipmart.dto.UserDto;
import groupthree.shopflipmart.entity.User;

import java.util.List;

public interface UserServiceImp {

    User getUserById(int userId);

    boolean login(String email, String password);

    UserDto getUserByEmail(String email);

    boolean saveUser(String email, String name, int phone, String password);
}
