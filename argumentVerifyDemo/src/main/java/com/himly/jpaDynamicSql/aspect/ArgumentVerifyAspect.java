package com.himly.jpaDynamicSql.aspect;

import com.himly.jpaDynamicSql.validation.NotNull;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

@Aspect
@Component
public class ArgumentVerifyAspect {

    @Before("execution(* com.himly.jpaDynamicSql.service.impl.*.*(..))")
    public void doArgumentVerifyForService(JoinPoint joinPoint) throws Exception{
        try{
            doArgumentVerify(joinPoint);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    private void doArgumentVerify(JoinPoint joinPoint) throws Exception{
        String methodName = joinPoint.getSignature().getName();
        Method method = getMethod(methodName,joinPoint);
        Object[] args = joinPoint.getArgs();
        String classType = joinPoint.getTarget().getClass().getTypeName();
        Class<?> clz = Class.forName(classType);
        String clzName = clz.getName();
        List<Map<String,Object>> nameAndArgs = getFieldsNameAndValue(this.getClass(),clzName,methodName,args);

        Annotation[] methodAnnotations = method.getAnnotations();
        if (isAnnotationExists(methodAnnotations,NotNull.class)) {
            int i = 0;
            while (nameAndArgs.size() > i) {
                if (nameAndArgs.get(i).get("value") == null) {
                    throw new RuntimeException(nameAndArgs.get(i).get("name") + " can not be null");
                }
                ++i;
            }
        }

        Annotation[][] argsAnnotations = method.getParameterAnnotations();
        int i = 0;
        for (Annotation[] annotation:argsAnnotations) {
            boolean isExists = isAnnotationExists(annotation,NotNull.class);
            if (isExists) {
                if (nameAndArgs.get(i).get("value") == null) {
                    throw new RuntimeException(nameAndArgs.get(i).get("name") + " can not be null");
                }
            }

            ++i;
        }
    }

    private Method getMethod(String name, JoinPoint joinPoint) {

        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method realMethod = null;
        for (Method method :methods) {
            if (method.getName().equals(name)) {
                realMethod = method;
                break;
            }
        }

        if (realMethod == null) {
            throw new RuntimeException("method not found");
        }

        return realMethod;
    }


    public boolean isAnnotationExists(Annotation[] annotations,Class clz) {

        for (Annotation annotation:annotations) {
            Class<? extends Annotation>  annotationType = annotation.annotationType();
            if (annotationType.getName().equals(clz.getName())) {
                return true;
            }
        }

        return false;
    }


    /**
     * 通过javassist反射机制 获取被切参数名以及参数值
     *
     * @param cls
     * @param clazzName
     * @param methodName
     * @param args
     * @return
     * @throws NotFoundException
     */
    private List<Map<String,Object>> getFieldsNameAndValue(Class cls, String clazzName, String methodName, Object[] args) throws NotFoundException {
        List<Map<String,Object>> list = new ArrayList<>(16);

        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            throw new RuntimeException("attr not found");
        }

        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>(2);
            map.put("name",attr.variableName(i+pos));
            map.put("value",args[i]);

            list.add(map);
        }

        return list;
    }
}
