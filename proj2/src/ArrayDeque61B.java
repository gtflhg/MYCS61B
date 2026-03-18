import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{
    private int size;
    private T[] elements;
    private int nextFirst;

    public ArrayDeque61B() {
        size = 0;
        nextFirst = 0;
        elements = (T[]) new Object[1000];
    }

    private int getActualIndex(int index){
        int actualIndex = index + nextFirst + 1;

        return getIndexInCircle(actualIndex);
    }

    private int getIndexInCircle(int index){
        if(index >= 1000){
            return index - 1000;
        }else if(index < 0){
            return index + 1000;
        }
        return index;
    }

    private boolean validateIndex(int index){
        return index >= 0 && index < size;
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        int index = getActualIndex(-1);
        elements[index] = x;
        size++;
        nextFirst = getIndexInCircle(nextFirst - 1);
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        int index = getActualIndex(size);
        elements[index] = x;
        size++;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++){
            returnList.add(elements[getActualIndex(i)]);
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return the element at the front of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getFirst() {
        return elements[getActualIndex(0)];
    }

    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getLast() {
        return elements[getActualIndex(size-1)];
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if(size == 0){
            return null;
        }
        T temp = elements[getActualIndex(0)];
        elements[getActualIndex(0)] = null;
        size--;
        nextFirst = getIndexInCircle(nextFirst + 1);
        return temp;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (size == 0){
            return null;
        }
        T temp = elements[getActualIndex(size-1)];
        elements[getActualIndex(size-1)] = null;
        size--;
        return temp;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (!validateIndex(index)||size==0){
            return null;
        }
        return elements[getActualIndex(index)];
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for ArrayDeque61B.");
    }

}
