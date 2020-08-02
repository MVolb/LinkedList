public class Element<T> {
	public T data; //Content of the node.
	public Element<T> next; //Pointer to the next node.
	public Element<T> prev; //Pointer to the previous node.
	
	public Element(T data) {
		this.data = data;
	}

}


