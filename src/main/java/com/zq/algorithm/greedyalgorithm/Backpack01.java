package com.zq.algorithm.greedyalgorithm;

/**
 *
 * ������̰��ѡ����Ƿ���0/1��������
 *
 *
 * Created by zhengshouzi on 2015/12/21.
 */
public class Backpack01 {
    public static void main(String[] args) {
        int[] widget = {10,20,30};
        int[] money= {60,100,120};
        double[] solution =new double[widget.length];
        System.out.println("��װ���ܼ�ֵ��"+backpack_01(widget,money,50,solution));
        System.out.println("��ȡ�ķ����ǣ�");
        for (int i = 0; i < widget.length; i++) {
            if (solution[i]>0){
                System.out.print(widget[i]+"*"+solution[i] +" , ");
            }
        }


    }
    public static int backpack_01(int[] widget,int[] money,int capacity,double[] solution){

        double[] value = new double[widget.length];
        //ʹ��̰���㷨
        for (int i = 0; i < value.length; i++) {
            value[i] = money[i]*1.0 / widget[i];
        }
        int sumValue =0;
        while (capacity>0){

            int index =  maxValueIndex(value);
            if (widget[index]<capacity){
                capacity -=widget[index];
                sumValue+=money[index];
                solution[index] =1;
            }else {
                sumValue += (capacity*1.0/widget[index] * money[index]);
                solution[index] = capacity*1.0/widget[index];
                capacity=0;
            }
        }
      return sumValue;
    }
    public static int maxValueIndex(double[] value){
        double max= value[0];
        int index =0;
        for (int i = 1; i < value.length; i++) {
            if (value[i]>=max){
                index=i;
                max =value[i];
            }
        }
        value[index] = 0;
        return index;
    }

}
