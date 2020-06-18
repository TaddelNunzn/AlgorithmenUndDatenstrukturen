
package Übungsblatt4.Aufgabe2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import junit.extensions.TestDecorator;

import java.util.Arrays;
import java.util.Random;

public class MergeSortTest {

    private static void runTest(int[] a) {
    	// create two copies of a, 
    	// one sorted using Java's built-in sort,
    	// one using the sort algorithm to test.
    	int[] acopy = new int[a.length];
    	int[] bcopy = new int[a.length];
    	System.arraycopy(a, 0, acopy, 0, a.length);
    	System.arraycopy(a, 0, bcopy, 0, a.length);
		MergeSort sort = new MergeSort();
		sort.sort(acopy);
    	Arrays.sort(bcopy);
    	assertTrue(Arrays.equals(acopy, bcopy));
    }
    
	@Test
	public void test1() {
		int[] a = new int[] {7};
		runTest(a);
	}
	
	@Test
	public void test2() {
		int[] a = new int[] {-3, -8, 1, -4};
		runTest(a);
	}

	@Test
	public void test3() {
		int[] a = new int[] {1, 2, 3, 4, 5};
		runTest(a);
	}

	@Test
	public void test4() {
		int[] a = new int[] {5, 4, 3, 2, 1};
		runTest(a);
	}

	@Test
	public void test5() {
		int[] a = MergeSort.createRandomArray(100);
		runTest(a);
	}

	@Test
	public void test6() {
		int[] a = new int[] {1,8,1,8,2,6,2,6};
		runTest(a);
	}

	@Test
	public void test7() {
		int[] a = new int[] {5,6,7,8,1,2,3,4};
		runTest(a);
	}

	@Test
	public void test8() {
		int[] a = new int[] {};
		runTest(a);
	}

}
