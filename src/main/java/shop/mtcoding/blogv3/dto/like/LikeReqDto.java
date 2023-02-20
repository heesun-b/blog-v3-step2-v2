package shop.mtcoding.blogv3.dto.like;

import lombok.Getter;
import lombok.Setter;

public class LikeReqDto {

    @Getter
    @Setter
    public static class LoveSaveReqDto {
        private Integer boardId;
    }

}
