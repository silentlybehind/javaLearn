package test.javaBasic;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlDecode {
    // 测试
    public static void main(String[] args) {
        // try {
        //     // 进行编码
        //     String encode = enCode("中文");
        //     System.out.println(encode);
        //     // 进行解码
        //     String ss = "https%3A%2F%2Fbig.bilibili.com%2Fmobile%2Findex%3Fnavhide%3D1%26from_spmid%3Dminetab%26order_report_params%3D%257B%2522exp_group_tag%2522%253A%2522def%2522%2C%2522exp_tag%2522%253A%2522def%2522%2C%2522material_type%2522%253A%25223%2522%2C%2522position_id%2522%253A%25223%2522%2C%2522request_id%2522%253A%25222597b326d9a6b3f46b7d32c8016483d2%2522%2C%2522tips_id%2522%253A%252220003%2522%2C%2522unit_id%2522%253A%25228207%2522%2C%2522vip_status%2522%253A%25220%2522%2C%2522vip_type%2522%253A%25221%2522%257D%26source_from%3Dmain.my-information.vip-entrance.new.click";
        //     String decode = deCode(ss);
        //     System.out.println(decode);
        //     System.out.println(deCode(deCode(ss)));
        // } catch (UnsupportedEncodingException e) {
        //     e.printStackTrace();
        // }


            String encodedUrl = "https%3A%2F%2Fm.bilibili.com%2Fdoria%2Fcelebrate-2023.html%3Fnavhide%3D1%26msource%3Dskt_03%26order_report_params%3D%257B%250A%2520%2520%2522exp_tag%2522%2520%3A%2520%2522def%2522%2C%250A%2520%2520%2522from_spmid%2522%2520%3A%2520%2522search.search-result.0.0%257Csearch.search-result.0.0%2522%2C%250A%2520%2520%2522tips_id%2522%2520%3A%2520%252219689%2522%2C%250A%2520%2520%2522from_out_spmid%2522%2520%3A%2520%2522search.search-result.0.0%2522%2C%250A%2520%2520%2522vip_type%2522%2520%3A%2520%25220%2522%2C%250A%2520%2520%2522season_id%2522%2520%3A%2520%252239871%2522%2C%250A%2520%2520%2522season_type%2522%2520%3A%2520%25223%2522%2C%250A%2520%2520%2522ep_id%2522%2520%3A%2520%2522511805%2522%2C%250A%2520%2520%2522button%2522%2520%3A%2520%2522vip%2522%2C%250A%2520%2520%2522vip_status%2522%2520%3A%2520%25220%2522%2C%250A%2520%2520%2522request_id%2522%2520%3A%2520%2522293b85e3bb5df4d7406783a6eb648420%2522%2C%250A%2520%2520%2522material_type%2522%2520%3A%2520%25223%2522%2C%250A%2520%2520%2522ep";

            // 解码URL
            String decodedUrl = "";
            try {
                decodedUrl = URLDecoder.decode(encodedUrl, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // 定义正则表达式
            String regex = "tips_id\" : \"(\\d+)";

            // 编译正则表达式
            Pattern pattern = Pattern.compile(regex);

            // 创建Matcher对象
            Matcher matcher = pattern.matcher(decodedUrl);

            // 查找匹配的字符串
            if (matcher.find()) {
                // 提取匹配到的结果
                String result = matcher.group(1);

                System.out.println(result);
            }

    }

    /**
     * 解码‘utf-8’
     * @param data 要解码的字符串
     * @return 解码后的字符串
     * @throws UnsupportedEncodingException 转码异常（必须捕获）
     */
    public static String deCode(String data) throws UnsupportedEncodingException {
        // 参数一：要解码的字符串 参数二：指定字符集
        data = URLDecoder.decode(data,"utf-8");
        return data;
    }
    /**
     * 编码‘utf-8’
     * @param data 要编码的字符串
     * @return 编码后的字符串
     * @throws UnsupportedEncodingException 转码异常（必须捕获）
     */
    public static String enCode(String data) throws UnsupportedEncodingException {
        // 参数一：要编码的字符串 参数二：指定字符集
        data = URLEncoder.encode(data,"utf-8");
        return data;
    }
}
