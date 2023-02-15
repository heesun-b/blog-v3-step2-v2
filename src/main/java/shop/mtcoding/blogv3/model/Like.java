package shop.mtcoding.blogv3.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Like {
    private int id;
    private int userId;
    private int boardId;
    private Timestamp createdAt;
}
