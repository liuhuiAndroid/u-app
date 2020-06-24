package com.study.u.validator;

import com.study.u.exception.GlobalException;
import com.study.u.result.CodeMsg;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Order(1)
public class ValidAop {

    /**
     * 所有controller方法都会执行此切面
     * 用于检验参数
     */
    @Pointcut("@annotation(com.study.u.validator.ValidAnn)")
    public void validAop() {
    }

    /**
     * 对切面进行字段验证
     *
     * @param joinPoint 切点
     * @return Object
     * @throws Throwable
     */
    @Around("validAop()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        List<String> errorList = new ArrayList<>();
        if (args != null) {
            Arrays.stream(args)
                    .forEach(arg -> {
                        if (arg instanceof BindingResult) {
                            BindingResult result = (BindingResult) arg;
                            if (result.hasErrors()) {
                                StringBuilder stringBuilder = new StringBuilder();
                                result.getAllErrors()
                                        .forEach(error -> {
                                            stringBuilder.append(error.getDefaultMessage());
                                            stringBuilder.append(",");
                                            errorList.add(error.getDefaultMessage());
                                        });
                                if (stringBuilder.length() > 0) {
                                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                                }
                                throw new GlobalException(CodeMsg.BIND_ERROR.fillArgs(stringBuilder.toString()));
                            }
                        }
                    });
        }
        return joinPoint.proceed();
    }
}