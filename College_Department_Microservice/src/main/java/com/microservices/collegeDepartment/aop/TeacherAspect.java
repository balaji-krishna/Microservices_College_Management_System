package com.microservices.collegeDepartment.aop;

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

import com.microservices.collegeDepartment.httpData.TeachersResponse;

@Aspect
@Component
public class TeacherAspect {

	public static final Logger LOGGER = LoggerFactory.getLogger(TeacherAspect.class);

	@Before(value = "execution(* com.microservices.collegeDepartment.controller.TeacherController.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		LOGGER.info("Request to {} started at {} ", joinPoint.getSignature(), LocalDate.now());
	}

	@After(value = "execution(* com.microservices.collegeDepartment.controller.TeacherController.*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		LOGGER.info("Request to {} ended at {} ", joinPoint.getSignature(), LocalDate.now());
	}

	@AfterReturning(value = "execution(* com.microservices.collegeDepartment.service.impl.TeacherServiceImpl.getAllTeachersInfo(..))", returning = "result")
	public void afterReturningAdvice(JoinPoint joinPoint, TeachersResponse result) {
		LOGGER.info(
				"Business logic to getAllTeachersInfo() method in TeacherServiceImpl completed successfully with response = {}",
				result);
	}

	@AfterThrowing(value = "execution(* com.microservices.collegeDepartment.service.impl.TeacherServiceImpl.getAllTeachersInfo(..))", throwing = "exception")
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception exception) {
		LOGGER.info("Business logic to getAllTeachersInfo() method in TeacherServiceImpl threw exception = {}",
				exception.getMessage());
	}

	@Around(value = "execution(* com.microservices.collegeDepartment.service.impl.TeacherServiceImpl.getAllTeachersInfo(..))")
	public TeachersResponse aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		LOGGER.info("In Around advice, request to {} started at {} ", proceedingJoinPoint.getSignature(),
				LocalDate.now());
		try {
			TeachersResponse result = (TeachersResponse) proceedingJoinPoint.proceed();
			return result;
		} catch (Throwable e) {
			LOGGER.info(
					"In Around advice, business logic to getAllTeachersInfo() method in TeacherServiceImpl threw exception = {}",
					e.getMessage());
		}
		LOGGER.info("In Around advice, request to {} ended at {}", proceedingJoinPoint.getSignature(), LocalDate.now());
		return null;
	}

}
