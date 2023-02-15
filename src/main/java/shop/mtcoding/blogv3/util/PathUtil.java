package shop.mtcoding.blogv3.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import shop.mtcoding.blogv3.handler.ex.CustomException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class PathUtil {

    private static String getStaticFolder() {
        return System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
    }

    public static String writeImageFile(MultipartFile profile) {

        UUID uuid = UUID.randomUUID();
        String uuidImageDBName = "/images/" + uuid + "_" + profile.getOriginalFilename();
        String uuidImageRealName = "images\\" + uuid + "_" + profile.getOriginalFilename();

        String staticFolder = getStaticFolder();
        Path imageFilePath = Paths.get(staticFolder + uuidImageRealName);
        try {
            // 내부적으로 스레드 구현 : 비동기
            // Files.write(path(경로), 바이트 데이터) : 바이트스트림으로 버퍼에 전송하기 때문에 getBytes
            Files.write(imageFilePath, profile.getBytes());
        } catch (Exception e) {
            throw new CustomException("사진을 웹서버에 저장하지 못하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return uuidImageDBName;
    }
}