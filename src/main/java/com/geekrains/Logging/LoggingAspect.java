package com.geekrains.Logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LoggingAspect {

    Map<String,Long> statistic = new HashMap<String,Long>(){{
        put("CartItemService", new Long(0));
        put("OrderService",new Long(0));
        put("ProductService",new Long(0));
        put("UserService",new Long(0));
    }};

    public Map<String, Long> getStatistic() {
        return statistic;
    }

    public static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(LogExecutionTime)")
    public Object methodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        // Measure method execution time
        StopWatch stopWatch = new StopWatch(className + "->" + methodName);
        stopWatch.start(methodName);
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        // Log method execution time
        if (logger.isInfoEnabled()) {
            logger.info(stopWatch.prettyPrint());
        }

        statistic.put(className, statistic.get(className) + stopWatch.getLastTaskTimeMillis());

        System.out.println("Statistik :" + statistic);

        return result;
    }
}
