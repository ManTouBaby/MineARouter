package com.hrw.main;

import android.app.Application;

import com.hrw.common.utils.database.MtDBType;
import com.hrw.common.utils.database.MtDBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/10/18 15:58
 * @desc:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        List<Map<String, MtDBType>> maps = new ArrayList<>();
        Map<String, MtDBType> typeMap = new HashMap<>();
        typeMap.put("text_table", MtDBType.TABLE_NAME);
        typeMap.put("id", MtDBType.TABLE_TYPE_PRIMARY);
        typeMap.put("student_name", MtDBType.TABLE_TYPE_TEXT);
        typeMap.put("student_grade", MtDBType.TABLE_TYPE_TEXT);
        typeMap.put("student_sex", MtDBType.TABLE_TYPE_TEXT);
        maps.add(typeMap);
        MtDBHelper.instance(this, "text", maps, 1);
    }
}
