package top.pcat.study.utils;

import java.util.UUID;

/**
 * @program: study
 * @description: UUIDUtils
 * @author: PCat
 * @create: 2022-02-14 11:33
 **/
public class UUIDUtils {

    public static  String getUUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
