package main.java.com.pest.demo.factory;

public class MapCreator {

	public MapCreator() {

	}

	public Map generate(int type, int size) {

		MapCreator creator = findCreatorForType(type);
		return creator.generate(size);

	}

	public Map generate(int size) {
		return new SafeMapCreator().generate(size);
	}

	public MapCreator findCreatorForType(int type) {
		MapCreator creator = null;

		switch (type) {
		case 1:
			creator = new SafeMapCreator();
			break;
		case 2:
			creator = new HazardousMapCreator();
			break;
		}

		return creator;
	}

}
