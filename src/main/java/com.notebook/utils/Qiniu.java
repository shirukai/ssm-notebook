package com.notebook.utils;

import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 七牛上传封装
 */
public class Qiniu {
    private static Logger logger = LoggerFactory.getLogger("七牛上传");
    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static String ACCESS_KEY = "kT93RyyRfkVBgned_N-xLjAlB3kobwGyt7aykgx3";
    private static String SECRET_KEY = "MVOQB-Nz2q55UA7p9H_MSJ9XkJrGrPyv_oSnbJtX";
    //外链域名
    private static String domian = "http://ov1a6etyz.bkt.clouddn.com/";
    //空间
    private static String bucketname = "notebook";

    /**
     * 获取token
     * @return map
     */
    public static Map<String,String> getToken(){
        Config.ACCESS_KEY = ACCESS_KEY;
        Config.SECRET_KEY = SECRET_KEY;
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        PutPolicy putPolicy = new PutPolicy(bucketname);
        Map<String,String> map = new HashMap<String, String>();
        try {
            String uptoken = putPolicy.token(mac);
            logger.info("uptoken:{}",uptoken);
            map.put("uptoken",uptoken);
            map.put("bucketName",bucketname);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
     return map;
    }

    /**
     * 上传
     * @param file MultipartFile
     * @return map
     */
    public static Map<String,String> upload(MultipartFile file){

        //上传到七牛后保存的文件名
        String key;
        //密钥配置
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        //自动识别要上传的空间的初村区域是华东、华北、华南
        Zone z = Zone.autoZone();
        com.qiniu.storage.Configuration  c  = new com.qiniu.storage.Configuration(z);
        //创建上传对象
        UploadManager uploadManager = new UploadManager(c);
        //获取upToken
        String upToken = auth.uploadToken(bucketname);
        //开始时间
        long  startTime=System.currentTimeMillis();
        String timeString = String.valueOf(startTime);
        logger.info("start_time:{}",startTime);
        Map<String ,String> map = new HashMap<String, String>();
        logger.info("file_name:{}",file.getOriginalFilename());
        key = timeString+file.getOriginalFilename();
        InputStream inputStream;

        if (!file.isEmpty()){
            String fileName = timeString+file.getOriginalFilename();
                try {
                    inputStream = file.getInputStream();
                    Response response = uploadManager.put(inputStream,key,upToken,null,null);
                    logger.info("response:{}",response.bodyString());
                    map.put("state","1");
                    map.put("info","上传七牛成功");
                    map.put("fileName",fileName);
                    map.put("qiniuUrl",domian+"/"+fileName);

                }catch (QiniuException e){
                    map.put("state","0");
                    map.put("info","上传七牛失败");
                    Response r = e.response;
                    logger.error("error:{}",r.toString());
                }catch (IOException e){
                    logger.error(e.getMessage());
                }
        }
        //结束时间
        long endTime=System.currentTimeMillis();
        logger.info("time:{}",endTime-startTime+"ms");
        return map;
    }

    /**
     * 删除文件
     * @param fileName 文件名
     * @return 1 表名成功
     */
    public static int delete(String fileName){
        Zone z = Zone.autoZone();
        com.qiniu.storage.Configuration  cfg  = new com.qiniu.storage.Configuration(z);
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);

        try {
        bucketManager.delete(bucketname,fileName);
        return 1;
        } catch (QiniuException ex) {
            logger.error(ex.response.toString());
        }
        return -1;
    }
}
