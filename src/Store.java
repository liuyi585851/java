public class Store {
    private Product[] products;
    private int total;

    public Store(int numberItems){
        products = new Product[numberItems];
        total = 0;
    }

    private boolean isFull(){
        return total == products.length;
    }

    private boolean isEmpty(){
        return total == 0;
    }

    public boolean add(Product product){
        if(isFull())    return false;
        products[total++] = product;
        return true;
    }

    public String listProducts(){
        if(isEmpty())   return "No products";
        String text = "";
        for(int i = 0;i < total;i++){
            text += i + ": " + products[i] + "\n";
        }
        return text;
    }

    public String listCurrentProducts(){
        if(isEmpty())   return "No products in the store";
        String text = "";
        for(int i = 0;i < total;i++){
            if(products[i].isInCurrentProductLine()){
                text += i + ": " + products[i] + "\n";
            }
        }
        return text;
    }

    public Product cheapestProduct(){
        if(isEmpty())   return null;
        Product cheapestproduct = products[0];
        for(int i = 1;i < total;i++){
            if(products[i].getUnitCost() < cheapestproduct.getUnitCost()){
                cheapestproduct = products[i];
            }
        }
        return cheapestproduct;
    }

    public double averageProductPrice(){
        if(isEmpty())   return -1;
        double sum = 0;
        for(int i = 0;i < total;i++){
            sum += products[i].getUnitCost();
        }
        return sum/total;
    }

    public String listProductsAboveAPrice(double price){
        if(isEmpty())   return "No products in the store";
        String text = "";
        for(int i = 0;i < total;i++){
            if(products[i].getUnitCost() > price){
                text += i + ": " + products[i] + "\n";
            }
        }
        return text;
    }

}
