package shop.mtcoding.blogv3.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

    public int insert(@Param("username") String username, @Param("password") String password,
            @Param("email") String email);

    public User findByUsername(String username);

    public User findByEmail(String email);

    public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
