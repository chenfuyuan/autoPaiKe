package com.ass.manager.service.autoPaike;

import com.ass.mapper.TClassMapper;
import com.ass.mapper.TClassroomMapper;
import com.ass.mapper.TReadyMapper;
import com.ass.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
* 个体，由染色体组成
* 一个个体代表一个全校的课表
* */
@Component
public class Individual implements Cloneable {
    /*全校所有的班级*/
    private List<TClass> classes;
    private int class_size;
    private int score;    //适应度得分
    private String groups;    //学期
    private int genes[][][];    //基因序列

    @Autowired
    private TClassMapper tClassMapper;

    @Autowired
    private TClassroomMapper tClassroomMapper;

    private List<TClassroom> classrooms;
    @Autowired
    private Chromsome chromsome;

    @Autowired
    private TReadyMapper tReadyMapper;

    @Autowired
    private ResolveConflict resolveConflict;

    @Autowired
    private StatisticsScore statisticsScore;

    /**
     * 对个体进行初始化
     * 一行表示一个班级的周课表
     */
    public void init(String groups) {
        this.groups = groups;
        initClass();
        genes = new int[class_size][][];
        for (int i = 0; i < class_size; i++) {
            int class_id = Integer.parseInt(classes.get(i).getId());
            chromsome.init(class_id, groups);
            genes[i] = chromsome.getGenes();
            chromsome.display();

        }
        initClassRoom();    //初始化班级列表
        String type = null;
//        System.out.println("======================");
//        System.out.println("初始课表");
//        display();
//        System.out.println("======================");
//        resolveConflict();    //解决冲突
//        System.out.println("冲突解决");
//        System.out.println("======================");
//        System.out.println("冲突解决后的课表");
//        display();
//        System.out.println("======================");
//        calculateScore();
//        System.out.println(score);
        //System.out.println("testint = " + testint);
//        display();
    }

    public void display() {
        for (int i = 0; i < genes.length; i++) {
            System.out.println("\n=====================");
            System.out.println("class" + (i + 1));
            for (int j = 0; j < genes[i].length; j++) {
                if (j % 5 == 0) {
                    System.out.println();
                }
                for (int k = 0; k < genes[i][j].length; k++) {
                    System.out.print(genes[i][j][k]);
                }
                System.out.print("  ");
            }
        }
    }


    /*初始化班级列表*/
    private void initClassRoom() {
        TClassroomExample tClassroomExample = new TClassroomExample();
        TClassroomExample.Criteria criteria = tClassroomExample.createCriteria();
        criteria.andStatusEqualTo(1);
        classrooms = tClassroomMapper.selectByExample(tClassroomExample);
    }


    /*计算适应度*/
    public void calculateScore() {
        score = 0;
        if (haveResolve()) {
            score = 0;
            return;
        }
        for (int i = 0; i < genes.length; i++) {
            score += statisticsScore.getScore(genes[i]);
        }

        //System.out.println(score);
    }

    private boolean haveResolve() {
        for (int time = 0; time < genes[0].length; time++) {
            for (int i = 0; i < genes.length; i++) {
                ArrayList<String> use_classrooms = new ArrayList<>();
                /*教室冲突*/
                int teacher_id_01 = genes[i][time][2];
                if (genes[i][time][0] != 0) {
                    if (!use_classrooms.contains(genes[i][time][3]+"")) {
                        use_classrooms.add(genes[i][time][3]+"");
                    } else {
                        System.out.println("教室存在冲突所以得分为0");
                        return true;
                    }

                    for (int j = i + 1; j < genes.length; j++) {
                        if (teacher_id_01 == genes[j][time][2]) {
                            System.out.println("教师存在冲突所以得分为0===============");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /*初始化班级*/
    private void initClass() {
        TClassExample tClassExample = new TClassExample();
        TClassExample.Criteria criteria = tClassExample.createCriteria();
        criteria.andStatusEqualTo(1);
        classes = tClassMapper.selectByExample(tClassExample);
        class_size = classes.size();
        //System.out.println(classes);
    }


    /**
     * 解决冲突
     */
    public void resolveConflict() {
//        ResolveConflict.teacherConflict(genes);
//        System.out.println("教师冲突解决");
//        ResolveConflict.classroomConflict(genes, classrooms,tClassroomMapper);
//        System.out.println("教室冲突解决");

        resolveConflict.conflict(genes, classrooms);
    }

    public List<TClass> getClasses() {
        return classes;
    }

    public void setClasses(List<TClass> classes) {
        this.classes = classes;
    }

    public int getClass_size() {
        return class_size;
    }

    public void setClass_size(int class_size) {
        this.class_size = class_size;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public int[][][] getGenes() {
        return genes;
    }

    public void setGenes(int[][][] genes) {
        this.genes = genes;
    }

    @Override
    public Individual clone() {
        Individual individual = null;
        try {
            individual = (Individual) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        /*克隆基因序列*/
        int[][][] copyGenes = new int[class_size][25][4];
        cloneGenes(copyGenes, this.genes);
        individual.setGenes(copyGenes);
        return individual;
    }

    /*克隆基因*/
    private static void cloneGenes(int[][][] genes, int[][][] original) {
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[i].length; j++) {
                for (int k = 0; k < original[i][j].length; k++) {
                    genes[i][j][k] = original[i][j][k];
                }
            }
        }
    }

    /**
     * @param p1
     * @param p2
     * @Description: 遗传产生下一代
     */
    public static List<Individual> genetic(Individual p1, Individual p2) {
        if (p1 == null || p2 == null) { //染色体有一个为空，不产生下一代
            System.out.println("染色体为空");
            return null;
        }
        if (p1.genes == null || p2.genes == null) { //染色体有一个没有基因序列，不产生下一代
            System.out.println("染色体无基因序列");
            return null;
        }
        if (p1.genes.length != p2.genes.length) { //染色体基因序列长度不同，不产生下一代
            System.out.println("染色体基因序列长度不同");
            return null;
        }
        /*克隆*/
        Individual c1 = p1.clone();
        Individual c2 = p2.clone();
        //随机产生交叉互换位置
        int size = c1.genes.length;
        int a = ((int) (Math.random() * size)) % size;
        int b = ((int) (Math.random() * size)) % size;
        int min = a > b ? b : a;
        int max = a > b ? a : b;
        //对位置上的基因进行交叉互换
        int[][] t;
        for (int i = min; i <= max; i++) {
            t = c1.genes[i];
            c1.genes[i] = c2.genes[i];
            c2.genes[i] = t;
        }
        List<Individual> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        return list;
    }


    /**
     * @param num
     * @Description: 基因num个位置发生变异
     */
    public void mutation(int num) {
        //允许变异
        int size = genes.length;
        for (int i = 0; i < num; i++) {
            //寻找变异位置
            int at = GAUtils.random(size);
            //变异后的值
            int class_id = Integer.parseInt(classes.get(at).getId());
            chromsome.init(class_id, groups);
            genes[at] = chromsome.getGenes();
        }
    }


    /**
     * 将课表写入数据库
     */
    public void inputDataBase() {
        /*清空数据库*/
        tReadyMapper.deleteByExample(new TReadyExample());
        long id = 1;
        for (int i = 0; i < genes.length; i++) {
            String class_id = classes.get(i).getId();
            for (int j = 0; j < genes[i].length; j++) {
                if (genes[i][j][0] != 0) {

                    String time = genes[i][j][0] + "";
                    int course_id = genes[i][j][1];
                    String teacher_id = genes[i][j][2] + "";
                    int classroom_id = genes[i][j][3];
                    TReady tReady = new TReady();
                    tReady.setClassid(class_id);
                    tReady.setClassroomid(classroom_id);
                    tReady.setCourseid(course_id);
                    tReady.setTime(time);
                    tReady.setTeacherid(teacher_id);
                    tReady.setGroups(groups);
                    tReady.setId(id);
                    tReadyMapper.insert(tReady);
                    id++;
                }
            }
        }
    }
}
