package shop.mtcoding.blogv3.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reply {
    private int id;
    private int userId;
    private int boardId;
    private String comment;
    private Timestamp createdAt;
}
