package disruptor.com.event;

/**
 * @Author jiangyunxiong
 * @Date 2019/7/28 1:28 AM
 *
 * 汽车信息
 */
public class MyInParkingDataEvent {

    // 车牌号
    private String carLicense;

    public String getCarLicense() {
        return carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }
}
