package shop.mtcoding.blogv3.dto.reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyResDto {

    @Getter
    @Setter
    public static class ReplyDetailResDto {
        private Integer id;
        private String comment;
        private Integer userId;
        private String username;
        private Integer boardId;
    }
}
