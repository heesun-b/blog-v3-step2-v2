package shop.mtcoding.blogv3.dto.reply;

import lombok.Getter;
import lombok.Setter;

public class ReplyReqDto {

    @Getter
    @Setter
    public static class ReplySaveReqDto {
        private String comment;
        private Integer boardId;
    }

}
