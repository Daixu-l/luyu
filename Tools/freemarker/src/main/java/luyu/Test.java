package luyu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import freemarker.template.TemplateException;
import freemarker.template.utility.DateUtil;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Daniel
 * 2020/4/24 2:11
 */
public class Test {

    public static void main(String[] args) throws IOException, TemplateException {
        System.out.println(testCombinationPrintlnString());

    }

    //测试同时使用两个api情况下的打印
    private static String testCombinationPrintlnString() throws IOException, TemplateException {
        HashMap<String, Object> templateDataMap = new HashMap<String, Object>();
        JSONObject templateData = new JSONObject();
        templateData.put("string", "    777777777777777777777777777   ");

        //测试去空格的同时截取字符串长度
        String template1 = "test string ${data.string?trim?substring(0,8)}";

        templateDataMap.put("data", templateData);
        return FreemarkerUtil.processTemplate(template1, templateDataMap);
    }

    //测试循环情况下的打印格式
    private static String loopPrintlnString() throws IOException, TemplateException {
        HashMap<String, Object> templateDataMap = new HashMap<String, Object>();
        JSONObject templateData = new JSONObject();
        templateData.put("string", " arb bucks ");

        //单独的进行循环打印
        String template1 = "<#list array as data>\n" +
                "name:${data.name} age:${data.age} \n" +
                "</#list>";

        //循环和其他的输出混合使用
        String template2 = "${datas.string}\n" +
                "<#list array as data>\n" +
                "name:${data.name} age:${data.age}\n" +
                "</#list>";

        templateDataMap.put("datas", templateData);
        templateDataMap.put("array", getTestDataArray());
        return FreemarkerUtil.processTemplate(template1, templateDataMap);
    }


    //测试各种字符串的打印格式
    private static String testPrintlnString() throws IOException, TemplateException {
        HashMap<String, Object> templateDataMap = new HashMap<String, Object>();
        JSONObject templateData = new JSONObject();
        templateData.put("string", "777777777777777777777777777");

        //将字符串中的首字母大写,不会判断字符串中首字母是否为空
        String template1 = "test string ${data.string?cap_first}";
        //将字符串的首字母小写,不会判断字符串中首字母是否为空
        String template2 = "test string ${data.string?uncap_first}";
        //将首字母大写 并以空格做为分割将每个被分割的字符串也大写
        String template3 = "test string ${data.string?capitalize}";
        //将字符串的长度做为返回值输出
        String template4 = "test string ${data.string?length}";
        //替换制定的字符串
        String template5 = "test string ${data.string?replace(\"arb\",\"ARB\")}";
        //去收尾空格
        String template6 = "test string ${data.string?trim}";
        //制定一个字符串的长度如果不足次长度在右侧拼接制定的字符串
        String template7 = "test string ${data.string?right_pad(35,\"~~~\")}";
        //制定一个字符串的长度如果不足次长度在左侧拼接制定的字符串 默认为空格
        String template8 = "test string ${data.string?left_pad(35,\"~~~\")}";
        //截取字符串指定长度从多少开始截取多少位
        String template9 = "test string ${data.string?substring(0,8)}";

        templateDataMap.put("data", templateData);
        return FreemarkerUtil.processTemplate(template9, templateDataMap);
    }


    //测试各种数字的打印格式
    private static String testPrintlnNumber() throws IOException, TemplateException {
        HashMap<String, Object> templateDataMap = new HashMap<String, Object>();
        JSONObject templateData = new JSONObject();
        templateData.put("number", 1111);

        //将浮点类型的数字精确到小数点后一位且不会自动四舍五入 后面的0个数代表精确的位数以此类推,如果原来的数字位数不够则用0填充
        String template1 = "test number ${data.number?string[\"0.00\"]}}";
        //将数字按3位一个逗号进行分割并保留两位小数
        String template2 = "test number ${data.number?string[\",###.00\"]}}";
        //将数字转化为百分数进行输出
        String template3 = "test number ${data.number?string[\"percent\"]}}";
        //将数字以0000的形式输出,超过0的位数后显示真实的数字,如果遇到浮点类型的数会自动四舍五入
        String template4 = "test number ${data.number?string[\"0000000\"]}";
        //将数字输出去掉 ,
        String template5 = "test number ${data.number?c}";
        templateDataMap.put("data", templateData);
        return FreemarkerUtil.processTemplate(template5, templateDataMap);
    }


    //测试用模板打印现在的时间 时间格式可自定义
    private static String testPrintlnTime() throws IOException, TemplateException {
        HashMap<String, Object> templateDataMap = new HashMap<String, Object>();
        JSONObject templateData = new JSONObject();
        templateData.put("time", new Date(System.currentTimeMillis()));

        String template = "current time ${data.time?string('yyyy-MM-dd HH:mm')}";

        templateDataMap.put("data", templateData);
        return FreemarkerUtil.processTemplate(template, templateDataMap);
    }


    private static JSONArray getTestDataArray() {
        JSONArray resultArray = new JSONArray();


        JSONObject dataJson1 = new JSONObject();
        dataJson1.put("name", "liergou");
        dataJson1.put("age", 18);
        resultArray.add(dataJson1);

        JSONObject dataJson2 = new JSONObject();
        dataJson2.put("name", "zhangsan");
        dataJson2.put("age", 20);
        resultArray.add(dataJson2);

        JSONObject dataJson3 = new JSONObject();
        dataJson3.put("name", "luyu");
        dataJson3.put("age", 22);
        resultArray.add(dataJson3);

        return resultArray;

    }

}
