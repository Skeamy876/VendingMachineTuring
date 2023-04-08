package machines;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created on 25/01/21 12:00 pm by Authors:
 * 1. Ackeem Mclennon 1803349
 * 2. Monique Bennett 2004188
 * 3. Christian Willams 1903419
 */

public class CounterRegister {
    private String item;
    private AtomicInteger count;

    public CounterRegister(String name, int initialValue) {
        this.item = name;
        this.count = new AtomicInteger(initialValue);
    }

    public int decrement() {
        return count.decrementAndGet();
    }

    public  int increment(){
        return count.incrementAndGet();
    }
}
