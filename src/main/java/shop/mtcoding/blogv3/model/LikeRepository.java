package shop.mtcoding.blogv3.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeRepository {
    public Like findById(int id);

    public List<Like> findByUserId(int userId);

    public List<Like> findByBoardId(int userId);

    public Like findByBoardIdAndUserId(@Param("boardId") int boardId, @Param("userId") int userId);

    public int insert(@Param("boardId") int boardId, @Param("userId") int userId, @Param("code") String code);

    public int deleteById(int id);
}
