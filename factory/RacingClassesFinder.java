package factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import game.arenas.Arena;
import game.racers.Racer;
//@author : Tzvi Puchinsky
public class RacingClassesFinder {
	private static String GAME_PACKAGE = "game";
	private static String GAME_PACKAGE_DIR = "src/game";
	private static RacingClassesFinder instance;

	/**
	 * Singelton Constructor
	 * @return
	 * instance of the Class
	 */
	public static RacingClassesFinder getInstance() {
		if (instance == null) {
			instance = new RacingClassesFinder();
		}
		return instance;
	}

	private List<Class<?>> classList;
	private List<Class<?>> racersList;
	private List<Class<?>> arenasList;

	/**
	 * Finds all the Racing classes in the project
	 */
	private RacingClassesFinder() {
		try {
			this.classList = this.loadClasses(new File(GAME_PACKAGE_DIR), GAME_PACKAGE);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.arenasList = loadArenas();
		this.racersList = loadRacers();
	}

	/**
	 * Returns list of strings of all the arena types in the project
	 * @return
	 * List of strings of all the arenas in the project
	 */
	public List<String> getArenasList() {
		List<String> list = new ArrayList<>();
		for (Class<?> c : this.arenasList) {
			list.add(c.getName());
		}
		return list;
	}

	/**
	 * Return list of the names of all the arenas in the project
	 * @return
	 * List type string of all the Names of the arena in the project
	 */
	public List<String> getArenasNamesList() {
		List<String> list = new ArrayList<>();
		for (Class<?> c : this.arenasList) {
			String s = c.getName();
			list.add(s.substring(s.lastIndexOf('.') + 1));
		}
		return list;
	}

	/**
	 * Gets all the racers types in the project
	 * @return
	 * List type string of all the racers path's in the project
	 */
	public List<String> getRacersList() {
		List<String> list = new ArrayList<>();
		for (Class<?> c : this.racersList) {
			list.add(c.getName());
		}
		return list;
	}

	/**
	 * Gets the names of racer types
	 * @return
	 * Return list of string Racer Names
	 */
	public List<String> getRacersNamesList() {
		List<String> list = new ArrayList<>();
		for (Class<?> c : this.racersList) {
			String s = c.getName();
			list.add(s.substring(s.lastIndexOf('.') + 1));
		}
		return list;
	}

	private List<Class<?>> loadArenas() {
		List<Class<?>> list = new ArrayList<>();
		for (Class<?> cls : classList) {
			if (Arena.class.isAssignableFrom(cls) && (Modifier.isAbstract(cls.getModifiers()) == false)) {
				list.add(cls);
			}
		}
		return list;
	}

	private List<Class<?>> loadClasses(File directory, String packageName)
			throws ClassNotFoundException, FileNotFoundException {
		List<Class<?>> list = new ArrayList<Class<?>>();

		if (!directory.exists()) {
			throw new FileNotFoundException();
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				list.addAll(loadClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".java")) {
				list.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 5)));
			}
		}
		return list;
	}

	private List<Class<?>> loadRacers() {
		List<Class<?>> list = new ArrayList<>();
		for (Class<?> cls : classList) {
			if (Racer.class.isAssignableFrom(cls) && (Modifier.isAbstract(cls.getModifiers()) == false)) {
				list.add(cls);
			}
		}
		return list;
	}

}
