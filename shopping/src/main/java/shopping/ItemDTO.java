package shopping;

public class ItemDTO {
	
	long id;
	String name;
	Product product;
	double cost;
	
	public ItemDTO(long id, String name, Product p, double cost){
		this.id = id;
		this.name = name;
		this.product = p;
		this.cost = cost;
	}

	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public Product getProduct() {
		// TODO Auto-generated method stub
		return product;
	}

	public double getUnitCost() {
		// TODO Auto-generated method stub
		return cost;
	}



}
