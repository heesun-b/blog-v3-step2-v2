package shop.mtcoding.blogv3.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Like {

    private Integer id;
    private Integer boardId;
    private Integer userId;
    private Timestamp createdAt;
}
