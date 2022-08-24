package com.paimonx;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author xu
 * @date 2022/7/26
 */
public class Patch_Zulu11_35_13 implements ClassFileTransformer{

    private static final String CLASSNAME = "javax.crypto.JceSecurity$IdentityWrapper";


    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        String replace = className.replace("/", ".");
        // 仅对指定的类修改
        if (CLASSNAME.equals(replace)) {

            ClassPool classPool = ClassPool.getDefault();
            try {
                // 获取完整类实例
                CtClass clazz = classPool.get(CLASSNAME);
                // 要添加的field
                CtField field = new CtField(CtClass.intType, "hash", clazz);
                field.setModifiers(Modifier.FINAL);
                clazz.addField(field);
                // 对构造器进行
                CtConstructor constructor = clazz.getConstructor("(Ljava/security/Provider;)V");
                // 缓存原属性 hash
                constructor.insertAfter("this.hash =  System.identityHashCode($1);");
                // 修改 hashCode 方法 返回缓存的 hash 属性
                CtMethod hashCode = clazz.getMethod("hashCode", "()I");
                hashCode.setBody("return this.hash;");
                return clazz.toBytecode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
