package shop.mtcoding.blogv3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeReqDto {
    private int userId;
    private int BoardId;
}
