public class SpecialItem {
    private String item;
    private int delay;
    private String textColor;

    public SpecialItem(String item, int delay, String color){
        this.item = item;
        this.delay = Math.max(1, delay);
        this.textColor = color;
    }

    public String getItem() {
        return item;
    }

    public int getDelay() {
        return delay;
    }

    public String getTextColor() {
        return textColor;
    }
}
