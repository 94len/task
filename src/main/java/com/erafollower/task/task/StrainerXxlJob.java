package com.erafollower.task.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

@Component
@Slf4j
public class StrainerXxlJob {



    @Autowired
    private JavaMailSender mailSender;

    /**
     * 1、简单任务示例（Bean模式）
     */
//    @XxlJob("strainerJobHandler")
//    public ReturnT<String> strainerJobHandler(String param) throws Exception {
//
//        LocalDate today = LocalDate.now();
//        LocalDate targetDate = LocalDate.of(2020,02,11);
//        LocalDate endDate = LocalDate.of(2020,03,11);
//
//
//
////        if(today.isBefore(targetDate)){
////            return ResponseHelper.buildResponseModel("不在日期2020-02-11~2020-03-11范围之内");
////        }else if(today.isAfter(endDate)){
////            return ResponseHelper.buildResponseModel("不在日期2020-02-11~2020-03-11范围之内");
////        }
//
//        // sendEmail
//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//            helper.setFrom("891846581@qq.com");
//            helper.setTo("891846581@qq.com");
//            helper.setSubject("滤网领取提醒");
//
//            String content = "叶脉程序控制中心提醒您，您的空气净化器需要更换滤网了。<br>"
//                    + "滤网领取时间为：2020-02-11~2020-03-11 <br>"
//                    + "请联系相关淘宝客服领取。";
//
//            helper.setText(content,true);
//            mailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            log.error("发送滤网领取提醒发送错误！");
//        }
//        return ReturnT.SUCCESS;
//    }

    /**
     * 4、跨平台Http任务
     */
    @XxlJob("httpJobHandler")
    public ReturnT<String> httpJobHandler(String param) throws Exception {

        // request
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        try {
            // connection
            URL realUrl = new URL(param);
            connection = (HttpURLConnection) realUrl.openConnection();

            // connection setting
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setReadTimeout(5 * 1000);
            connection.setConnectTimeout(3 * 1000);
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept-Charset", "application/json;charset=UTF-8");

            // do connection
            connection.connect();

            //Map<String, List<String>> map = connection.getHeaderFields();

            // valid StatusCode
            int statusCode = connection.getResponseCode();
            if (statusCode != 200) {
                throw new RuntimeException("Http Request StatusCode(" + statusCode + ") Invalid.");
            }

            // result
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            String responseMsg = result.toString();

//            XxlJobLogger.log(responseMsg);
            log.error(responseMsg);
            return ReturnT.SUCCESS;
        } catch (Exception e) {
//            XxlJobLogger.log(e);
            log.error(e.getMessage());
            return ReturnT.FAIL;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }

            } catch (Exception e2) {
//                XxlJobLogger.log(e2);
                log.error(e2.getMessage());
            }
        }

    }

}
