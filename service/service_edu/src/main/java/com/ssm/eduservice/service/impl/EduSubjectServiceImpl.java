package com.ssm.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssm.eduservice.entity.EduSubject;
import com.ssm.eduservice.entity.excel.SubjectData;
import com.ssm.eduservice.entity.subject.OneSubject;
import com.ssm.eduservice.entity.subject.TwoSubject;
import com.ssm.eduservice.listenter.SubjectExcelListenter;
import com.ssm.eduservice.mapper.EduSubjectMapper;
import com.ssm.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-08
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListenter(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //查询所有的一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectsList = baseMapper.selectList(wrapperOne);

        //查询所有的二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id","0");
        List<EduSubject> twoSubjectsList = baseMapper.selectList(wrapperTwo);

        //创建list集合,用于存储最终封装数据
        List<OneSubject> finalSubject = new ArrayList<>();
        for (int i = 0; i <oneSubjectsList.size() ; i++) {
            EduSubject eduSubject = oneSubjectsList.get(i);
            OneSubject oneSubject = new OneSubject();
            // oneSubject.setId(eduSubject.getId());
            // oneSubject.setTitle(eduSubject.getTitle());
            //效果同上哦!
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubject.add(oneSubject);

            List<TwoSubject> twoFinalSubject = new ArrayList<>();
            for (int j = 0; j <twoSubjectsList.size(); j++) {
                EduSubject tSubject = twoSubjectsList.get(j);
                if (tSubject.getParentId().equals(eduSubject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubject.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubject);
        }
        return finalSubject;
    }
}
