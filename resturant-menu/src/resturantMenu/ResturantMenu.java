package resturantMenu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ResturantMenu {

	private static	class MenuItem{

		public String name;		
		public Integer price;

		public MenuItem(String name, Integer price) {

			this.name = name;
			this.price = price;
		}

		@Override
		public String toString() {
			
			if(name.length()>25) {
				return name + "\t\t" + price +" kr";
			}else if(name.length()>20) {
				return name + "\t\t\t" + price +" kr";
			}else if(name.length()>17) {
				return name + "\t\t\t" + price +" kr";
			}else {
				return name + "\t\t\t\t" + price +" kr";
			}

		}
	}

	public static void main(String[] args) {

		ArrayList<MenuItem> menuList = readFiles();
		
		System.out.println("\nRestaurant Vegetarian Fresh\n menu: \n");
		menuList.stream().forEach(System.out::println);
				
		System.out.println("Do you want see the menu sorted by price?\n y for yes and all other no");
		Scanner sc = new Scanner(System.in);
		String bool = sc.next();
		sc.close();
		
		if(bool.equals("y")) {
			
			menuList.stream().sorted(((item1, item2) -> Integer.compare(item1.price, item2.price)))
			.forEach(System.out::println);
			
		}
		
		System.out.println("\n\nGood bye welcome back!");		
	}


	public static ArrayList<MenuItem> readFiles() {

		ArrayList<MenuItem> menuList = new ArrayList<>();

		try {
			List<String> menu = Files.lines(Paths.get("..\\resturant-menu\\files\\menu.txt"))
					.filter(line -> !line.startsWith("*"))
					.map(line -> line.replace(" kr", ""))
					.collect(Collectors.toList());

			for(int i = 0; i<menu.size(); i++) {

				int j = i + 1;				
				MenuItem item = new MenuItem(menu.get(i), Integer.parseInt(menu.get(j)));				
				menuList.add(item);
				i+=1;
			}

		} catch (IOException e) {
			
			e.printStackTrace();
		}

			return menuList;
	}
}
