package factory;

public class SafeMapCreator extends MapCreator {

	public Map generate(int size) {
		return SafeMap.getInstance(size);
	}

}
