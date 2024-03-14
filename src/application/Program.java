package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Sale> listSales = new ArrayList<>();

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();

		// C:\Dev\ws-eclipse\desafioAnaliseDeVendas1.csv

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				listSales.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
			}

			Set<String> names = new HashSet<>();
			for (Sale s : listSales) {
				names.add(s.getSeller());
			}

			System.out.println("Total de vendas por vendedor: ");
			for (String name : names) {
				double sum = listSales.stream().filter(x -> x.getSeller().equals(name)).mapToDouble(x -> x.getTotal())
						.sum();
				System.out.println(name + " - R$ " + String.format("%.2f", sum));
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			sc.close();
		}

	}

}
