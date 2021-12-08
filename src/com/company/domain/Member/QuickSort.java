package com.company.domain.Member;

import java.util.ArrayList;

public class QuickSort {
    public static void sort(ArrayList<CompSwimmer> array) {
        quicksort(array, 0, array.size()-1);
    }

    //metoden som bruges til at sortere i arraylisten af konkurrencesvømmere alt efter hvem der har den bedste tid
    private static void quicksort(ArrayList<CompSwimmer> a, int p, int r) {
        if( p < r ) {
            int i = p-1;
            for (int j=p; j<r; j++) {


                if (a.get(j).compareTo(a.get(r)) <= 0) {
                    i++;
                    CompSwimmer b = a.get(i);
                    a.set(i, a.get(j));
                    a.set(j, b);
                }
            }
            CompSwimmer b = a.get(i + 1);
            a.set(i + 1, a.get(r));
            a.set(r, b);

            quicksort(a,p,i);
            quicksort(a,i+2,r);
        }
    }
}
