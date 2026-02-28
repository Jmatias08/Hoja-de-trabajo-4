/**
 * StackFactory - Factory Pattern to create IStack instances
 */
public class StackFactory {

    /**
     * @param type      "arraylist", "vector", or "list"
     * @param listType  "singly" or "doubly" (only used when type = "list")
     */
    public static <T> IStack<T> getStack(String type, String listType) {
        switch (type.toLowerCase()) {
            case "arraylist":
                return new ArrayListStack<>();
            case "vector":
                return new VectorStack<>();
            case "list":
                IList<T> list;
                if (listType != null && listType.equalsIgnoreCase("doubly")) {
                    list = new DoublyLinkedList<>();
                } else {
                    list = new SinglyLinkedList<>();
                }
                return new ListStack<>(list);
            default:
                throw new IllegalArgumentException("Unknown stack type: " + type);
        }
    }
}
