public class Main {
    public static void main(String[] args) {
        Product coffee = new Product("Coffee", 30000);
        Product tea = new Product("Tea", 25000);
        Product milkTea = new Product("Milk Tea", 35000);
        Product water = new Product("Water", 10000);

        Table table1 = new Table("1");
        table1.addItem(coffee);
        table1.addItem(tea);

        Table table2 = new Table("2");
        table2.addItem(milkTea);
        table2.addItem(water);
        table2.addItem(coffee);

        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.addTable(table1);
        coffeeShop.addTable(table2);

        table1.getName();
        System.out.println("Table 1 total: " + table1.getPrice() + " VND");
        table2.getName();
        System.out.println("Table 2 total: " + table2.getPrice() + " VND");
        System.out.println("Total revenue: " + coffeeShop.getTotalRevenue() + " VND");
    }
}