public class SafeMapCreator extends MapCreator {

	public Map generate(int size) {
		return new SafeMap(size);
	}

}
