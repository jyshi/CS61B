package list;

public class LockDList extends DList {
	public void lockNode(DListNode node) {
		((LockDListNode)node).isLocked = true;
	}
	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
		return new LockDListNode(item, prev, next);
	}
	public void remove(DListNode node) {
		if (((LockDListNode)node).isLocked) {
			return;
		}
		super.remove(node);

	}

}
