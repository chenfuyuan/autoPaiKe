package com.ass.manager.service.autoPaike;

import com.ass.mapper.TClassroomMapper;
import com.ass.mapper.TCourseMapper;
import com.ass.pojo.TClassroom;
import com.ass.pojo.TReady;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 解决冲突
 */
@Component
public class ResolveConflict {
    @Autowired
    private TCourseMapper tCourseMapper;
    @Autowired
    private TClassroomMapper tClassroomMapper;

    private ArrayList<String> use_classrooms;

    private List<TClassroom> classrooms;


    /*解决冲突*/
    public void conflict(int[][][] genes, List<TClassroom> classrooms) {
        this.classrooms = classrooms;
        int time_size = genes[0].length;    //时间段数量
        int class_size = genes.length;    //班级总数
        for (int time = 0; time < time_size; time++) {    //通过时间段遍历
            use_classrooms = new ArrayList<>();    //统计该时段使用过的教室
            for (int i = 0; i < class_size; i++) {    //遍历该时间段的所有班级课表
                /*获取信息*/
                int time_id_01 = genes[i][time][0];
                int course_id_01 = genes[i][time][1];
                int teacher_id_01 = genes[i][time][2];
                int classroom_id_01 = genes[i][time][3];
                TReady tReady01 = new TReady();
                tReady01.setTime(String.valueOf(time_id_01));
                tReady01.setTeacherid(String.valueOf(teacher_id_01));
                tReady01.setCourseid(course_id_01);
                tReady01.setClassroomid(classroom_id_01);
                /*当该班级该时间段有课程时进行冲突检测*/
                if (time_id_01 != 0) {    //当该时间段该班级有课时进行检测
                    /*解决教室冲突*/
                    if (!use_classrooms.contains(classroom_id_01 + "")) {
                        use_classrooms.add(classroom_id_01 + "");
                    } else {
                        /*解决教室冲突*/
                        if (classroomConflict(tReady01, genes, i)) {

                        }
                    }
                    /*与其他班级做比较，解决教师冲突*/
                    for (int j = i + 1; j < class_size; j++) {    //与其他班级进行比较
                        /*获取信息*/
                        int time_id_02 = genes[j][time][0];
                        int course_id_02 = genes[j][time][1];
                        int teacher_id_02 = genes[j][time][2];
                        int classroom_id_02 = genes[j][time][3];
                        TReady tReady02 = new TReady();
                        tReady02.setTime(String.valueOf(time_id_02));
                        tReady02.setTeacherid(String.valueOf(teacher_id_02));
                        tReady02.setCourseid(course_id_02);
                        tReady02.setClassroomid(classroom_id_02);
                        if (teacherConflict(tReady01, tReady02, genes, i)) {
                            continue;
                        }

                    }

                }
            }
        }
    }


    /**
     * 解决教室冲突
     *
     * @param tReady01 课表1
     * @param genes    基因序列
     * @param i        班级
     * @return true代表进行调课
     */
    private boolean classroomConflict(TReady tReady01, int[][][] genes, int i) {
        //System.out.println("解决教室冲突");
        String type = getClassRoomType(tReady01.getClassroomid());
        /*切换教室*/
        int time = Integer.parseInt(tReady01.getTime()) - 1;
        int t = 0;
        while (true) {
            int rNumber = GAUtils.random(classrooms.size());
            int new_classroom_id = classrooms.get(rNumber).getId();

            /*循环一定数量时，未找到合适教室时，进行调课处理*/
            t++;
            if (t > 4 * classrooms.size()) {
                    //System.out.println("教室不足，调课");
                changeReady(tReady01, genes, i);
//                    System.out.println("教室不足，调课成功");
                return true;
            }

            /*教室类型匹配*/
            if (!classrooms.get(rNumber).getType().equals(type)) {
                continue;
            }
            /*如果随机选到的教室未使用，则换教室上课*/
            if (!use_classrooms.contains(new_classroom_id + "")) {
//                System.out.println("换教室成功");
                //System.out.println("更换教室为"+new_classroom_id);
                use_classrooms.add(new_classroom_id + "");
                tReady01.setClassroomid(new_classroom_id);

                genes[i][time][3] = new_classroom_id;
                return true;
            }
        }
    }


    private String getClassRoomType(Integer classroomid) {
        return tClassroomMapper.selectByPrimaryKey(classroomid).getType();
    }

    /**
     * 解决教师冲突
     *
     * @param tReady01 课表1
     * @param tReady02 课表2
     * @param genes    基因序列
     * @param i        班级
     * @return
     */
    private boolean teacherConflict(TReady tReady01, TReady tReady02, int[][][] genes, int i) {

        boolean have = false;
        if (tReady01.getTeacherid().equals(tReady02.getTeacherid())) {
            //System.out.println("解决教师冲突");
            have = true;
            /*调课*/
            changeReady(tReady01, genes, i);
//            System.out.println("教师冲突解决");
        }


        return have;
    }

    /**
     * 调课
     *
     * @param tReady01 原始课表
     * @param genes    全校课表
     * @param i        班级
     */
    private void changeReady(TReady tReady01, int[][][] genes, int i) {
//        System.out.println("调课");
        /*随机生成一个时间段*/
        int rNumber = -1;
        int time = Integer.parseInt(tReady01.getTime()) - 1;
        int t = 0;
        while (true) {
            t++;
            if (t > 100) {
                System.out.println("调课失败");
                return;
            }

            rNumber = GAUtils.random(25);    //生成时间段
            /*判断该时间段是否符合条件*/
            if (genes[i][rNumber][0] != 0) {
                continue;
            }
            boolean bol = tReadyConflict(tReady01, rNumber, genes, i);
            /*判断如果将课程移动到该时间段是否会冲突*/

            if (bol) {
                continue;
            } else {
                break;
            }
        }
        //System.out.println("调课：由"+(time+1)+"调至"+(rNumber+1));
        tReady01.setTime(String.valueOf(rNumber+1));
        use_classrooms.remove(genes[i][time][3] + "");
        genes[i][rNumber] = genes[i][time];
        genes[i][time] = new int[4];
        genes[i][rNumber][0] = rNumber + 1;
//        System.out.println("调课成功");
    }

    /**
     * 判断调整后的时间段是否有冲突
     *
     * @param tReady  课表
     * @param rNumber 调整后的时间段
     * @param genes   全校课表
     * @param i
     * @return
     */
    private boolean tReadyConflict(TReady tReady, int rNumber, int[][][] genes, int i) {
        int time = Integer.parseInt(tReady.getTime()) - 1;
        ArrayList<String> t_use_classroom = new ArrayList<>();
        /*判断教师是否冲突*/
        for (int k = 0; k < genes.length; k++) {
            /*判断教师是否冲突*/
            if (genes[k][rNumber][0] != 0) {
                /*统计该时间段的教室使用情况*/
                t_use_classroom.add(genes[k][time][3] + "");
                //System.out.println("teacher_01 = " +tReady.getTeacherid() + "=?" +k+"班"+(rNumber+1)+"时的老师"+genes[k][rNumber][2] +"?"+(Integer.parseInt(tReady.getTeacherid()) == genes[k][rNumber][2]));
                if (Integer.parseInt(tReady.getTeacherid()) == genes[k][rNumber][2]) {
                    return true;
                }
            }
        }
        /*判断教室是否冲突，冲突时更换教室*/
        if (t_use_classroom.contains(tReady.getClassroomid())) {
            int t = 0;
            /*更换教室*/
            while (true) {
                t++;
                if (t > 4 * classrooms.size()) {    //如果教室都被占用，则进行调课
                    return true;
                }
                /*生成随机教室*/
                int random = GAUtils.random(classrooms.size());
                TClassroom classroom = classrooms.get(random);
                int new_classroom_id = classroom.getId();
                String type = getClassRoomType(tReady.getCourseid());
                /*教室类型不符合时*/
                if (!classroom.getType().equals(type)) {
                    continue;
                }
                /*如果教室未使用，则更换上课地点*/
                if (!t_use_classroom.contains(new_classroom_id + "")) {
                    //System.out.println("更换教室为"+new_classroom_id);
                    tReady.setClassroomid(new_classroom_id);
                    genes[i][time][3] = new_classroom_id;
                    return false;
                }

            }

        }
        return false;
    }

}
