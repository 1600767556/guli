package com.ssm.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/11/8 15:05
 */
public class TestEasyExcel {
    public static void main(String[] args) {
       // String fileName = "D:\\Users\\shaoshao\\Desktop\\write.xlsx";
       // EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());


        //实现读操作
        String fileName = "D:\\Users\\shaoshao\\Desktop\\write.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ExcelListent()).sheet().doRead();
    }
    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setName("ssm"+i);
            list.add(demoData);
        }
        return list;
    }
}
