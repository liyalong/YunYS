package com.yunyisheng.app.yunys.base;

import java.util.List;

/**
 * Created by Administrator on 2016-12-28.
 */

public interface PressionListener {
    //授权成功的接口
    void onGranted();
    //授权失败的接口
    void onFailure(List<String> failurePression);
}
