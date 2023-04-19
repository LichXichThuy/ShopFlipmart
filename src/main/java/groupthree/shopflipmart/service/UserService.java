package groupthree.shopflipmart.service;

import groupthree.shopflipmart.dto.UserDto;
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
    public UserDto getUserByEmail(String email) {
        UserDto userDto = new UserDto();
        userDto.getUserDto(userRepository.findByEmail(email));

        return userDto;
    }

    @Override
    public boolean saveUser(String email, String name, int phone, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPhoneNum(phone);
        user.setName(name);
        user.setPassword(password);

        try {
            userRepository.save(user);
            return true;
        } catch (Exception e){
            System.err.println("Error save user in UserService + " + e.getMessage());
            return false;
        }
    }
}
