package shop.mtcoding.blogv3.dto.board;

import lombok.Getter;
import lombok.Setter;

public class BoardResDto {

    @Getter
    @Setter
    public static class BoardMainResponseDto {
        private int id;
        private String title;
        private String thumbnail;
        private String username;
    }

    @Getter
    @Setter
    public static class BoardDetailResponseDto {
        private int id;
        private int userId;
        private String title;
        private String thumbnail;
        private String username;
        private String content;
    }
}
