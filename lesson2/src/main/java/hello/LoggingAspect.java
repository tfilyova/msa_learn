package hello;


import org.apache.tomcat.util.json.JSONParser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());


    @Pointcut("within(hello.GreetingController)")
    public void stringProcessingMethods() {
    };

    @Before("stringProcessingMethods()")
    public void beforeCallAtMethod1(JoinPoint joinPoint) {
        String paramList = "";
        MethodSignature method = (MethodSignature) joinPoint.getSignature();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            paramList += method.getParameterNames()[i] + " = " + joinPoint.getArgs()[i] + "; ";
        }
        logger.log(Level.INFO,"Входные параметры " + joinPoint.toString() + " args={" + paramList + "}");
    }

    @AfterReturning(pointcut ="stringProcessingMethods()", returning = "returnValue")
    public void logAfterReturning(JoinPoint joinPoint, Object returnValue) {
        logger.log(Level.INFO," Возвращаемое значение " + joinPoint.toString() +" {" + returnValue + "}");

    }



}