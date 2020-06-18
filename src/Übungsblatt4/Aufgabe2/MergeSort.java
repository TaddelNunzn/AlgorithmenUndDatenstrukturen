package Übungsblatt4.Aufgabe2;

import java.util.Random;

public class MergeSort {

	// buffer for merge operation.
	int[] b;
	
	public void sort(int[] a) {
		mergeSort(a,0,a.length);
	}

	public void mergeSort(int[] a, int left, int right){
		if(left>=right)return;

		int middle=(left+right)/2;

		mergeSort(a,left,middle);
		mergeSort(a,middle+1,right);

		b=new int[right-left];
		int lz=left;
		int rz= middle+1;//vllt +1

		for(int i=0;i<b.length;i++){
			if(lz<middle && rz<right){
				if(a[lz]<=a[rz]){
					b[i]=a[lz];
					lz++;
				}else{
					b[i]=a[rz];
					rz++;
				}
			}else if(lz<middle&&rz>=right){
				b[i]=a[lz];
				lz++;
			}else if(lz>=middle && rz<right){
				b[i]=a[rz];
				rz++;
			}
		}
		for(int i=0;i<b.length;i++){
			a[left+i]=b[i];
		}
	}
	
	public static void swap(int[] a, int pos1, int pos2) {
		int tmp = a[pos1];
		a[pos1] = a[pos2];
		a[pos2] = tmp;
	}

	// always the same pseudo random numbers!
	private static final Integer DEFAULT_SEED = Integer.valueOf(654321);

	public static int[] createRandomArray(int n) {
		int[] a = new int[n];
		for (int i=0; i<n; ++i) 
			a[i] = i;
		// shuffle randomly.
		Random rand = new Random(DEFAULT_SEED*n); 
		for (int i=0; i<n; ++i) {
			int pos = rand.nextInt(n);
			swap(a, i, pos);
		}
		return a;
	}

	public static void runTest(int n) {

		long startTime;
		long endTime;

		int[] a = createRandomArray(n);
		MergeSort sort = new MergeSort();
		startTime = System.currentTimeMillis();
		sort.sort(a);
		endTime = System.currentTimeMillis();
		System.out.format(
				"[n=%d] : %d ms.\n",
				n, (endTime - startTime));
	}

	public static void main(String[] args) {
	}
	
}
