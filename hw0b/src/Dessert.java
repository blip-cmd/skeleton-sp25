public class Dessert {
    int flavour;
    int price;
    static int numDesserts = 0; // to keep track number of times created

    public Dessert(int flavour, int price){
        this.flavour = flavour;
        this.price = price;
        numDesserts ++;
    }

    public void printDessert(){
        System.out.println(flavour +" "+ price + " " + numDesserts);
    }

    public static void main(String[] args){
        System.out.println("I love dessert!");

        Dessert fruit = new Dessert(2, 5);
        Dessert salad = new Dessert(1, 10);

        fruit.printDessert();
        salad.printDessert();
    }
}
