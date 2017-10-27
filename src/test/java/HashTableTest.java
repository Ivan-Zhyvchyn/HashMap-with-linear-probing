import com.softindex.test.MyHashMap;
import org.junit.Assert;
import org.junit.Test;

public class HashTableTest {

    @Test
    public void testPutAndGet(){
        MyHashMap hashMap = new MyHashMap();

        hashMap.put(13, 355);
        hashMap.put(20, 1);
        hashMap.put(44, 10000);
        hashMap.put(1, 999);

        Assert.assertEquals(1,hashMap.get(20));
        Assert.assertEquals(355,hashMap.get(13));
        Assert.assertEquals(999,hashMap.get(1));

    }

    @Test
    public void testSize(){

        MyHashMap hashMap = new MyHashMap();

        hashMap.put(1, 355);
        hashMap.put(3, 300);
        hashMap.put(2345, 12345);
        hashMap.put(1343, 1543);

        Assert.assertEquals(4,hashMap.size());

    }
}
