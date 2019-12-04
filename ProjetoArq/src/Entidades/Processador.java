package Entidades;

public class Processador {
	
	private Core core1;
	private Core core2;
	private Cache cache;
	
	
	public Processador() {
		super();
		this.core1 = new Core();
		this.core2 = new Core();
		this.cache = new Cache(100);
	}
	
	public Core getCore1() {
		return this.core1;
	}
	public void setCore1(Core core1) {
		this.core1 = core1;
	}
	public Core getCore2() {
		return this.core2;
	}
	public void setCore2(Core core2) {
		this.core2 = core2;
	}
	public void atualizar(int novo) {
		cache.updateLast(novo);
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
}
