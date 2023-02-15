package shop.mtcoding.blogv3.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeRepository {
    public List<Like> findByUserId(int userId);

    public List<Like> findByBoardId(int boardId);

    public Like findByBoardIdAndUserId(@Param("boardId") int boardId, @Param("userId") int userId);

    public int insert(@Param("userId") int userId, @Param("boardId") int boardId);

    public int deleteById(int id);
}
