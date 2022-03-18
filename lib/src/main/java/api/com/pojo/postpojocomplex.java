package api.com.pojo;
import java.util.List;

public class postpojocomplex {
	private String name;
	private List<String> jobs;
	private List<cityModels> citymodels;

	public List<cityModels> getCitymodels() {
		return citymodels;
	}
	public void setCitymodels(List<cityModels> citymodels) {
		this.citymodels = citymodels;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getJobs() {
		return jobs;
	}
	public void setJobs(List<String> jobs) {
		this.jobs = jobs;
	}
	
}
