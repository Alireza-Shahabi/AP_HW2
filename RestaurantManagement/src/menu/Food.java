package menu;

public class Food extends MenuItem {
    private String spiceLevel;
    private int preparationTime;

    public Food(String itemId, String name, double price, String spiceLevel, int preparationTime) {
        super(itemId, name, price, "Food");
        this.spiceLevel = spiceLevel;
        this.preparationTime = preparationTime;
    }

    public String getSpiceLevel() {
        return spiceLevel;
    }
    public void setSpiceLevel(String spiceLevel) {
        this.spiceLevel = spiceLevel;
    }

    public int getPreparationTime() {
        return preparationTime;
    }
    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    @Override
    public String getDetails() {
        return "ID: " + getItemId()
                + ", Name: " + getName()
                + ", Price: " + getPrice()
                + ", Category: " + getCategory()
                + ", Spice: " + spiceLevel
                + ", Preparation Time: " + preparationTime + "min";
    }
}
