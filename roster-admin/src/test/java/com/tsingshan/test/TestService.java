package com.tsingshan.test;

import com.tsingshan.TsingShanApplication;
import com.tsingshan.common.base.AjaxResult;
import com.tsingshan.common.utils.ExcelUtil;
import com.tsingshan.common.utils.StringUtils;
import com.tsingshan.infomana.domain.Employee;
import com.tsingshan.infomana.service.IEmployeeService;
import com.tsingshan.system.domain.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TsingShanApplication.class)
public class TestService {


    @Autowired
    private IEmployeeService employeeService;

    @Test
    public void employeeExportTest() {


//        String split = "1,2,3,4";
//        String[] colsNames = {"employeeNo", "employeeName", "identityCard", "birthDate"};
//        String[] params = new String[colsNames.length];
//
//        for (int i = 0; i < colsNames.length; i++) {
//            params[i] = StringUtils.humpToLine2(colsNames[i]);
//        }
//        Integer[] ids = StringUtils.stringConvertInteger(split);
//
//        System.out.println(ids);
//
//
//        List<Employee> stringObjectMapList = employeeService.selectExport(params, ids);
//
//        ExcelUtil<Employee> util = new ExcelUtil<Employee>(Employee.class);
//
//
//        AjaxResult ajaxResult = util.exportExcel(stringObjectMapList, "user", colsNames);
//        System.out.println(ajaxResult.get("msg"));
    }


//    @Test
//    public void test() {
//
//        Employee employee = employeeService.selectEmployeeById(1);
//
////        Date date=new Date();
////        System.out.println(date);
////        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat df = DateFormat.getTimeInstance();//只显示出时分秒
//        //String sDate=sdf.format(date);
//        String format = df.format(employee.getBirthDate());
//        String format1 = df.format(employee.getUpdateTime());
//        System.out.println("@@@@@"+ format);
//        System.out.println("@@@@@"+ format1);
//        String temp1 = "00:00:00";
//        String temp = "00:00:00";
//        if (temp.compareTo(temp1) != 0) {
//            System.out.println("temp 小于 format");
//        }
//
//
//    }
}
