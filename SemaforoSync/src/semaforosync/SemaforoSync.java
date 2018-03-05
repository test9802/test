/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforosync;

import java.util.concurrent.Semaphore;
//from   jav  a  2  s  .  co m
public class SemaforoSync {
  public static void main(String args[]) {
    Semaphore sem = new Semaphore(1);
    new Producer(sem, "P");
    //new Intermediary(sem, "I");
    new Consumer(sem, "C");
    
  }
}
class Compartir {
  static int contador = 0;
}
class Producer implements Runnable {
  String name;
  Semaphore sem;

  Producer(Semaphore s, String n) {
    sem = s;
    name = n;
    new Thread(this).start();
  }
  public void run() {
    try {
      sem.acquire();
      for (int i = 0; i < 5; i++) {
        System.out.println(name + ": " + Compartir.contador++);
        Thread.sleep(1000);
      }
    } catch (InterruptedException exc) {
      System.out.println(exc);
    }
    sem.release();
  }
}
class Consumer implements Runnable {
  String name;
  Semaphore sem;
  Consumer(Semaphore s, String n) {
    sem = s;
    name = n;
    new Thread(this).start();
  }
  public void run() {
    try {
      sem.acquire();
      
      for (int i = 0; i < 5; i++) {
        System.out.println(name + ": " + Compartir.contador--);
        Thread.sleep(1000);
      }
    } catch (InterruptedException exc) {
      System.out.println(exc);
    }
    sem.release();
  }
}
/*
class Intermediary implements Runnable {
  String name;
  Semaphore sem;

  Intermediary(Semaphore s, String n) {
    sem = s;
    name = n;
    new Thread(this).start();
  }
  public void run() {
    try {
      sem.acquire();
      for (int i = 0; i < 5; i++) {
        System.out.println(name + ": " + Compartir.contador++);
        Thread.sleep(1000);
      }
    } catch (InterruptedException exc) {
      System.out.println(exc);
    }
    sem.release();
  }
}
*/
