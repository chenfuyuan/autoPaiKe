package com.ass.manager.service.autoPaike;

import com.ass.mapper.TReadyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*遗传算法*/
@Component
public class GAAlgorithm {
    /*种群数量*/
    private int population_size = 50;
    private int maxIterNum = 50;
    private int generation = 1;

    private double bestScore;//最好得分
    private double worstScore;//最坏得分
    private double totalScore;//总得分
    private double averageScore;//平均得分
    /**基因变异的概率*/
    private double mutationRate = 0.01;
    /**最大变异步长*/
    private int maxMutationNum = 4;
    /*记录最好的一次*/
    private Individual bestIndividual;
    private int maxBestScore = Integer.MIN_VALUE;
    private int geneI;    //第几代

    @Autowired
    private Individual individual;

    private List<Individual> population;

    @Autowired
    private TReadyMapper tReadyMapper;

    public boolean caculte(String groups) {
        //1.初始化种群
        init(groups);
        for (generation = 1; generation <= maxIterNum; generation++) {
            System.out.println("遗传第"+(generation)+"代");
            //2.计算种群适应度
            caculteScore();
            System.out.println("3>验证阈值...");
            //4.种群遗传
            evolve();
            //5.基因突变
            mutation();
        }
        System.out.println(population);
        bestIndividual.display();
        bestScore = bestIndividual.getScore();

        System.out.println("bestScore = " + bestScore);
        bestIndividual.inputDataBase();

        return true;
    }

    /*基因突变*/
    private void mutation() {
        System.out.println("5>基因突变...");
        for (Individual individual : population) {
            if (Math.random() < mutationRate) { //发生基因突变
                int mutationNum = GAUtils.random(maxMutationNum);
                individual.mutation(mutationNum);
            }
        }
    }

    /**
     * 种群进行遗传进化
     */
    private void evolve() {
        List<Individual> childPopulation = new ArrayList<>();
        //生成下一代种群
        while (childPopulation.size() < population_size) {
            Individual childOne = getParentIndividual();
            Individual childTwo = getParentIndividual();
            List<Individual> children = Individual.genetic(childOne, childTwo);
            if (children != null) {
                for (Individual individual : children) {
                    childPopulation.add(individual);
                }
            }
        }
        int i = 0;
        /*精英保留*/


        System.out.println("4.2>产生子代种群...");
        //新种群替换旧种群
        population.clear();
        population = childPopulation;
    }



    /**
     * 轮盘赌法筛选子代
     *
     * @return
     */
    private Individual getParentIndividual() {
//        System.out.println("4.1>筛选父代种群一次...");
        while (true) {
            int slice = GAUtils.random(totalScore);//在total区间中取一个随机数
            int sum = 0;
//            System.out.println("slice = " + slice);
            for (Individual individual : population) {
                sum += individual.getScore();
//                System.out.println("测试：sum=" + sum + "  individual.getScore()=" + individual.getScore());
                if (sum > slice &&individual.getScore() >= averageScore) {
                    return individual;
                }
            }
        }
    }

    /**
     * 计算机适应度
     */
    private void caculteScore() {
        System.out.println("2>计算适应度...");
        bestScore = population.get(0).getScore();
        worstScore = population.get(0).getScore();
        totalScore = 0;
        for (Individual individual : population) {
            individual.resolveConflict();
            individual.calculateScore();
            double score = individual.getScore();
            if (score >= bestScore && score > maxBestScore) {
                bestScore = score;
                bestIndividual = individual.clone();
                geneI = generation;
            }
            if (score < worstScore ) {
                worstScore = score;
            }
            totalScore += score;    //统计总得分
        }
        System.out.println(totalScore);
        System.out.println("best = " + bestScore);
        averageScore = totalScore / population_size;    //平均得分
        averageScore = averageScore > bestScore ? bestScore : averageScore;
        System.out.println("averageScore"+averageScore);
    }


    /**
     * 初始化种群
     *
     * @param groups 学期
     */
    private void init(String groups) {
        System.out.println("1>初始化种群...");
        population = new ArrayList<>();
        for (int i = 0; i < population_size; i++) {
            individual.init(groups);
            Individual copy_individual = individual.clone();
            population.add(copy_individual);
            System.out.println("初始化成功"+(i+1)+"个");
        }
        System.out.println("初始化成功");
        System.out.println(population);
    }

    public Individual getBestIndividual() {
        return bestIndividual;
    }

    public void setBestIndividual(Individual bestIndividual) {
        this.bestIndividual = bestIndividual;
    }
}
