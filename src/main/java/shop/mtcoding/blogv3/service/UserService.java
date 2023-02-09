package shop.mtcoding.blogv3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv3.dto.user.UserReqDto.JoinReqDto;
import shop.mtcoding.blogv3.dto.user.UserReqDto.LoginReqDto;
import shop.mtcoding.blogv3.handler.ex.CustomException;
import shop.mtcoding.blogv3.model.User;
import shop.mtcoding.blogv3.model.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void join(JoinReqDto joinReqDto) {
        User SameUsername = userRepository.findByUsername(joinReqDto.getUsername());

        if (SameUsername != null) {
            throw new CustomException("동일한 Username이 존재합니다.");
        }

        User SameEmail = userRepository.findByEmail(joinReqDto.getEmail());
        if (SameEmail != null) {
            throw new CustomException("동일한 Email이 존재합니다.");
        }

        int result = userRepository.insert(joinReqDto.getUsername(), joinReqDto.getPassword(), joinReqDto.getEmail());

        if (result != 1) {
            throw new CustomException("회원가입 실패");
        }
    }

    @Transactional(readOnly = true)
    public User login(LoginReqDto loginReqDto) {
        User principal = userRepository.findByUsernameAndPassword(loginReqDto.getUsername(), loginReqDto.getPassword());

        if (principal == null) {
            throw new CustomException("Username 또는 Password를 확인하세요.");
        }
        return principal;
    }
}
