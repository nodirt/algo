package algo.sort.comparison;

import java.util.Comparator;

import algo.sort.SortingAlgorithm;

public abstract class ComparisonSorting<E> extends SortingAlgorithm<E> {
	private Comparator<E> _comparator = new algo.util.DefaultComparator<E>();
	
	public Comparator<E> getComparator() {
	    return _comparator;
	}
	public void setComparator(Comparator<E> comparator) {
	    if (comparator == null) {
	        throw new IllegalArgumentException("comparator must not be null");
	    }
	    
	    _comparator = comparator;
	}
	
	protected int compare(E a, E b) {
	    return _comparator.compare(a, b);
	}
}
