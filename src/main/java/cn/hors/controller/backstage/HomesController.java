package cn.hors.controller.backstage;

import cn.hors.bean.PAccount;
import cn.hors.consts.SystemConst;
import cn.hors.service.PAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/backstage")
public class HomesController implements SystemConst {
    @Resource
    private PAccountService pAccountService;
    @RequestMapping("/exists")
    @ResponseBody
    public Map<String,Object> exists(String md5){
        Map<String,Object> map = new HashMap<>();
        String realPath = null;
        try {
            String serverpath= ResourceUtils.getURL("classpath:static").getPath().replace("%20"," ").replace('/', '\\');
            realPath=serverpath.substring(1,serverpath.indexOf("\\target"))+"\\src\\main\\resources\\static\\hors\\images";//从路径字符串中取出工程路径
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        map.put("exists", Files.exists(Paths.get(realPath).resolve(md5)));
        return map;
    }
    /**
     *
     * @param file  参数注解的name就是前端的name
     * @return
     */
    @PostMapping("/fileUpload")
    public String fileUpload(@RequestPart("file") MultipartFile file, PAccount pAccount, SessionStatus status) throws FileNotFoundException {
        String serverpath= ResourceUtils.getURL("classpath:static").getPath().replace("%20"," ").replace('/', '\\');
        String realPath=serverpath.substring(1,serverpath.indexOf("\\target"))+"\\src\\main\\resources\\static\\hors\\images";//从路径字符串中取出工程路径
        System.out.println("realPath = " + realPath);
        Path path = Paths.get(realPath);
        try {
            if(!Files.exists(path)){
                Files.createDirectories(path);
            }
            //得到上传文件的实际名称
            String fileName = file.getOriginalFilename();
            pAccount.setHead(fileName);
            System.out.println(fileName);
            //String fileName = file.getOriginalFilename();
            //path.resolve(fileName) 得到完整的file路径
            file.transferTo(path.resolve(fileName));
            pAccountService.updateHead(pAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        status.setComplete();
        return "backstage/logins";
    }
    //fileUpload
    @GetMapping("/loginout")
    public String loginOut(SessionStatus status){
        //清除session
        status.setComplete();
        return "backstage/logins";
    }
    @GetMapping("/index")
    @PreAuthorize("hasAuthority('ir')")
    public String home(){
        System.out.println(1);
        return "backstage/index";
    }
    //防止二次请求非法进去
    @GetMapping
    public String homes(){
        return "backstage/logins";
    }
    @GetMapping("/edit")
    public String edit(){
        return "backstage/edit";
    }
}
//F:\workpace\Hors\src\main\resources\static\layui\image\QQ图片20200523203655.jpg
