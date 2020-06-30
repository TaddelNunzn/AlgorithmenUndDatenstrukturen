package Übungsblatt1.Aufgabe4;

import java.util.LinkedList;
import java.util.List;

public class Sieve {
  static boolean[] sieve(int n) {
    boolean[] result = new boolean[n + 1];
    result[2]=true;
    for(int r = 3;r<n+1;r++){
      for(int i = 2;i<r;i++){
        if(r%i==0){result[r]=false;break;}
        result[r]=true;
      }
    }
    return result;
  }

  static List<Integer> primesUpTo(int n){
    List<Integer> result = new LinkedList<>();
    boolean[] sieve = sieve(n);
    for(int i=0;i<sieve.length;i++){
      if(sieve[i])result.add(i);
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(2&1);
  }
}