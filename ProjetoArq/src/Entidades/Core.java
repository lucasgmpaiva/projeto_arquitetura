package Entidades;



public class Core {
	
	private Cache cache;
	
	public Core() {
		this.cache = new Cache(50);
	}

	public Core(Cache cache) {
		super();
		this.cache = cache;
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
	

}
