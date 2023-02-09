package shop.mtcoding.blogv3.dto.user;

import lombok.Getter;
import lombok.Setter;

public class UserReqDto {

    @Getter
    @Setter
    public static class JoinReqDto {
        private String username;
        private String password;
        private String email;
    }

    @Getter
    @Setter
    public static class LoginReqDto {
        private String username;
        private String password;
    }
}
