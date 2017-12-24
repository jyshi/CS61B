package list;

class LockDListNode extends DListNode{
	boolean isLocked;
	
	LockDListNode(Object i, DListNode p, DListNode n) {
		super(i,p,n);
		isLocked = false;
	}
	
}
