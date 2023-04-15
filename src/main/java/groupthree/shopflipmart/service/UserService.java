package groupthree.shopflipmart.service;

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
    public boolean login(String email, String password) {
        List<User> list = userRepository.findAll();
        return list.size() >= 1;
    }
}
