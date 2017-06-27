package wangjicong.xmlparser;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class Center {
    private static final String TAG = Center.class.getName();
    public static final String CENTER = "center";
    public static final String NAME = "name";
    public static final String NUMBERS = "numbers";
    public static final String ADDRESS = "address";


    private String name;
    private String numbers;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
