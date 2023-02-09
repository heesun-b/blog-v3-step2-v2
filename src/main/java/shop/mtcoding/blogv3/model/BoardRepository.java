package shop.mtcoding.blogv3.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.blogv3.dto.board.BoardResDto.BoardDetailResponseDto;
import shop.mtcoding.blogv3.dto.board.BoardResDto.BoardMainResponseDto;

@Mapper
public interface BoardRepository {

    public int insert(@Param("userId") int userId, @Param("title") String title,
            @Param("content") String content, @Param("thumbnail") String thumbnail);

    public List<BoardMainResponseDto> findAllWithUser();

    public Board findById(int id);

    public BoardDetailResponseDto findByIdWithUser(int id);

    public int deleteById(int id);

    public int updateById(@Param("id") int id, @Param("title") String title,
            @Param("content") String content, @Param("thumbnail") String thumbnail);

}
