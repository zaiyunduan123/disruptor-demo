package disruptor.com.consumer;


import com.lmax.disruptor.EventHandler;
import disruptor.com.event.MyInParkingDataEvent;


/**
 * @Author jiangyunxiong
 * @Date 2019/7/28 1:31 AM
 * <p>
 * 第三个消费者，sms短信服务，告知司机你已经进入停车场，计费开始。
 */
public class MyParkingDataToSmsHandler implements EventHandler<MyInParkingDataEvent> {


    @Override
    public void onEvent(MyInParkingDataEvent myInParkingDataEvent, long l, boolean b) throws Exception {
        String carLicense = myInParkingDataEvent.getCarLicense();
        System.out.println("第三个消费者 负责给 " + carLicense + " 的车主发送一条短信，并告知他计费开始了 ....");
    }
}
