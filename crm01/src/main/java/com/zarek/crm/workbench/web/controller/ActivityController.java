package com.zarek.crm.workbench.web.controller;

import com.zarek.crm.settings.domain.User;
import com.zarek.crm.settings.service.UserService;
import com.zarek.crm.settings.service.impl.UserServiceImpl;
import com.zarek.crm.utils.DateTimeUtil;
import com.zarek.crm.utils.PrintJson;
import com.zarek.crm.utils.ServiceFactory;
import com.zarek.crm.utils.UUIDUtil;
import com.zarek.crm.workbench.domain.Activity;
import com.zarek.crm.workbench.service.ActivityService;
import com.zarek.crm.workbench.service.impl.ActivityServiceImpl;
import com.zarek.crm.workbench.vo.PaginationVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到市场活动控制器");

        String path = request.getServletPath();
//        System.out.println(request.getContextPath());
        System.out.println(path);

        if("/workbench/activity/getUserList.do".equals(path)){
            getUserList(request,response);
        }else if("/workbench/activity/save.do".equals(path)){
            save(request,response);
        }else if("/workbench/activity/pageList.do".equals(path)){
            pageList(request,response);
        }


    }

    //
    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到查询市场活动信息列表的操作(结合条件查询以及分页查询)");
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        //略过的记录数
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.parseInt(pageNoStr);
        //每页展示的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        //计算出略过的记录数
        int skipCount = (pageNo - 1)*pageSize;
        //原材料让控制器去拿，干活需要让业务层去干活，
        //控制层就是获取前端的数据，或者把数据返回前端的层；业务层就是对获取的数据进行加工处理的层

        //把要传给dao层的数据装入一个Map中
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        /*
            前端需要：
                市场活动信息表
                查询的总条数

                业务层拿到了以上两项，如何放回
                使用vo或者map
                map:
                    map.put("total",total)
                    map.put("dataList",dataList)
                    PrintJSON map--->json
               vo:
               pagination<T>
                private int total;
                private List<T> dataList;

                PaginationVO<Activity> vo = new PaginationVO<Activity>();
                vo.setTotal(total);
                vo.setDataList(dataList);
                PrintJSON vo ----> json
                将来分页查询，每个模块都有，所以我们选择使用一个通用vo，操作起来比较方便

         */
         PaginationVO<Activity> vo = as.pageList(map);

         PrintJson.printJsonObj(response,vo);

    }

    //执行市场活动的添加操作
    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动的添加操作");
        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        //创建时间，当前系统时间
        String createTime = DateTimeUtil.getSysTime();
        //创建人：当前登录的用户
        String createBy = ((User)request.getSession().getAttribute("user")).getName();

        Activity a = new Activity();
        a.setId(id);
        a.setCost(id);
        a.setStartDate(startDate);
        a.setOwner(owner);
        a.setName(name);
        a.setEndDate(endDate);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);


        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.save(a);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得用户信息列表");

        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> uList = userService.getUserList();

        PrintJson.printJsonObj(response,uList);

    }
    
    
}
