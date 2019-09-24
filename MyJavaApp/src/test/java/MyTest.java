import org.junit.Assert;
import org.junit.Test;
import com.thread.CopyOnWriteArrayList.ReadThread;
import com.thread.CopyOnWriteArrayList.WriteThread;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyTest {

    @Test
    public void name() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        HelloWorld.print(new PrintStream(out));
        String s = out.toString();

        Assert.assertEquals("Hello", s);
    }

    /**
     * 测试CopyOnWriteArrayList当在读的过程中，添加了元素，会读到新添加的元素。
     */
    @Test
    public void testCopyOnWriteArrayList() {
        //1、初始化CopyOnWriteArrayList
        List<Integer> tempList = Arrays.asList(new Integer [] {1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2});
        CopyOnWriteArrayList<Integer> copyList = new CopyOnWriteArrayList<>(tempList);

        //2、模拟多线程对list进行读和写
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));

        System.out.println("copyList size:"+copyList.size());

        try {
            Thread.sleep(1000 * 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
