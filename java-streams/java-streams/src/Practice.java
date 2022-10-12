import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice {

	public static void main(String[] args) {
		
		List<Employee> employees = Arrays.asList(
				new Employee(1, "eshita", 1001, "ACTIVE", 100, "female"),
				new Employee(2, "pushpak", 1002, "ACTIVE", 40, "male"),
				new Employee(3, "mayank", 1002, "ACTIVE", 30, "male"),
				new Employee(4, "harsh", 1001, "ACTIVE", 50, "male"),
				new Employee(4, "harshita", 1001, "ACTIVE", 60, "female"),
				new Employee(5, "mayank", 1003, "ACTIVE", 80, "male")
		);
		
		final List<List<Employee>> DUNIYA = new ArrayList<>();
		
		List<Employee> company1 = Arrays.asList(
				new Employee(1, "eshita", 1001, "ACTIVE", 100, "female"),
				new Employee(2, "pushpak", 1002, "ACTIVE", 40, "male")
		);
		List<Employee> company2 = Arrays.asList(
				new Employee(3, "mayank", 1002, "ACTIVE", 30, "male"),
				new Employee(4, "harsh", 1001, "ACTIVE", 50, "male")
		);
		List<Employee> company3 = Arrays.asList(
				new Employee(4, "harshita", 1001, "ACTIVE", 60, "female"),
				new Employee(5, "mayank", 1003, "ACTIVE", 80, "male")
		);
		DUNIYA.add(company1);
		DUNIYA.add(company2);
		DUNIYA.add(company3);
		
		/**allMatch**/
		System.out.println(checkIfAllEmployeesAreFemale(employees));
		System.out.println(checkIfAllEmpHaveSalaryGreaterThan50AndAllAreFemales(employees));
		System.out.println(allMatchOnEmptyStreamReturnTrue(new ArrayList<String>()));
		
		/**anyMatch**/
		System.out.println(checkIfAnyEmployeeIsMale(employees));
		System.out.println(anyMatchOnEmptyStreamReturnFalse(new ArrayList<String>()));
		
		/**count**/
		System.out.println(countNumberOfFemaleEmployees(employees));
		
		/**distinct**/
		System.out.println(getDistinctEmployeeNames(employees));
		
		/**findAny**/
		System.out.println(getAnyEmployeeWhoHasSalaryGreaterThan50(employees).map(Employee::getName).orElse(null));
		
		/**findFirst**/
		System.out.println(getEmployeeWhoIsMale(employees).map(Employee::getName).orElse(null));
		
		/**flatMap**/
		System.out.println(getAllEmployeesFromDuniyaWhoAreMale(DUNIYA));
	}
	
	private static List<String> getAllEmployeesFromDuniyaWhoAreMale(List<List<Employee>> DUNIYA) {
		return DUNIYA.stream().flatMap(object -> object.stream())
				.filter(employee -> employee.getGender().equals("male")).map(employee -> employee.getName())
				.collect(Collectors.toList());
	}

	private static Optional<Employee> getEmployeeWhoIsMale(List<Employee> employees) {
		return employees.stream().filter(employee -> employee.getGender().equals("male")).findFirst();
	}

	private static Optional<Employee> getAnyEmployeeWhoHasSalaryGreaterThan50(List<Employee> employees) {
		return employees.stream().filter(employee -> employee.getSalary() > 99).findAny();
	}

	private static List<String> getDistinctEmployeeNames(List<Employee> employees) {
		return employees.stream().distinct().map(employee -> employee.getName()).collect(Collectors.toList());
	}

	private static long countNumberOfFemaleEmployees(List<Employee> employees) {
		return employees.stream().filter(employee -> employee.getGender().equals("female")).count();
	}

	private static boolean anyMatchOnEmptyStreamReturnFalse(List<String> employees) {
		return employees.stream().anyMatch(employee -> true);
	}

	private static boolean checkIfAnyEmployeeIsMale(List<Employee> employees) {
		return employees.stream().anyMatch(employee -> employee.getGender().equals("male"));
	}

	private static boolean allMatchOnEmptyStreamReturnTrue(List<String> employees) {
		return employees.stream().allMatch(employee -> false);
	}

	public static boolean checkIfAllEmployeesAreFemale(List<Employee> employees) {
		return employees.stream().allMatch(employee -> employee.getGender().equals("female"));
	}
	
	public static boolean checkIfAllEmpHaveSalaryGreaterThan50AndAllAreFemales(List<Employee> employees) {
		//Predicate<Employee> salaryGreaterThan50 = employee -> employee.getSalary() > 50;
		//Predicate<Employee> employeesAreFemale = employee -> employee.getGender().equals("female");
		//return employees.stream().allMatch(salaryGreaterThan50.and(employeesAreFemale));
		return employees.stream().allMatch(employee -> employee.getSalary() > 50 && employee.getGender().equals("female"));
	}
}
