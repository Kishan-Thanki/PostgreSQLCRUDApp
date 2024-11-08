package Models;

public class State {
	private int state_id;
	private long population;
	private String name;
	private float literacy_rate;
	
	public State(int state_id,long population, String name, float literacy_rate) {
		super();
		this.state_id= state_id;
		this.population = population;
		this.name = name;
		this.literacy_rate = literacy_rate;
	}
	public long getPopulation() {
		return population;
	}
	public void setPopulation(long population) {
		this.population = population;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getLiteracy_rate() {
		return literacy_rate;
	}
	public void setLiteracy_rate(float literacy_rate) {
		this.literacy_rate = literacy_rate;
	}
}
	
