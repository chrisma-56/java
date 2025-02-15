import java.util.*;

class program6_2 {
    final String[] buffer=new String[5];
    int count=0;
    public static void main(String[] args) {
        program6_2 obj=new program6_2();
        Producer pos=obj.new Producer();
        Consumer cos=obj.new Consumer();
        pos.start();
        cos.start();
    }
    class Producer extends Thread
    {
        public void run()
        {
        while (true) {
            procedData();
            
        }
        }

    }
    class Consumer extends Thread
    {
        public void run()
        {
        while (true) {
            consumedData();
            
        }
        }

    }
    public void procedData()
    {
        synchronized(buffer){
            if(count==buffer.length){
                try{
                    System.out.println("Buffer is full");
                    buffer.wait();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
            Scanner s=new Scanner(System.in);
            System.out.println("Enter The Data into Buffer");
            String data=s.nextLine();
            if("exit".equalsIgnoreCase(data))
            {
                System.exit(0);
            }
            buffer[count++]=data;
            System.out.println("Produced Data:"+ data);
            buffer.notifyAll();
        }
    }
    public void consumedData()
    {
        synchronized(buffer)
        {
            if(count==0)
            {
                try {
                    System.out.println("Buffer is Empty");
                    buffer.wait();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
            String data=buffer[--count];
            System.out.println("Consumed Data:"+ data);
            buffer.notifyAll();
        }
    }
    
}