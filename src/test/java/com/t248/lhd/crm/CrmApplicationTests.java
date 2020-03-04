package com.t248.lhd.crm;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.t248.lhd.crm.com.ucpaas.restDemo.client.JsonReqClient;
import com.t248.lhd.crm.entity.CusService;
import com.t248.lhd.crm.entity.OrdersSolr;
import com.t248.lhd.crm.entity.SolrTest;
import com.t248.lhd.crm.entity.User;
import com.t248.lhd.crm.repository.CusServiceRepository;
import com.t248.lhd.crm.service.MailService;
import com.t248.lhd.crm.service.TokenService;
import com.t248.lhd.crm.service.impl.RamJob;
import com.t248.lhd.crm.tools.RedisUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import static org.quartz.JobBuilder.newJob;

@SpringBootTest
class CrmApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void set() {
        redisTemplate.opsForValue().set("myKey", "myValue");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }

    @Test
    public void a() {
        redisUtil.set("1", "胖哥");
    }

    @Test
    public void aa() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        System.out.println("认证状态:" + subject.isAuthenticated());
        boolean ishasRole = subject.hasRole("role1");
        System.out.println("单个角色判断:" + ishasRole);
        boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1", "role2"));
        System.out.println("多个角色判断:" + hasAllRoles);
        boolean isPermitted = subject.isPermitted("user:L05");
        System.out.println("单个权限判断:" + isPermitted);
        boolean isPermittedAll = subject.isPermittedAll("user:L05", "user:L06");
        System.out.println("多个权限判断:" + isPermittedAll);
    }

    @Test
    public void aaa() {
        Md5Hash md5Hash = new Md5Hash("1", "salt", 1);
        System.out.println(md5Hash.toString());
        System.out.println("f51703256a38e6bab3d9410a070c32ea");
    }

    @Resource
    private MailService mailService;

    @Test
    public void testMail() throws Exception {
        String to = "895179246@qq.com";
        String subject = "你本次登陆的验证码为:";
        Random random = new Random();
        int num = random.nextInt(1000);

        String content = num + "";
        try {
            mailService.sendSimpleMail(to, subject, content);
            System.out.println("成功了");

        } catch (MailException e) {
            System.out.println("失败了");
            e.printStackTrace();
        }

    }

    public int testMail1() throws Exception {
        String to = "197207598@qq.com";
        String subject = "你本次登陆的验证码为:";
        Random random = new Random();
        int num = random.nextInt(1000);

        String content = num + "";
        try {
            mailService.sendSimpleMail(to, subject, content);
            System.out.println("成功了");

        } catch (MailException e) {
            System.out.println("失败了");
            e.printStackTrace();
        }
        return num;
    }

    @Test
    public void dasa() throws Exception {
        int a = testMail1();

        System.out.println(a);
        System.out.println("请输入验证码:");
        int num = 555;
        if (a != num) {
            System.out.println("错误!");
        } else {
            System.out.println("成功!");
        }
    }

    @Test
    public void test2() {
        String to = "1366448219@qq.com";
        String subject = "今晚要加班，不用等我了";
        String rscId = "img110";
        String content = "<html><body><img width='250px' src=\'cid:" + rscId + "\'></body></html>";
        // 此处为linux系统路径
        String imgPath = "D:/桌面/111.jpg";
        try {
            mailService.sendInlineResourceMail(to, subject, content, imgPath, rscId);
            System.out.println("成功了");
        } catch (MessagingException e) {
            System.out.println("失败了");
            e.printStackTrace();
        }
    }

    @Test
    public void aaaaaa() {
        for (int i = 0; i > 5; i++) {
            test2();
        }

    }

    @Resource
    CusServiceRepository cusServiceRepository;

    @Test
    public void test11() {
        List<CusService> list = cusServiceRepository.findAll();
        for (CusService cusService : list) {
            System.out.println(cusService.getSvrCustName() + "===" + cusService.getCustomer().getCustName());
        }
    }

    @Test
    public void aaaaa() {
        // 定义二维码的参数
        int width = 300; // 图片宽度
        int height = 300; // 图片高度
        String format = "jpg"; // 图片格式  如果是png类型，logo图变成黑白的，
        String content = "aaa!!!";// 二维码内容

        // 1.定义HashMap hints
        HashMap hints = new HashMap();
        // 2.hints调用put函数设置字符集、间距以及纠错度为M
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//纠错等级【L，M，Q，H】
        hints.put(EncodeHintType.MARGIN, 2);
        // 生成二维码
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            // 3.最后用MultiformatWriter函数类调用echoed函数并返回一个值 然后写入文件
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 这里路径后面的img.png不可省略，前面是自己选取生成的图片地址
            Path file = new File("C:\\Users\\马运动\\Pictures\\Camera Roll\\img.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            //*************添加logo*****************
            //读取二维码图片
            BufferedImage bufferedImage = ImageIO.read(new File(file.toString()));
            //获取画笔
            Graphics2D graphics = bufferedImage.createGraphics();
            //读取logo图片
            BufferedImage logo = ImageIO.read(new File("C:\\Users\\马运动\\Pictures\\Camera Roll\\22.jpg"));
            //设置二维码大小，太大了会覆盖二维码，此处为20%
            int logoWidth = logo.getWidth() > bufferedImage.getWidth() * 2 / 10 ? (bufferedImage.getWidth() * 2 / 10) : logo.getWidth();
            int logoHeight = logo.getHeight() > bufferedImage.getHeight() * 2 / 10 ? (bufferedImage.getHeight() * 2 / 10) : logo.getHeight();
            //设置logo图片放置的位置，中心
            int x = (bufferedImage.getWidth() - logoWidth) / 2;
            int y = (bufferedImage.getHeight() - logoHeight) / 2;
            //开始合并并绘制图片
            graphics.drawImage(logo, x, y, logoWidth, logoHeight, null);
            graphics.drawRoundRect(x, y, logoWidth, logoHeight, 15, 15);
            //logob边框大小
            graphics.setStroke(new BasicStroke(2));
            //logo边框颜色
            graphics.setColor(Color.white);
            graphics.drawRect(x, y, logoWidth, logoHeight);
            graphics.dispose();
            logo.flush();
            bufferedImage.flush();
            ImageIO.write(bufferedImage, format, new File("C:\\Users\\马运动\\Pictures\\Camera Roll\\img2.png"));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 生成excel
     */
    @Test
    public void exportExcel() {
        Map<String, Integer> accts = new HashMap<String, Integer>() {
            {
                put("123456", 125);
                put("123451", 121);
                put("123457", 124);
                put("123459", 122);

            }
        };

        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("FXT");
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cellOne = row1.createCell(0);
        // 设置单元格内容
        cellOne.setCellValue("账号");
        HSSFCell cellTwo = row1.createCell(1);
        // 设置单元格内容
        cellTwo.setCellValue("金额");

        //行数
        int rowNum = 1;
        //遍历hashmap
        Iterator iterator = accts.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            //创建一行行记录
            rowNum++;
            // 在sheet里创建下一行
            HSSFRow newRow = sheet.createRow(rowNum);
            // 创建单元格并设置单元格内容
            newRow.createCell(0).setCellValue((String) key);
            newRow.createCell(1).setCellValue((Integer) val);

        }

        // 第六步，将文件存到指定位置
        try {
            String path = "D:/桌面/b.xlsx";
            File file = new File(path);
            //如果已经存在则删除
            if (file.exists()) {
                file.delete();
            }
            //检查父包是否存在
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            //创建文件
            file.createNewFile();
            FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            String str = "导出成功！";
            System.out.println(str);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            String str1 = "导出失败！";
            System.out.println(str1);
        }
        // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        //sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));


    }
    FileOutputStream out;

 /*@Test
public void test() throws DocumentException, MalformedURLException, IOException {

out = new FileOutputStream("D:\\Demo\\春.pdf");

// 设置页面的属性 

Rectangle tRectangle = new Rectangle(PageSize.A4); // 页面大小 

 tRectangle.setBackgroundColor(BaseColor.WHITE); // 页面背景色 

tRectangle.setBorder(1220);// 边框 

tRectangle.setBorderColor(BaseColor.BLUE);// 边框颜色 

tRectangle.setBorderWidth(244.2f);// 边框宽度 
Document doc = new Document(tRectangle);// 定义文档  

 doc = new Document(tRectangle.rotate());// 横向打印 

PdfWriter writer = PdfWriter.getInstance(doc, out);// 书写器 

 writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);//版本(默认1.4) 

 //设置PDF文档属性 

 doc.addTitle("春");// 标题 

 doc.addAuthor("朱自清");// 作者 

doc.addSubject("春天的脚步近了");// 主题 

 doc.addKeywords("春");// 关键字 

 doc.addCreator("田野上的风筝");// 创建者 

 Paragraph tParagraph = new Paragraph("春", getChineseFont());

tParagraph.setAlignment(Element.ALIGN_JUSTIFIED);// 对齐方式 

        Paragraph tParagraphs = new Paragraph("盼望着，盼望着，东风来了春天的脚步近了。一切都像刚睡醒的样子，欣欣然张开了眼。山朗润起来了，水涨起来了，太阳的脸红起来了", getChineseFont());

 tParagraph.setAlignment(Element.ALIGN_CENTER);// 居中

 tParagraph.setIndentationLeft(12);// 左缩进 

tParagraph.setIndentationRight(12);// 右缩进 

 tParagraph.setFirstLineIndent(24);// 首行缩进 

tParagraph.setLeading(20f);// 行间距 

 tParagraph.setSpacingBefore(5f);// 设置上空白 

 tParagraph.setSpacingAfter(10f);// 设置段落下空白

doc.setMargins(10, 20, 30, 40);// 页边空白 

Image img = Image.getInstance("D:\\Demo\\spring.png");

 img.setAlignment(Image.MIDDLE); //设置图片居中

 img.setBorder(Image.BOX);

img.setBorderWidth(10);

 img.setBorderColor(BaseColor.WHITE);

 img.scaleToFit(900, 350);// 设置图片大小 

 doc.open();// 打开文档 

///  doc.add(tRectangle);

 doc.add(tParagraph); //添加段落

 doc.add(tParagraphs);

doc.add(img); //添加img

 //  doc.newPage();    //添加下一页 

//  writer.setPageEmpty(true);// fasle-显示空内容的页;true-不会显示 

doc.close(); //记得关闭document

 }
    public Font getChineseFont() {

BaseFont simpChinese;

Font ChineseFont = null;

try {

 simpChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

 ChineseFont = new Font(simpChinese, 12, Font.NORMAL, BaseColor.BLUE);

} catch (DocumentException e) {

e.printStackTrace();

 } catch (IOException e) {

 e.printStackTrace();

 }

return ChineseFont; 

 } */
    @Test
    public void sendMsg(){
        String sid = "e21f93b47d21f012791f820c6732eaa0";//用户的账号唯一标识“Account Sid”
        String token = "1a9df3660675c869f4c30fff9e336e52";//用户密钥“Auth Token”
        String appid = "bcfe4a7c79254ead86949019d4d8ba49";//创建应用时系统分配的唯一标示
        String templateid = "494081";//可在后台短信产品→选择接入的应用→短信模板-模板ID，查看该模板ID
        String param = generateWord();//模板中的替换参数（验证码）
        String mobile = "13047257597";//接收的单个手机号，暂仅支持国内号码
        String uid = "";//用户透传ID，随状态报告返回

        try {
            String result=new JsonReqClient().sendSms(sid, token, appid, templateid, param, mobile, uid);
            System.out.println("Response content is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 产生随机的6位数字字符串
     */
    private static String generateWord() {
        int length = 6;
        String[] beforeShuffle = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        return afterShuffle.substring(2, 2 + length);
    }
    @Autowired
    SolrClient solrClient;
    @Test
    public void findUser() throws IOException, SolrServerException {
        List<SolrTest> userList = new ArrayList<SolrTest>();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        try {
            QueryResponse queryResponse = solrClient.query(solrQuery);
            if (queryResponse != null) {
                userList = queryResponse.getBeans(SolrTest.class);
                for (SolrTest u : userList) {
                    System.out.println(u.getAPKName() + u.getSoftwareName());
                }
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void findorders() throws IOException, SolrServerException {
        List<OrdersSolr> userList = new ArrayList<OrdersSolr>();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.setStart(0);
        solrQuery.setRows(3);
        try {
            QueryResponse queryResponse = solrClient.query(solrQuery);

            if (queryResponse != null) {
                userList = queryResponse.getBeans(OrdersSolr.class);
                for (OrdersSolr u : userList) {
                    System.out.println(u.getOdr_id()+u.getOdr_customer_no()+u.getOdr_addr());
                }
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Resource
    TokenService tokenService;
    @Test
    public void redisTest() throws Exception {
        User user=new User();
        user.setUsrName("admin");
        user.setUsrPassword("123");
        String token=tokenService.generateToken("",user.getUsrName());
        tokenService.save(token,user);
    }
}



