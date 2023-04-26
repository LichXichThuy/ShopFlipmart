package groupthree.shopflipmart.service;

import groupthree.shopflipmart.dto.UserDto;
import groupthree.shopflipmart.entity.Role;
import groupthree.shopflipmart.entity.User;
import groupthree.shopflipmart.repository.UserRepository;
import groupthree.shopflipmart.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(int userId) {
        return userRepository.getById(userId);
    }

    @Override
    public boolean login(String email, String password) {
        List<User> list = userRepository.findAllByEmailAndPassword(email, password);
        return list.size() >= 1;
    }

    @Override
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public boolean saveUser(String email, String name, int phone, String password) {
        User user = new User();
        Role role = new Role();
        role.setId(3);

        user.setEmail(email);
        user.setPhoneNum(phone);
        user.setName(name);
        user.setPassword(password);
        user.setRole(role);

        try {
            userRepository.save(user);
            return true;
        } catch (Exception e){
            System.err.println("Error save user in UserService + " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean changePassword(String password, String email) {
        try {
            userRepository.updatePasswordByEmail(password, email);
            return true;
        }catch (Exception e){
            System.out.println("Error change password: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean changeInfor(User user) {
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            System.out.println("Error change information: " + e.getMessage());
            return false;
        }
    }
}
