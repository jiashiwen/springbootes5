package ren.shiwen.springbootes5.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAdvice {
	private Logger logger = LogManager.getLogger(this.getClass());

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Pointcut("execution(public * ren.shiwen.springbootes5.controller..*.*(..))")
	public void ControllerLog() {
	}

	@Before("ControllerLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());
	}

	@AfterReturning(returning = "ret", pointcut = "ControllerLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("RESPONSE : " + ret);
		logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
	}
}
