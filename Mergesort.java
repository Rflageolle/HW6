/*
 * Ryan Flageolle
 * Computer Science
 * November 21, 2017
 * HW6
 */

/** The Mergesort class is where the sort methods take place **/

public class Mergesort {

    /** This method is the wrapper method **/
    public static <T extends Comparable<T>> void sort(T[] a) {
	       mergesort(a, 0, a.length-1);
    }

    /** This method is the recusive call method **/
    private static <T extends Comparable<T>> void mergesort (T[] a, int i, int j) {
	       if (j-i < 1) {return;}
	       int mid = (i+j)/2;
	       mergesort(a, i, mid);
	       mergesort(a, mid+1, j);
	       merge(a, i, mid, j);
    }

    
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void  merge(T[] a, int p, int mid, int q) {

	       Object[] tmp = new Object[q-p+1];
	       int i = p;
	       int j = mid+1;
	       int k = 0;
	       while (i <= mid && j <= q) {
	          if (a[i].compareTo(a[j])<=0)
		             tmp[k] = a[i++];
	          else
		             tmp[k] = a[j++];
	               k++;
	       }
	       if (i <= mid && j > q) {
	            while (i <= mid)
		          tmp[k++] = a[i++];
	       } else {
	            while (j <= q)
		          tmp[k++] = a[j++];
	       }
	       for (k = 0; k < tmp.length; k++) {
	            a[k+p] = (T)(tmp[k]); // by casting the Object[] to that of t usually generates a warning which was handled at the begining of the method
	       }
    }

}
