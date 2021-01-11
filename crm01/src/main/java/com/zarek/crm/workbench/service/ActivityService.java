package com.zarek.crm.workbench.service;

import com.zarek.crm.workbench.domain.Activity;
import com.zarek.crm.workbench.vo.PaginationVO;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity a);

    PaginationVO<Activity> pageList(Map<String, Object> map);
}
