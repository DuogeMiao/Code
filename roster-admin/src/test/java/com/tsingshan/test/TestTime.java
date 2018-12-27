package com.tsingshan.test;

import com.tsingshan.infomana.service.IEmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTime {


    @Test
    public void test() {
//        Date date=new Date();
//        System.out.println(date);
//        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat df = DateFormat.getTimeInstance();//只显示出时分秒
//        //String sDate=sdf.format(date);
//        String format = df.format(date);
//        String temp1 = "00:00:00";
//        String temp = "00:00:00";
//        if (temp.compareTo(temp1) != 0 ) {
//            System.out.println("temp 小于 format");
//        }
//       // System.out.println(sDate);
//        System.out.println(format);
    }


    @Test
    public void insert() {
//        int[] a = {314, 298, 508, 123, 486,145};
//        for (int i = 1; i < a.length; i++)     //n-1此扫描，依次向前插入n-1个元素
//        {
//            int temp = a[i];       //每趟将a[i]插入到前面的排序子序列中
//            int j;
//            for (j = i - 1; j >= 0 && temp < a[j]; j--) {
//                a[j + 1] = a[j];  //将前面较大的元素向后移动
//            }
//            a[j + 1] = temp;      //temp值到达插入位置
//        }

 //       System.out.println(k);

        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8 };
        int s0, s1, s2;
        s0 = s1 = s2 = 0;
        for (int i = 0; i < 8; i++)
        {
            switch (a[i] % 3)
            {
                case 0: s0 += a[i]; break;
                case 1: s1 += a[i]; break;
                case 2: s2 += a[i]; break;
            }
        }
        System.out.println(s0 + " " + s1 + " " + s2);


    }
}
