package com.example.po.stadiummanagement3.step.service;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.CountDownTimer;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 田雍恺 on 2017/12/14.
 * 加速传感器计步的方法
 * 根据加速度传感器三轴的平均值得到行走过程中的位置信息，满足走一步的条件就计一步
 * 将计步分为三个状态，准备计时、计时中、计步中
 * “计时中”是在3.5秒内每隔0.7秒对步数进行一次判断，看步数是否仍然在增长，如果不在增长说明
 * 之前是无效的震动并没有走路，得到的步数不计入总步数中；反之则将这3.5秒内的步数加入总步数中。
 * 之后进入“计步中”状态进行持续计步，并且每隔2秒去判断一次当前步数和2秒前的步数是否相同，如
 * 果相同则说明步数不在增长，计步结束
 */

public class StepDetector implements SensorEventListener {
    private final String TAG="TAG_StepDetector";     //"StepDetector";
    private final int valueNum = 5;  //存放三轴数据的个数
    private float[] tempValue=new float[valueNum];  //用于存放计算阈值的波峰波谷差值
    private int tempCount=0;
    private boolean isDirectionUp = false; //是否上升标志
    private int continueUpCount=0; //持续上升次数
    private int continueUpFormerCount=0;//上一点的持续上升的次数
    private boolean lastStatus = false; //上一点的状态，上升还是下降
    private float peakOfWave = 0; //波峰值
    private float valleyOfWave = 0; //波谷值
    private long timeOfThisPeak = 0; //此次波峰时间
    private long timeOfLastPeak = 0; //上次波峰时间
    private long timeOfNow = 0; //当前时间
    private float gravityOld = 0;//上次传感器的值
    private final float initialValue = (float)1.7;
    private float ThreadValue=(float)2.0;

    //初始范围
    private float minValue=11f;
    private float maxValue=19.6f;

    private int CountTimeState=0; //0：准备计时， 1：计时中  2：正常计步中
    public static int CURRENT_STEP=0; //记录当前的步数
    public static int TEMP_STEP=0;//记录临时的步数
    private int lastStep = -1;
    private static float average=0; //用x、y、z轴三个维度计算出平均值
    private Timer timer;
    private long duration = 3500;//倒计时3.5秒，此期间不会显示步数，用于屏蔽细微波动
    private TimeCount time;
    OnSensorChangeListener onSensorChangeListener;

    //回调函数
    public interface OnSensorChangeListener {
        void onChange();
    }

    public StepDetector(Context context) {
        super();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //监听器set方法
    public void setOnSensorChangeListener(OnSensorChangeListener onSensorChangeListener) {
        this.onSensorChangeListener=onSensorChangeListener;
    }

    //当传感器发生改变后调用的函数
    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        //同步块
        synchronized (this) {
            //获取加速传感器
            if(sensor.getType()==sensor.TYPE_ACCELEROMETER) {
                calc_step(event);
            }
        }
    }

    private void calc_step(SensorEvent event) {
        average=(float)Math.sqrt(Math.pow(event.values[0], 2) + Math.pow(event.values[1],2) + Math.pow(event.values[2],2));
        detectorNewStep(average);
    }

    /**
     * 检测新的步数
     * 1、传入sensor中的数据
     * 2、检测到波峰并且符合时间差以及阈值条件，则判定为一步
     * 3、符合时间差条件，但波峰值大于initialValue，将该差值纳入阈值的计算中
     * @param values     加速传感器三轴的平均值
     */
    private void detectorNewStep(float values) {
        if(gravityOld==0) {
            gravityOld=values;
        } else {
            if(DetectorPeak(values, gravityOld)) {
                timeOfLastPeak=timeOfThisPeak;
                timeOfNow=System.currentTimeMillis();

                if(timeOfNow-timeOfLastPeak>=200&&(peakOfWave-valleyOfWave>=ThreadValue)&&
                        (timeOfNow-timeOfLastPeak)<=2000){
                    timeOfThisPeak=timeOfNow;
                    preStep();
                }
                if(timeOfNow-timeOfLastPeak>=200&&(peakOfWave-valleyOfWave>=initialValue)) {
                    timeOfThisPeak=timeOfNow;
                    ThreadValue=Peak_Valley_Thread(peakOfWave-valleyOfWave);
                }
            }
        }
        gravityOld=values;
    }

    /**
     * 判断状态并计步
     */
    private void preStep() {
        if(CountTimeState==0) {
            //开启计时器，在3.5秒内每0.7秒检测一次
            time=new TimeCount(duration,700);
            time.start();
            CountTimeState=1;  //计时中
            Log.v(TAG,"开启计时器");
        } else if(CountTimeState==1) {
            TEMP_STEP++;  //传感器测得的数据满足一步的条件步数加一
            Log.v(TAG,"计步中 TEMP_STEP:"+TEMP_STEP);
        } else if(CountTimeState==2) {
            CURRENT_STEP++;
            if(onSensorChangeListener!=null) {
                onSensorChangeListener.onChange();
            }
        }
    }

    /**
     * 检测波峰
     * 满足四个条件
     * 1、目前的点为下降趋势
     * 2、之前的点为上升趋势
     * 3、到波峰为止，持续上升大于等于第二次
     * 4、波峰值大于1.2g，小于2g
     * @param newValue
     * @param oldValue
     * @return
     */
    public boolean DetectorPeak(float newValue, float oldValue) {
        lastStatus=isDirectionUp;
        if(newValue>=oldValue) {
            isDirectionUp=true;
            continueUpCount++;
        } else {
            continueUpFormerCount=continueUpCount;
            continueUpCount=0;
            isDirectionUp=false;
        }
        if(!isDirectionUp&&lastStatus&&(continueUpFormerCount>=2&&(oldValue>=minValue&&oldValue<=maxValue))) {
            //满足上面波峰的四个条件，此时为波峰状态
            peakOfWave=oldValue;
            return true;
        } else if(!lastStatus&&isDirectionUp) {
            //满足波谷条件，此时为波谷状态
            valleyOfWave=oldValue;
            return false;
        } else {
            return false;
        }
    }

    /**
     * 阈值计算
     * 通过波峰波谷的差值计算阈值
     * 记录四个值，存入tempValue[]数组中
     * 再将数组传入函数averageValue中计算阈值
     * @param value
     * @return
     */
    public float Peak_Valley_Thread(float value){
        float tempThread=ThreadValue;
        if(tempCount<valueNum) {
            tempValue[tempCount]=value;
            tempCount++;
        } else {
            //此时tempCount=valueNum=5
            tempThread=averageValue(tempValue, valueNum);
            for(int i=0; i<valueNum;i++){
                tempValue[i-1]=tempValue[i];
            }
            tempValue[valueNum-1]=value;
        }
        return tempThread;
    }

    /**
     * 梯度化阈值
     * @param value
     * @param n
     * @return
     */
    public float averageValue(float value[], int n){
        float ave=0;
        for(int i=0;i<n;i++) {
            ave+=value[i];
        }
        ave=ave/valueNum;  //计算数组平均值
        if(ave>=8){
            Log.v(TAG,"超过8");
            ave=(float)4.3;
        }else if(ave>=7&&ave<8){
            Log.v(TAG,"7-8");
            ave=(float)3.3;
        }else if(ave>=4&&ave<7){
            Log.v(TAG,"4-7");
            ave=(float)2.3;
        }else if(ave>=3&&ave<4){
            Log.v(TAG,"3-4");
            ave=(float)2.0;
        }else{
            Log.v(TAG,"else (ave<3)");
            ave=(float)1.7;
        }
        return ave;
    }
    class TimeCount extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if(lastStep==TEMP_STEP){
                //一段时间内，TEMP_STEP没有步数增长，则计时停止，同时计步也停止
                Log.v(TAG,"onTick 计时停止");
                time.cancel();
                CountTimeState=0;
                lastStep=-1;
                TEMP_STEP=0;
            }else{
                lastStep=TEMP_STEP;
            }

        }

        @Override
        public void onFinish() {
            //计时器正常结束，则开始计步
            time.cancel();
            CURRENT_STEP+=TEMP_STEP;
            lastStep=-1;
            Log.v(TAG,"计时器正常结束");
            timer=new Timer(true);
            TimerTask task=new TimerTask() {
                @Override
                public void run() {
                    //步数不再增长的时候停止计步
                    if(lastStep==CURRENT_STEP) {
                        timer.cancel();
                        CountTimeState=0;
                        lastStep=-1;
                        TEMP_STEP=0;
                    }
                    else {
                        lastStep=CURRENT_STEP;
                    }
                }
            };
            timer.schedule(task,0,2000);//每隔两秒执行一次，不断检测是否已经停止运动
            CountTimeState=2;
        }
    }
}