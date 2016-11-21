package io.github.rhkiswani.javaff.decetor;

public class ApiDetectorUtil {

    private ApiDetectorUtil(){
    }

    private static Boolean isJPAAvailable = null;
    private static Boolean isSlf4jAvailable = null;
    private static Boolean isJacksonAvailable = null;

    public static Boolean isJPAAvailable(){
        if(isJPAAvailable == null){
            isJPAAvailable = ApiDetectorFactory.instance().getDefault().isAvailable("javax.persistence.Id");;
        }
        return isJPAAvailable;
    }

    public static Boolean isSlf4jAvailable(){
        if(isSlf4jAvailable == null){
            isSlf4jAvailable = ApiDetectorFactory.instance().getDefault().isAvailable("org.slf4j.Logger");;
        }
        return isSlf4jAvailable;
    }

    public static Boolean isJacksonAvailable(){
        if(isJacksonAvailable == null){
            isJacksonAvailable = ApiDetectorFactory.instance().getDefault().isAvailable("com.fasterxml.jackson.databind.ObjectMapper");;
        }
        return isJacksonAvailable;
    }

}
