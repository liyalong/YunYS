package com.yunyisheng.app.yunys.net;

import com.yunyisheng.app.yunys.login.service.CompanyService;
import com.yunyisheng.app.yunys.login.service.ShortMessageService;
import com.yunyisheng.app.yunys.login.service.UserService;
import com.yunyisheng.app.yunys.main.service.HomeService;
import com.yunyisheng.app.yunys.project.service.ProjectService;
import com.yunyisheng.app.yunys.schedule.service.ScheduleService;
import com.yunyisheng.app.yunys.userset.service.UserSetService;

import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by liyalong on 2017/12/20.
 */

public class Api {
    //192.168.2.208   172.16.160.67 172.16.26.86 192.168.2.205
    public static final String BASE_PATH = "http://172.16.160.67:8080/fairyland-system/";
    private static UserService userService;
    private static ShortMessageService shortMessageService;
    private static CompanyService companyService;
    private static ProjectService projectService;
    private static UserSetService userSetService;
    private static HomeService homeService;
    private static ScheduleService scheduleService;

    public static UserService userService(){
        if(userService == null){
            synchronized (Api.class){
                if(userService == null){
                    userService = XApi.getInstance().getRetrofit(BASE_PATH,true).create(UserService.class);
                }
            }
        }
        return userService;
    }
    public static ShortMessageService shortMessageService(){
        if(shortMessageService == null){
            synchronized (Api.class){
                if(shortMessageService == null){
                    shortMessageService = XApi.getInstance().getRetrofit(BASE_PATH,true).create(ShortMessageService.class);
                }
            }
        }
        return shortMessageService;
    }
    public static CompanyService companyService(){
        if(companyService == null){
            synchronized (Api.class){
                if(companyService == null){
                    companyService = XApi.getInstance().getRetrofit(BASE_PATH,true).create(CompanyService.class);
                }
            }
        }
        return companyService;
    }

    public static ProjectService projectService(){
        if(projectService == null){
            synchronized (Api.class){
                if(projectService == null){
                    projectService = XApi.getInstance().getRetrofit(BASE_PATH,true).create(ProjectService.class);
                }
            }
        }
        return  projectService;
    }

    public static UserSetService userSetService(){
        if(userSetService == null){
            synchronized (Api.class){
                if(userSetService == null){
                    userSetService = XApi.getInstance().getRetrofit(BASE_PATH,true).create(UserSetService.class);
                }
            }
        }
        return userSetService;
    }

    public static HomeService homeService(){
        if(homeService == null){
            synchronized (Api.class){
                if(homeService == null){
                    homeService = XApi.getInstance().getRetrofit(BASE_PATH,true).create(HomeService.class);
                }
            }
        }
        return homeService;
    }

    public static ScheduleService scheduleService(){
        if(scheduleService == null){
            synchronized (Api.class){
                if(scheduleService == null){
                    scheduleService = XApi.getInstance().getRetrofit(BASE_PATH,true).create(ScheduleService.class);
                }
            }
        }
        return scheduleService;
    }

}
