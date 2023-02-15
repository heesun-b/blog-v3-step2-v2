package shop.mtcoding.blogv3.dto.reply;

import java.sql.Timestamp;

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

    @Getter
    @Setter
    public static class ReplyAdminResDto {
        private Integer id;
        private String comment;
        private String username;
        private Integer boardId;
        private Timestamp createdAt;
    }

}
