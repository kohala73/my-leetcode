package interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 多线程顺序打印数字
 * 三个线程分别打印 0、偶数、奇数，要求输出序列为 010203040506070809010...
 * 即：0,1,0,2,0,3,0,4,0,5,...
 * 
 * 线程A调用 zero()，只打印0。
 * 线程B调用 even()，只打印偶数。
 * 线程C调用 odd()，只打印奇数。
 */
public class ZeroEvenOdd {
    private int n; // 需要打印的数，直到 2n

    private final Lock lock = new ReentrantLock();
    private final Condition zeroCondition = lock.newCondition();
    private final Condition evenCondition = lock.newCondition();
    private final Condition oddCondition = lock.newCondition();
    
    // 状态标志：0-打印0, 1-打印奇数, 2-打印偶数
    private int state = 0; 

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                // 等待，直到轮到打印0
                while (state != 0) {
                    zeroCondition.await();
                }
                printNumber.accept(0);
                // 决定下一个打印奇数还是偶数
                if ((i + 1) % 2 == 1) {
                    state = 1; // 下一个奇数
                    oddCondition.signal();
                } else {
                    state = 2; // 下一个偶数
                    evenCondition.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            lock.lock();
            try {
                // 等待，直到轮到打印偶数
                while (state != 2) {
                    evenCondition.await();
                }
                printNumber.accept(i);
                state = 0; // 下一个打印0
                zeroCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            lock.lock();
            try {
                // 等待，直到轮到打印奇数
                while (state != 1) {
                    oddCondition.await();
                }
                printNumber.accept(i);
                state = 0; // 下一个打印0
                zeroCondition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    // --- 测试和演示 ---
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Java多线程顺序打印数字 ===");
        int n = 5; // 打印序列直到 0,1,0,2,0,3,0,4,0,5
        System.out.println("目标序列长度: " + (2 * n) + " (n=" + n + ")");
        
        ZeroEvenOdd zeo = new ZeroEvenOdd(n);
        
        StringBuilder result = new StringBuilder();
        IntConsumer printer = value -> {
            synchronized(result) {
                 result.append(value);
            }
            System.out.print(value);
        };
        System.out.print("打印结果: ");

        Thread tA = new Thread(() -> {
            try {
                zeo.zero(printer);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Thread-Zero");

        Thread tB = new Thread(() -> {
            try {
                zeo.even(printer);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Thread-Even");

        Thread tC = new Thread(() -> {
            try {
                zeo.odd(printer);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Thread-Odd");

        // 启动线程
        tA.start();
        tB.start();
        tC.start();

        // 等待所有线程执行完毕
        tA.join();
        tB.join();
        tC.join();
        
        System.out.println(); // 换行
        
        String expected = "0102030405";
        synchronized(result) {
            String actual = result.toString();
            System.out.println("期望结果: " + expected);
            System.out.println("实际结果: " + actual);
            System.out.println("结果正确: " + expected.equals(actual));
        }
    }
}



