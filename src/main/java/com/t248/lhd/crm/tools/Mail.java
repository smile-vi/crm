package com.t248.lhd.crm.tools;

import com.t248.lhd.crm.service.MailService;
import org.springframework.mail.MailException;

import javax.annotation.Resource;
import java.util.Random;
import java.util.Scanner;

public class Mail {
    @Resource
    private MailService mailService;
    public  int testMail() throws  Exception {
        String to = "895179246@qq.com";
        String subject ="你本次登陆的验证码为:";
        Random random=new Random();
        int num = random.nextInt(1000);

        String content=num+"";
        try {
            mailService.sendSimpleMail(to,subject,content);
            System.out.println("成功了");

        }catch (MailException e){
            System.out.println("失败了");
            e.printStackTrace();
        }
        return num;
    }
    public void num(int num){

    }

    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);

    }
}
