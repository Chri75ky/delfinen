package com.company.domain.Member;

public class QuickSort {
    public static void sort(CompSwimmer[] array) {
        quicksort(array, 0, array.length-1);
    }

    private static void quicksort(CompSwimmer[] a, int p, int r) {
        if( p < r ) {
            int i = p-1;
            for (int j=p; j<r; j++) {


                if (a[j].compareTo(a[r]) <= 0) {
                    i++;
                    CompSwimmer b = a[i];
                    a[i] = a[j];
                    a[j] = b;
                }
            }
            CompSwimmer b = a[i+1];
            a[i+1] = a[r];
            a[r] = b;

            quicksort(a,p,i);
            quicksort(a,i+2,r);
        }
    }
}
