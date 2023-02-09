package shop.mtcoding.blogv3.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private int id;
    private int userId;
    private String username;
    private String title;
    private String thumbnail;
    private String content;
    private Timestamp createdAt;
}
