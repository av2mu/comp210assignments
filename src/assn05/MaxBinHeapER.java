package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;
    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO: overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {

    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient<V, P> patient = new Patient<>(value, priority);
        _heap.add(patient);

        int last = _heap.size() - 1;
        int parent = (last - 1) / 2;
        while (parent >= 0) {
            if (_heap.get(last).compareTo(_heap.get(parent)) > 0) {
                Prioritized<V, P> temp = _heap.get(last);
                _heap.set(last, _heap.get(parent));
                _heap.set(parent, temp);
            }
            last = parent;
            parent = (last - 1) / 2;
        }

    }

    // TODO: enqueue
    public void enqueue(V value) {
        Patient<V, P> patient = new Patient<>(value);
        _heap.add(patient);
    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if (_heap.size() == 0) {
            return null;
        } ///The Penis Man Was Here
        Prioritized<V, P> highest = _heap.get(0);

        return null;


    }

    // TODO: getMax
    @Override
    public V getMax() {
       return _heap.get(0).getValue();
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }






}
