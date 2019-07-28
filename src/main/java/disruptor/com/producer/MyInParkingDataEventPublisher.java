package disruptor.com.producer;

import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;
import disruptor.com.event.MyInParkingDataEvent;


/**
 * @Author jiangyunxiong
 * @Date 2019/7/28 1:53 AM
 * <p>
 * 生产者，进入停车场的车辆
 */
public class MyInParkingDataEventPublisher {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private final static EventTranslatorVararg<MyInParkingDataEvent> translator = new EventTranslatorVararg<MyInParkingDataEvent>() {
        public void translateTo(MyInParkingDataEvent myInParkingDataEvent, long seq, Object... objs) {
            String carLicense = "粤A-" + (int) (Math.random() * 100000);
            myInParkingDataEvent.setCarLicense(carLicense);// 随机生成一个车牌号
            threadLocal.set(carLicense);
        }
    };

    private final RingBuffer<MyInParkingDataEvent> ringBuffer;


    public MyInParkingDataEventPublisher(RingBuffer<MyInParkingDataEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void publicEvent() {
        this.ringBuffer.publishEvent(translator);
        System.out.println("生产者  完成了车牌号为" + threadLocal.get() + " 的上报停车数据 ....");

    }


}
