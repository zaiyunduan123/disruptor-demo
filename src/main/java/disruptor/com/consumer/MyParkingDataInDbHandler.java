package disruptor.com.consumer;
import com.lmax.disruptor.EventHandler;
import disruptor.com.event.MyInParkingDataEvent;
/**
 * @Author jiangyunxiong
 * @Date 2019/7/28 1:31 AM
 * <p>
 * 第一个消费者，负责保存进场汽车的信息
 */
public class MyParkingDataInDbHandler implements EventHandler<MyInParkingDataEvent> {

    @Override
    public void onEvent(MyInParkingDataEvent myInParkingDataEvent, long l, boolean b) throws Exception {
        String carLicense = myInParkingDataEvent.getCarLicense();
        System.out.println("第一个消费者 负责保存 " + carLicense + " 信息 ....");
    }
}
