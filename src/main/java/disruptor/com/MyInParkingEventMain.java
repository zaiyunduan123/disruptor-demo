package disruptor.com;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import disruptor.com.consumer.MyParkingDataInDbHandler;
import disruptor.com.consumer.MyParkingDataToKafkaHandler;
import disruptor.com.consumer.MyParkingDataToSmsHandler;
import disruptor.com.event.MyInParkingDataEvent;
import disruptor.com.factory.MyInParkingEventFactory;
import disruptor.com.producer.MyInParkingDataEventPublisher;

import java.util.concurrent.ThreadFactory;

/**
 * 执行的Main方法 ，
 * 一个生产者（汽车进入停车场）；
 * 三个消费者（一个记录汽车信息，一个发送消息给系统，一个发送消息告知司机）
 * 前两个消费者同步执行，都有结果了再执行第三个消费者
 */
public class MyInParkingEventMain {
    public static void main(String[] args) throws InterruptedException {

        MyInParkingEventFactory factory = new MyInParkingEventFactory();
        int ringBufferSize = 1024;
        ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable);
            }
        };
        //创建disruptor
        Disruptor<MyInParkingDataEvent> disruptor = new Disruptor<>(factory, ringBufferSize, threadFactory);

        // 使用disruptor创建消费者组 MyParkingDataInDbHandler 和 MyParkingDataToKafkaHandler
        EventHandlerGroup<MyInParkingDataEvent> handlerGroup = disruptor.handleEventsWith(
                new MyParkingDataInDbHandler(), new MyParkingDataToKafkaHandler());

        // 当上面两个消费者处理结束后在消耗 smsHandler
        MyParkingDataToSmsHandler myParkingDataSmsHandler = new MyParkingDataToSmsHandler();
        handlerGroup.then(myParkingDataSmsHandler);

        //启动
        disruptor.start();
        RingBuffer<MyInParkingDataEvent> ringBuffer = disruptor.getRingBuffer();
        MyInParkingDataEventPublisher producer = new MyInParkingDataEventPublisher(ringBuffer);
        for (int i = 0; i < 5; i++) {
            producer.publicEvent();
            Thread.sleep(1000); // 假设一秒钟进一辆车
        }
        //关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；
        disruptor.shutdown();
    }
}
