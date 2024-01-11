package org.handson.Question2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayExchange<T> {
    /**
     * This function exchanges two elements in an array.
     *
     * @param  array         the array in which elements should be exchanged
     * @param  elementOne    the first element to be exchanged
     * @param  elementTwo    the second element to be exchanged
     * @return               the modified array with elements exchanged
     */
    public T[] arrayExchange(T[] array, T elementOne, T elementTwo) {
        List<T> listOfArray = new ArrayList<>(Arrays.asList(array));
        Collections.replaceAll(listOfArray, elementOne, elementTwo);
        Collections.replaceAll(listOfArray, elementTwo, elementOne);
        return listOfArray.toArray(array);
    }

    /**
     * A description of the entire Java function.
     *
     * @param  args      	the command line arguments
     */
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7};
        ArrayExchange<Integer> arrayExchange = new ArrayExchange<>();
        Integer[] array1 = arrayExchange.arrayExchange(array, 1, 3);
        System.out.println(Arrays.toString(array1));
    }
}
