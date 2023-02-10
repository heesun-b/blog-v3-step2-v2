package shop.mtcoding.blogv3.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blogv3.dto.reply.ReplyResDto.ReplyDetailResDto;

@Mapper
public interface ReplyRepository {
    public List<Reply> findAll();

    public Reply findById(int id);

    public int insert(@Param("userId") int userId, @Param("boardId") int boardId,
            @Param("comment") String comment);

    public int updateById(@Param("id") int id, @Param("comment") String comment);

    public int deleteById(int id);

    public List<ReplyDetailResDto> findByBoardIdWithUser(int boardId);
}
