package top.pcat.study.controller;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.pcat.study.pojo.Clasp;
import top.pcat.study.service.ClassService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/classes")
public class ClassController {


    @Resource
    private MinioClient minioClient;

    @Autowired
    ClassService classService;

    /**
     * 创建班级
     */
    @PostMapping("/{userId}")
    public int createClass(@PathVariable String userId, HttpServletRequest request) throws ServletException, IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String className1 = request.getParameter("className");
        String image = request.getParameter("image");

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] result = decoder.decode(image);
        InputStream inputStream = new ByteArrayInputStream(result);

        int aClass = classService.createClass(userId, className1);

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket("group.photo")
                        .object("g"+aClass + ".png")
                        .stream(inputStream, -1, 10485760)
                        .contentType("image/png")
                        .build());

//        String url = minioClient.getPresignedObjectUrl(
//                GetPresignedObjectUrlArgs.builder()
//                        .method(Method.GET)
//                        .bucket("group.photo")
//                        .object(userId + ".png")
//                        .expiry(7, TimeUnit.DAYS)
//                        .build());

        return aClass;
    }

    /**
     * 获取加入的班级列表
     */
    @GetMapping("/{userId}")
    public List<Clasp> getClassList(@PathVariable String userId){
        return classService.getClassList(userId);
    }

    /**
     * 获取创建的班级列表
     */
    @GetMapping("/admin/{userId}")
    public List<Clasp> getCreateClassList(@PathVariable String userId){
        return classService.getCreateClassList(userId);
    }

    /**
     * 加入班级
     */
    @PutMapping("/{userId}/{classId}")
    public int joinClass(@PathVariable String userId,@PathVariable String classId,  @Param("className") String className){
        log.info("加入班级"+userId+" "+classId+" "+className);
        return classService.joinClass(userId, classId, className);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/{groupId}/infoName")
    @ResponseBody
    public String getGroupInfoName(@PathVariable String groupId) {
        return classService.getGroupInfoName(groupId);
    }
}
