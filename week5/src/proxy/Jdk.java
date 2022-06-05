package proxy;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import proxy.impl.UserApi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class Jdk {
    public static <T> T getProxy(Class clazz) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();;
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
                return proxy.getClass().getSimpleName()+ " " +method.getName() + " was proxied by JDKProxy";
            }
        });
    }

    @Test
    public void test_reflection() throws Exception {
        Class<UserApi> clazz = UserApi.class;
        Method queryUserInfo = clazz.getMethod("queryUserInfo");
        Object invoke = queryUserInfo.invoke(clazz.newInstance());
        System.out.println(invoke);
    }


    @Test
    public void test_Jdk_getProxy() throws Exception {
        IUserApi api = Jdk.getProxy(IUserApi.class);
        String invoke = api.queryUserInfo();
        System.out.println(invoke);
    }

}
