package disruptor.com.consumer;

import com.lmax.disruptor.EventHandler;
import disruptor.com.event.MyInParkingDataEvent;
/**
 * @Author jiangyunxiong
 * @Date 2019/7/28 1:31 AM
 * <p>
 *  第二个消费者，负责发送通知告知工作人员
 */
public class MyParkingDataToKafkaHandler implements EventHandler<MyInParkingDataEvent> {


    @Override
    public void onEvent(MyInParkingDataEvent myInParkingDataEvent, long l, boolean b) throws Exception {
        String carLicense = myInParkingDataEvent.getCarLicense();
        System.out.println("第二个消费者  负责发送 "+carLicense+" 进入停车场信息给 kafka系统 ....");
    }
}
