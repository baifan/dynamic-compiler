import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Hello extends FileInputStream {

    public Hello(String name) throws FileNotFoundException {
        super(name);
    }

    public void test(Object test) throws Exception{
        System.out.println(super.available());
        Map object = (HashMap) test;

    }
}