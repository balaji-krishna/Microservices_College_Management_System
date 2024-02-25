package com.microservices.student.aop;

import java.time.LocalDate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentAspect {

	public static final Logger LOGGER = LoggerFactory.getLogger(StudentAspect.class);

	@Before(value = "execution(* com.microservices.student.controller.StudentController.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		LOGGER.info("Request to {} started at {} ", joinPoint.getSignature(), LocalDate.now());
	}

	@After(value = "execution(* com.microservices.student.controller.StudentController.*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		LOGGER.info("Request to {} ended at {} ", joinPoint.getSignature(), LocalDate.now());
	}

	@AfterReturning(value = "execution(* com.microservices.student.service.impl.StudentServiceImpl.afterReturnThrowDemo(..))", 
			returning = "result")
	public void afterReturningAdvice(JoinPoint joinPoint, String result) {
		LOGGER.info(
				"Business logic to afterReturnThrowDemo() method in StudentServiceImpl completed successfully with response = {}",
				result);
	}

	@AfterThrowing(value = "execution(* com.microservices.student.service.impl.StudentServiceImpl.afterReturnThrowDemo(..))", 
			throwing = "exception")
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception exception) {
		LOGGER.info("Business logic to afterReturnThrowDemo() method in StudentServiceImpl threw exception = {}",
				exception.getMessage());
	}
	
	@Around(value = "execution(* com.microservices.student.service.impl.StudentServiceImpl.aroundAdviceDemo(..))")
	public String aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		LOGGER.info("In Around advice, request to {} started at {} ", proceedingJoinPoint.getSignature(), LocalDate.now());
		try {
			String result = (String) proceedingJoinPoint.proceed();
			return result;
		} catch (Throwable e) {
			LOGGER.info("In Around advice, business logic to aroundAdvice() method in StudentServiceImpl threw exception = {}",
					e.getMessage());
		}
		LOGGER.info("In Around advice, request to {} ended at {}", proceedingJoinPoint.getSignature(), LocalDate.now());
		return null;
	}

}
