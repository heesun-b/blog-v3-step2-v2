package shop.mtcoding.blogv3.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import shop.mtcoding.blogv3.dto.board.BoardReqDto.BoardSaveReqDto;

public class Thumbnail {

    public static String thumbnailParse(String html) {

        Document d = Jsoup.parse(html);
        Elements els = d.select("img");

        if (els.size() == 0) {
            return "/images/dora.png";
        } else {
            Element el = els.get(0);
            return el.attr("src");
        }
    }
}
