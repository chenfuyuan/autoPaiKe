package com.ass.manager.service.autoPaike;


import com.ass.mapper.TCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
* 计算适应度
*
* */
@Component
public class StatisticsScore {
    private  int score;    //周课表适应度
    private  int timeScore;    //节次适应度
    private  int timeSpaceScore;    //时间间隔适应度
    private  Map<Integer, Integer> time_Space_Map;
    /*理论课时间期望值*/
    private static int[] timeWeight = {4, 5, 4, 3, 3, 5, 5, 4, 4, 2, 5, 5, 4, 3, 2, 5, 5, 3, 3, 2, 4, 5, 3, 2, 1};
    /*体育课时间期望值*/
    private static int[] sportTimeWeight = {0, 4, 0, 4, 0, 0, 4, 0, 4, 0, 0, 4, 0, 4, 0, 0, 4, 0, 4, 0, 0, 4, 0, 4, 0};
    /*时间差期望值*/
    private static int[] timeSpaceWeight = {0, 0, 0, 0, 0, 0, 0, 4, 4, 2, 2, 5, 5, 3, 3, 5, 5, 4, 4, 3, 3, 2, 2, 1};

    @Autowired
    private TCourseMapper tCourseMapper;

    /**
     * 计算适应度
     *
     * @param genes 需要计算的课表
     * @return 返回适应度得分
     */
    public  int getScore(int genes[][]) {
        init();
        statisticsScore(genes);
        /*适应度得分 = 节点适应度平均值 + 时间间隔适应度平均值*/
        score = (int)(timeScore * 0.7 + timeSpaceScore * 0.3);
        return score;
}


    /*初始化*/
    private  void init() {
        score = 0;
        timeScore = 0;
        timeSpaceScore = 0;
        time_Space_Map = new HashMap<>();
    }

    /**
     * 统计节点适应度 和 时间间隔适应度
     *
     * @param genes 需要统计的课表
     */
    private  void statisticsScore(int genes[][]) {
        time_Space_Map = new HashMap<>();
        timeSpaceScore = 0;    //初始化时间间隔得分
        timeScore = 0;    //初始化节次适应度得分
        int t = 0;
        for (int i = 0; i < genes.length; i++) {
            timeScore+=5;
            if (genes[i][0] != 0) {
//                String way = tCourseMapper.selectByPrimaryKey(genes[i][1]).getWay();
//                if (way.equals("室外")) {
//                    timeScore += sportTimeWeight[i];
//                }else {
                    timeScore += timeWeight[i];    //统计节次适应度得分
//                }
                int course_id = genes[i][1];
                int time = genes[i][0];
                if (time_Space_Map.get(course_id) == null) {
                    time_Space_Map.put(course_id, time);
                } else {
                    int time2 = time_Space_Map.get(course_id);
                    int timeSpace = Math.abs(time - time2);
                    time_Space_Map.put(course_id, time);
                    timeSpaceScore += timeSpaceWeight[timeSpace - 1];
                }
            }else{
                t++;
                if (t == 5) {
                    timeScore -= 5;
                }
            }
            if ((i + 1) % 5 == 0) {
                t = 0;
            }
        }
    }
}
