package machines;

import java.util.concurrent.atomic.AtomicInteger;

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
