public enum Turn {
    O(0),
    X(1);

    private final int value;
    Turn(int value)
    {
        this.value=value;
    }

    public int getValue()
    {
        return value;
    }
}
