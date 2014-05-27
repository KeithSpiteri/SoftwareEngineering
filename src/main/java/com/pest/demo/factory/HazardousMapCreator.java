package factory;

public class HazardousMapCreator extends MapCreator {

	public Map generate(int size) {
		return HazardousMap.getInstance(size);
	}

}
