package org.example.mashibing;

public class T_03_UseBinarySearch {
	public static void main(String[] args) {
		int[] a = { 1, 2, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		int num = 2;
		int index = -1;

		for(int start = 0, end = a.length-1; start <= end;){
			int mid = start + ((end - start) >> 1);// mid = (start+end)/2
			if (a[mid] >= num){
				index = mid;
				end = mid-1;
			}else{
				start = mid+1;
			}
		}

		System.out.println(index);

	}
}
