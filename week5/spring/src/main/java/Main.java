import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pojo.BeanByAnnotation;
import pojo.BeanByXml;

public class Main {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BeanByXml bean1 = (BeanByXml) context.getBean("beanbyxml1");
        System.out.println(bean1.toString());



    }
}
