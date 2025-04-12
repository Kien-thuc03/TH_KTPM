import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {
    private List<Table> tables;
    
    public CoffeeShop() {
        this.tables = new ArrayList<>();
    }
    
    public void addTable(Table table) {
        tables.add(table);
    }
    
    public void removeTable(Table table) {
        tables.remove(table);
    }
    
    public double getTotalRevenue() {
        double total = 0;
        for (MenuComponent table : tables) {
            total += table.getPrice();
        }
        return total;
    }
}