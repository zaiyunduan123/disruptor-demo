package disruptor.com.factory;

import com.lmax.disruptor.EventFactory;
import disruptor.com.event.MyInParkingDataEvent;

/**
 * @Author jiangyunxiong
 * @Date 2019/7/28 2:37 AM
 */
public class MyInParkingEventFactory implements EventFactory<MyInParkingDataEvent> {
    @Override
    public MyInParkingDataEvent newInstance() {
        return new MyInParkingDataEvent();
    }
}
