/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
	protected List setList;
  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
	  setList = new DList();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return setList.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    // Your solution here.
	  if (setList.isEmpty()) {
		  setList.insertFront(c);
	  } else {		  		 
		  try {  
			  ListNode node = setList.front();
			  while (node.isValidNode()) {
				  if (((Comparable)node.item()).compareTo(c) < 0) {
					  if (node == setList.back()) {
						  node.insertAfter(c);
						  return;
					  } else {
						  node = node.next();		  
					  }
				  } else if (((Comparable)node.item()).compareTo(c) == 0) {
					  return;
				  } else { 
					  node.insertBefore(c);
					  return;
				  }
			  }			  
		  } catch (InvalidNodeException e){
			  System.err.println(e);
		  }
	  }

  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    // Your solution here.
	  try {
		  ListNode thisNode = setList.front();
		  ListNode sNode = s.setList.front();
		  while(thisNode.isValidNode() && sNode.isValidNode()) {
			  Comparable thisItem = (Comparable)thisNode.item();
			  Comparable sItem = (Comparable)sNode.item();
			  if (thisItem.compareTo(sItem) < 0) {
				  thisNode = thisNode.next();
			  } else if (thisItem.compareTo(sItem) == 0) {
				  sNode = sNode.next();
			  } else {
				  thisNode.insertBefore(sItem);
				  sNode = sNode.next();
			  }
		  }
		  while (sNode.isValidNode()) {
			  setList.insertBack(sNode.item());
			  sNode = sNode.next();
		  }
	  } catch (InvalidNodeException e) {
		  System.err.println(e);
	  }
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    // Your solution here.
	  try {
		  ListNode thisNode = setList.front();
		  ListNode sNode = s.setList.front();
		  while (thisNode.isValidNode() && sNode.isValidNode()) {
			  Comparable thisItem = (Comparable)thisNode.item();
			  Comparable sItem = (Comparable)sNode.item();
			  if (thisItem.compareTo(sItem) < 0) {
				  if (thisNode != setList.back()) {
					  thisNode = thisNode.next();
					  thisNode.prev().remove();  
				  } else {
					  thisNode.remove();
				  }
			  } else if (thisItem.compareTo(sItem) == 0) {
				  thisNode = thisNode.next();
				  sNode = sNode.next();
			  } else {
				  sNode = sNode.next();
			  }
		  }
		  while (thisNode.isValidNode()) {
			  if (thisNode != setList.back()) {
				  thisNode = thisNode.next();
				  thisNode.prev().remove();  
			  } else {	
				  thisNode.remove();
			  }
		  }
		  
	  } catch (InvalidNodeException e) {
		  System.err.println(e);
	  }
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
	  String s = "{";
	  ListNode node = setList.front();
	  try {
		  while (node.isValidNode()) {
			  s += "  " + (node.item()).toString();
			  node = node.next();
		  }
	  } catch (InvalidNodeException e) {
		  System.err.println(e);
	  }
	  return s + "  }";
  }

  public static void main(String[] argv) {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
    System.out.println();
    System.out.println("Tesing union()");
    s.union(s2);
    System.out.println("After s.union(s2), s should be { 3 4 5 }: " + s);
    s2.union(s3);
    System.out.println("After s2.union(s3), s2 should be { 3 4 5 8 }: " + s2);
    Set s4 = new Set();
    System.out.println("Empty set s4 = " + s4);
    s.union(s4);
    System.out.println("After s.union(s4), s should be { 3 4 5 }: " + s);
    s4.union(s);
    System.out.println("After s4.union(s), s4 should be { 3 4 5 }: " + s4);

    System.out.println();
    System.out.println("Tesing intersect()");
    Set s5 = new Set();
    Set s6 = new Set();
    s6.insert(new Integer(1));
    s5.intersect(s6);
    System.out.println("{}.intersect({1}) should be { }: " + s5);
    s6.intersect(s5);
    System.out.println("{1}.intersect({}) should be { }: " + s6);
    s6.insert(new Integer(1));
    Set s7 = new Set();
    s7.insert(new Integer(1));
    s7.insert(new Integer(2));
    s6.intersect(s7);
    System.out.println("{1}.intersect({1 2}) should be { 1 }: " + s6);
    s6.insert(new Integer(2));
    s6.insert(new Integer(3));
    s6.intersect(s7);
    System.out.println("{1 2 3}.intersect({1 2}) should be { 1 2 }: " + s6);
    s6.insert(new Integer(3));
    s6.insert(new Integer(5));
    s7.insert(new Integer(4));
    s7.insert(new Integer(7));
    s7.intersect(s6);
    System.out.println("{1 2 4 7}.intersect({1 2 3 5}) should be { 1 2 }: " + s7);
    
  }
}
