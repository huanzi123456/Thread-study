package com.huanzi.juc.code.map;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.IntStream;
public class ConurrentSkipListMapTest {

    public static void main(String[] args) {
     ConcurrentSkipListMap<String,Integer> map = new ConcurrentSkipListMap<>();
      int size =10;
       Thread[] threads = new Thread[size];
        IntStream.range(0,size).forEach(i->{
             Thread thread = new Thread(new AddTest(map,i));
             threads[i] = thread;
             thread.start();
         });
         Arrays.asList(threads).stream().forEach(t-> {
             try {
                 t.join();
             } catch (InterruptedException e) {}
         });

         map.subMap("4","8").entrySet().stream().map(Map.Entry::getValue).
                 forEach(System.out::println);//求出区间，这个跳跃表最大的优势

 }

  private static class AddTest implements Runnable{
     private ConcurrentSkipListMap<String,Integer> map;
      private String key ;
       public AddTest(ConcurrentSkipListMap<String,Integer> map,Integer value) {
            this.map = map;
             key = String.valueOf(value);
        }

         @Override
         public void run() {
            map.put(key,Integer.valueOf(key));
        }
 }
 }
