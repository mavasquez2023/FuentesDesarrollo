package cl.laaraucana.RentasPrevired.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author bustanil
 */
public class TaskExecutorExample {

    private class Task implements Runnable {
        
        private int i;

        public Task(int i) {
            this.i = i;
        }

        public void run() {
            final long sleeptime = Math.round(Math.random() * 1000) % 10 * 1000;
            System.out.println("Thread "+ i + " waiting for : " + sleeptime);
            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException ex) {
                Logger.getLogger(TaskExecutorExample.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Thread "+ i + " done!");
        }
    }
    
    private ThreadPoolTaskExecutor taskExecutor;

    public TaskExecutorExample(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void executeTasks() {
        for (int i = 0; i < 10; i++) {
            taskExecutor.execute(new Task(i));
        }
        taskExecutor.getThreadPoolExecutor().shutdown();
        while(!taskExecutor.getThreadPoolExecutor().isTerminated()) try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TaskExecutorExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Finished");
    }
    
    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring.xml");
        TaskExecutorExample taskExecutorExample = (TaskExecutorExample) ctx.getBean("taskExecutorExample");
        taskExecutorExample.executeTasks();
    }
    
}
