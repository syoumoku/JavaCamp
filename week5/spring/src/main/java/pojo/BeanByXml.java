package pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class BeanByXml {
    @Autowired
    private BeanByAnnotation bean2;
    private String name;
    private Long id;
}
