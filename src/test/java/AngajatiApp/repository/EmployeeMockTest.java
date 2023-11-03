import static org.junit.jupiter.api.Assertions.*;

import AngajatiApp.controller.DidacticFunction;
import AngajatiApp.controller.EmployeeController;
import AngajatiApp.model.Employee;
import AngajatiApp.repository.EmployeeMock;
import AngajatiApp.validator.EmployeeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeMockTest {

    private EmployeeMock employeeRepository;
    private Employee employee;
    private EmployeeController employeeController;
    private EmployeeValidator employeeValidator;

    @BeforeEach
    public void setUp() {
        employeeRepository = new EmployeeMock();
    }

    @Test
    public void testModifyEmployeeFunction() {
        Employee employeeTest = new Employee("Vasile", "Gheorghe", "1234567890", DidacticFunction.ASISTENT, 2500d);
        employeeRepository.addEmployee(employeeTest);

        employeeRepository.modifyEmployeeFunction(employeeTest, DidacticFunction.CONFERENTIAR);

        Employee modifiedEmployee = employeeRepository.getEmployeeList().stream()
                .filter(e -> e.getId() == employeeTest.getId())
                .findFirst()
                .orElse(null);

        assertNotNull(modifiedEmployee);
        assertEquals(DidacticFunction.CONFERENTIAR, modifiedEmployee.getFunction());
    }

    @Test
    public void testModifyEmployeeFunction_Invalid() {
        Employee employeeTest = null;
        employeeRepository.addEmployee(employeeTest);

        employeeRepository.modifyEmployeeFunction(employeeTest, DidacticFunction.CONFERENTIAR);

        Employee modifiedEmployee = employeeRepository.getEmployeeList().stream()
                .filter(e -> e.getId() == employeeTest.getId())
                .findFirst()
                .orElse(null);

        assertNotNull(modifiedEmployee);
        assertEquals(DidacticFunction.CONFERENTIAR, modifiedEmployee.getFunction());
    }


    @Test
    void TC_1_addEmployee() {
        try {
            String fName = "Andrei";
            String lName = "Pop";
            String CNP = "1566455648654";
            DidacticFunction dF = DidacticFunction.ASISTENT;
            double salary = 4000.00;

            boolean result = employeeRepository.addEmployee(new Employee(fName,lName,CNP,dF,salary));
            assertEquals(true,result);
        } catch (Exception e) {
            assert(true);
        }
    }

    @Test
    void TC_3_addEmployeeCNP() {
        try {
            String fName = "Andrei";
            String lName = "Pop";
            String CNP = "94L1265489847";
            DidacticFunction dF = DidacticFunction.ASISTENT;
            double salary = 4000.00;
            boolean result = employeeRepository.addEmployee(new Employee(fName,lName,CNP,dF,salary));

            assertEquals(false,result);
        } catch (Exception e) {
            assert(true);
        }
    }

    @Test
    void TC_4_addEmployeeSalary() {
        try {
            String fName = "Andrei";
            String lName = "Pop";
            String CNP = "1564846556468";
            DidacticFunction dF = DidacticFunction.ASISTENT;
            double salary = 1D;
            boolean result = employeeRepository.addEmployee(new Employee(fName,lName,CNP,dF,salary));

            assertEquals(true,result);
        } catch (Exception e) {
            assert(true);
        }
    }


    @Test
    void TC_5_addEmployeeSalary() {
        try {
            String fName = "Andrei";
            String lName = "Pop";
            String CNP = "1564684646546";
            DidacticFunction dF = DidacticFunction.ASISTENT;
            double salary = 4000D;
            boolean result = employeeRepository.addEmployee(new Employee(fName,lName,CNP,dF,salary));

            assertEquals(true,result);
        } catch (Exception e) {
            assert(true);
        }
    }
}
