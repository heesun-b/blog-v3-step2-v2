package shop.mtcoding.blogv3.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeRepository {
    public Like findById(int id);

    public List<Like> findAll();

    public List<Like> findByUserId(int userId);

    public List<Like> findByBoardId(int boardId);

    public Like findByBoardIdAndUserId(@Param("boardId") int boardId, @Param("userId") int userId);

    public int insert(Like like);

    public int updateById(Like like);

    public int deleteById(int id);
}
