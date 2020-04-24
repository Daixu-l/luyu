package luyu;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * @author Daniel
 * 2020/4/24 2:10
 */
public class FreemarkerUtil {

    public static String processTemplate(String templateValue, HashMap<String, Object> map) throws IOException, TemplateException {
        Configuration configuration = new Configuration();
        String templateName = "defaultTemplate";
        StringWriter stringWriter = new StringWriter();
        Template template = new Template(templateName, templateValue, configuration);
        template.process(map, stringWriter);
        return stringWriter.toString();
    }
}
