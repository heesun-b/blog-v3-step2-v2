package shop.mtcoding.blogv3.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Like {

    private int id;
    private int boardId;
    private int userId;
    private String code;
}
