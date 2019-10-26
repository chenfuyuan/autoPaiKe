package com.ass.manager.service.autoPaike;

import com.ass.mapper.*;
import com.ass.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * 一个班级为一个个体
 * 通过int的二维数组，存储一个班级的周课表
 * 行为时间段
 * 一列为一个课程信息，第一列为时间，第二列课程编号，第三列为教师编号，第四列为教室编号
 */
@Component
public class Chromsome {
    private int genes[][];    //基因序列
    /*一天分为5个时间段，早2午2晚1，周一至周五*/
    private static int time_size = 25;

    private int class_id;

    private int department_id;
    @Autowired
    private TCourseMapper tCourseMapper;
    @Autowired
    private TTeacherMapper tTeacherMapper;
    @Autowired
    private TCourseTeacherMapper tCourseTeacherMapper;
    @Autowired
    private TClassroomMapper tClassroomMapper;
    @Autowired
    private TClassMapper tClassMapper;

    /*该班级所有的课程*/
    private List<TCourse> courses;    //该专业所有课程数
    private int course_size;    //课程数量
    private Map<Integer, Integer> course_teacher_map = new HashMap<>();
    private Map<Integer, Integer> course_weektime_map = new HashMap<>();
    private int totalWeekTime;    //总周课时
    /*学校的教室数*/
    private List<TClassroom> common_classrooms;    //普通教室
    private int common_classroom_size;
    private List<TClassroom> speacial_classrooms;    //特殊的教室
    private int speacial_classroom_size;
    private List<TClassroom> sport_classrooms;    //操场
    private int sport_classroom_size;
    private ArrayList<Integer> times;

    /*班级周课表适应度*/
    private int score;
    private int timeScore;    //节次适应度得分
    private int timeSpaceScore;    //组合度适应度得分
    private Map<Integer, Integer> course_timeSpace;    //时间间距
//    private double[] timeWeight = {0.75, 0.88, 0.75, 0.55, 0.42, 0.89, 0.99, 0.72, 0.6, 0.39, 0.92, 0.97, 0.68, 0.57, 0.37, 0.88, 0.95, 0.65, 0.52, 0.3, 0.75, 0.82, 0.48, 0.25, 0.15};

    /*理论课时间期望值*/
    private int[] timeWeight = {4, 5, 4, 3, 3, 5, 5, 4, 4, 2, 5, 5, 4, 3, 2, 5, 5, 3, 3, 2, 4, 3, 2, 1};
    /*体育课时间期望值*/
    private int[] sportTimeWeight = {0, 4, 0, 4, 0, 0, 4, 0, 4, 0, 0, 4, 0, 4, 0, 0, 4, 0, 4, 0, 0, 4, 0, 4, 0};
    /*时间差期望值*/

    private int[] timeSpaceWeight = {0, 0, 0, 4, 4, 2, 2, 5, 5, 3, 3, 5, 5, 4, 4, 3, 3, 2, 2, 1};
    private Map<Integer, Integer> time_Space_Map;


    public void statisticsScore() {
        score = 0;
        statistics();
        //Score = 节点适应度平均得分 + 时间间隔平均得分
        score = timeScore / course_size + timeSpaceScore / course_size;
    }

    /*统计适应度得分*/
    private void statistics() {
        time_Space_Map = new HashMap<>();
        timeSpaceScore = 0;    //初始化时间间隔得分
        timeScore = 0;    //初始化节次适应度得分
        for (int i = 0; i < genes.length; i++) {
            if (genes[i][0] != 0) {
                timeScore += timeWeight[i];
                int course_id = genes[i][1];
                int time = genes[i][0];
                if (time_Space_Map.get(course_id) == null) {
                    time_Space_Map.put(course_id, time);
                } else {
                    int time2 = time_Space_Map.get(course_id);
                    int timeSpace = Math.abs(time - time2);
                    time_Space_Map.put(course_id, time);
                    timeSpaceScore += timeSpace;
                }
            }
        }
    }


    public void init(int class_id, String groups) {
        this.class_id = class_id;
        //System.out.println(tCourseMapper);
        times = new ArrayList();
        initCourse(groups);
        initClassRoom();
        genes = new int[time_size][4];
        score = 0;
        initgenes();

    }


    /*生成初始基因序列*/
    private void initgenes() {
        while (!courses.isEmpty()) {
            int time = GAUtils.random(25) + 1;
            if (!times.contains(time)) {
                times.add(time);
            } else {
                continue;
            }
            int course_id = getRandomCourse_id();
            int teacher_id = course_teacher_map.get(course_id);
            int classroom_id = getRandomClassroom_id(course_id);
            comGenes(time - 1, time, course_id, teacher_id, classroom_id);
        }
        /*对基因进行排序*/
//        genesSort();
    }

    /**
     * 对基因进行排序
     */
    public void genesSort() {
        GAUtils.genesSort(genes);
    }

    /**
     * 组成一段基因序列 ，代表该时间段上的课程的信息
     * 时间 课程号 教师号 教室号
     *
     * @param index        第几段基因
     * @param time         时间段
     * @param course_id    课程号
     * @param teacher_id   教师号
     * @param classroom_id 教室号
     */
    private void comGenes(int index, int time, int course_id, int teacher_id, int classroom_id) {
        genes[index][0] = time;
        genes[index][1] = course_id;
        genes[index][2] = teacher_id;
        genes[index][3] = classroom_id;
    }

    /**
     * 获取随机的教室
     *
     * @param course_id
     * @return 教室的id
     */
    private int getRandomClassroom_id(int course_id) {
        TCourse tCourse = tCourseMapper.selectByPrimaryKey(course_id);
        String way = tCourse.getWay();
        int classroom_id = 0;
        if (course_weektime_map.get(course_id) == null) {
            switch (way) {
                case "理论":
                    classroom_id = getRandomCommon_Classroom_id();
                    break;
                case "理论+上机":
                    classroom_id = getSpecial_Classroom_id(1);
                    break;
                case "理论+实验":
                    classroom_id = getSpecial_Classroom_id(2);
                    break;
                case "室外":
                    classroom_id = getSpecial_Classroom_id(3);
                default:
                    break;
            }
        }else{
            classroom_id = getRandomCommon_Classroom_id();
        }


        return classroom_id;
    }

    /*随机获取一个普通教室*/
    private int getRandomCommon_Classroom_id() {
        int rNumber = GAUtils.random(common_classroom_size);
        int classroom_id = common_classrooms.get(rNumber).getId();
        return classroom_id;
    }

    /*随机获取一个特殊教室，根据传入的type值选取机房或者实验室*/
    private int getSpecial_Classroom_id(int type) {
        String classroomType = null;
        if (type == 3) {
            int rNumber = GAUtils.random(sport_classroom_size);
            TClassroom tClassroom = sport_classrooms.get(rNumber);
            return tClassroom.getId();
        }
        switch (type) {
            case 1:
                classroomType = "机房";
                break;
            case 2:
                classroomType = "实验室";
                break;
        }
        while (true) {
            {
                int rNumber = GAUtils.random(speacial_classroom_size);
                TClassroom tClassroom = speacial_classrooms.get(rNumber);
                int classroom_id = tClassroom.getId();
                if (tClassroom.getType().equals(classroomType)) {
                    return classroom_id;
                }
            }
        }

    }


    /**
     * 获取随机的课程
     *
     * @return 随机课程id
     */
    private int getRandomCourse_id() {
        int rNumber = GAUtils.random(courses.size());    //随机数
        int course_id = courses.get(rNumber).getId();    //随机得到的课程号
        int residueweekTime = course_weektime_map.get(course_id) - 1;//剩余未排周课时
        if (residueweekTime == 0) {
            courses.remove(rNumber);
            course_weektime_map.remove(course_id);
        } else {

            course_weektime_map.put(course_id, residueweekTime);
        }
        return course_id;
    }


    /**
     * 获取该专业的所有必修课表
     * 通过department_id 和 type 和 status获取课表
     * @param groups
     */
    private void initCourse(String groups) {
        initDepartment();

        /*添加条件 通过学院id 和 选取必修课type = 1 获取相应的课表*/
        TCourseExample tCourseExample = new TCourseExample();
        TCourseExample.Criteria criteria = tCourseExample.createCriteria();
        criteria.andDepartmentidEqualTo(department_id);
        criteria.andTypeEqualTo(1);
        criteria.andStatusEqualTo(1);
        criteria.andGroupsEqualTo(groups);
        courses = tCourseMapper.selectByExample(tCourseExample);
        course_size = courses.size();

        initCourseTeacher();    //初始化课程和教师映射
        initCOurseWeekTimeMap();    //初始化周课时和课程的映射


    }

    /**
     * 初始化专业id
     */
    private void initDepartment() {
        TClass tClass = tClassMapper.selectByPrimaryKey(String.valueOf(class_id));
        department_id = tClass.getDepartmentid();
    }


    /**
     * 初始化课程的周课时数
     */
    private void initCOurseWeekTimeMap() {
        //System.out.println(courses);

        totalWeekTime = 0;
        for (TCourse tCourse : courses) {
            //System.out.println(tCourse.getName());
            course_weektime_map.put(tCourse.getId(), tCourse.getWeektime());
            totalWeekTime += tCourse.getWeektime();
        }
        //System.out.println("totalWeekTime = " + totalWeekTime);
    }

    /**
     * 初始化课程和任课老师的映射
     */
    private void initCourseTeacher() {
        for (int i = 0; i < course_size; i++) {
            int course_id = courses.
                    get(i).getId();
            /*添加查询条件*/
            TCourseTeacherExample tCourseTeacherExample = new TCourseTeacherExample();
            TCourseTeacherExample.Criteria criteria = tCourseTeacherExample.createCriteria();
            criteria.andCourseidEqualTo(course_id);
            List<TCourseTeacher> tCourseTeachers = tCourseTeacherMapper.selectByExample(tCourseTeacherExample);

            /*通过课程获取任课老师集合，从中随机选择一个老师*/
            int t = (int) (tCourseTeachers.size() * Math.random());
            int teacher_id = Integer.parseInt(tCourseTeachers.get(t).getTeacherid());
            course_teacher_map.put(course_id, teacher_id);
        }
        //System.out.println(course_teacher_map);
    }


    /**
     * 获取全校的教室
     */
    private void initClassRoom() {
        initCommonClassRoom();
        initSpecialClassRoom();
    }

    /*初始化特殊教室*/
    private void initSpecialClassRoom() {
        /*添加查询条件*/
        TClassroomExample tClassroomExample = new TClassroomExample();
        TClassroomExample.Criteria criteria = tClassroomExample.createCriteria();
        criteria.andStatusEqualTo(1);    //判断是否被删除
        criteria.andTypeNotEqualTo("普通教室");
        criteria.andTypeNotEqualTo("操场");
        speacial_classrooms = tClassroomMapper.selectByExample(tClassroomExample);
        speacial_classroom_size = speacial_classrooms.size();

        initSportClassroom();
    }

    /**
     * 初始化操场教室
     */
    private void initSportClassroom() {
        TClassroomExample tClassroomExample = new TClassroomExample();
        TClassroomExample.Criteria criteria = tClassroomExample.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andTypeEqualTo("操场");
        sport_classrooms = tClassroomMapper.selectByExample(tClassroomExample);
        sport_classroom_size = sport_classrooms.size();
    }

    /*初始化普通教室*/
    private void initCommonClassRoom() {
        /*添加查询条件*/
        TClassroomExample tClassroomExample = new TClassroomExample();
        TClassroomExample.Criteria criteria = tClassroomExample.createCriteria();
        criteria.andStatusEqualTo(1);    //判断是否被删除
        criteria.andTypeEqualTo("普通教室");

        common_classrooms = tClassroomMapper.selectByExample(tClassroomExample);
        common_classroom_size = common_classrooms.size();

    }

    public int[][] getGenes() {
        return genes;
    }

    public void setGenes(int[][] genes) {
        this.genes = genes;
    }

    public void display() {
        for (int i = 0; i < genes.length; i++) {
            if (i % 5 == 0) {
                //System.out.println();
            }
            for (int j = 0;j < genes[i].length ; j++) {
//                System.out.print(genes[i][j]);
            }
//            System.out.print("  ");

        }
    }
}
