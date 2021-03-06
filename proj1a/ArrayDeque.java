public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int front;
    private int rear;
    private int refactor;


    /**
     * creates an empty list
     */

    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = rear = -1;
        size = 0;
        refactor = 2;
    }

    public void addFirst(T item) {
        if (isEmpty() == true) {
            front = rear = 0;
            items[0] = item;
        } else {
            if (minusOne(front) == rear && size == items.length) {
                addFirstResizing(item);
                front = minusOne(front);
                items[front] = item;
            } else {
                front = minusOne(front);
                items[front] = item;
            }
        }
        size++;
    }

    /* need resizing */
    public void addLast(T item) {
        if (isEmpty() == true) {
            front = rear = 0;
            items[0] = item;

        } else {
            if (plusOne(rear) == items.length || plusOne(rear) == front) {
                addLastResizing(item);
                rear = plusOne(rear);
                items[rear] = item;

            } else {
                rear = plusOne(rear);
                items[rear] = item;
            }
        }
        size++;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }

    }

    public int size() {
        return size;
    }

    public void printDeque() {
        System.out.print("Output: ");
        if (front == rear) {
            System.out.println(items[0]);
        } else if (front > rear) {
            /* front to the end of array*/
            for (int i = front; i < items.length; i++) {
                System.out.print(items[i] + ", ");
            }
            /* beginning to rear */
            for (int i = 0; i <= rear; i++) {
                System.out.print(items[i] + ", ");
            }
        } else {
            /* from front to rear*/
            for (int i = front; i <= rear; i++) {
                System.out.print(items[i] + ", ");
            }
        }
    }

    /* no resizing is needed */
    public T removeFirst() {

        if (isEmpty() == true) {
            size = 0;
            return null;
        } else if (size == 1) {
            T removedItem = items[front];
            items[0] = null;
            size = 0;
            return removedItem;
        } else {
            T removedItem = items[front];
            items[front] = null;
            front = plusOne(front);
            size--;
            return removedItem;
        }
    }

    public T removeLast() {

        if (isEmpty() == true) {
            size = 0;
            return null;
        } else if (size == 1) {
            T removedItem = items[rear];
            items[0] = null;
            size = 0;
            return removedItem;
        } else {
            T removedItem = items[rear];
            items[rear] = null;
            rear = minusOne(rear);
            size--;
            return removedItem;
        }

    }

    public T get(int index) {

        if (index < 0 || index >= size) {
            return null;
        } else {
            int current = (front + index) % items.length;
            return items[current];
        }

    }


    private ArrayDeque ArrayDeque(ArrayDeque other) {
        ArrayDeque newArr = new ArrayDeque();
        if (other.isEmpty() == true) {
            return newArr;
        } else {
            for (int i = 0; i < other.items.length; i++) {
                newArr.items[i] = other.items[i];
            }
            return newArr;
        }
    }

    private int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        } else {
            return index - 1;
        }

    }

    /* resizing front == rear */
    private T[] addFirstResizing(T item) {
        T[] newArr = (T[]) new Object[items.length * refactor];
        int ptr = 1;
        if (front > rear) {  /** add first */
            for (int i = front; i < items.length; i++) {
                newArr[ptr] = items[i];
                ptr++;
            }
            for (int i = 0; i <= rear; i++) {
                newArr[ptr] = items[i];
                ptr++;
            }
            rear = ptr - 1;
        } else { /* front < rear */
            for (int i = front; i <= rear; i++) {
                newArr[ptr] = items[i];
                ptr++;
            }
        }
        front = 1;
        items = newArr;
        return newArr;
    }

    private T[] addLastResizing(T item) {
        T[] newArr = (T[]) new Object[items.length * refactor];
        int ptr = 0;
        if (front > rear) {  /** add last */
            for (int i = front; i < items.length; i++) {
                newArr[ptr] = items[i];
                ptr++;
            }
            for (int i = 0; i <= rear; i++) {
                newArr[ptr] = items[i];
                ptr++;
            }
            rear = ptr - 1;
        } else { /* front < rear */
            for (int i = front; i <= rear; i++) {
                newArr[ptr] = items[i];
                ptr++;
            }
        }
        front = 0;
        items = newArr;
        return newArr;
    }

}
