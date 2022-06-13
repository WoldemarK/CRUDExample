package com.example.crudexample.service;

import com.example.crudexample.model.Employee;
import com.example.crudexample.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Показать всех сотрудников компании
     *
     * @return
     */
    public List<Employee> getAllEmployee(Employee employee) {
        return employeeRepository.findAll();
    }

    /**
     * Происходит поиск сотрудника по его ID, если такого ID нет выводиться ошибка
     *
     * @param id
     * @return
     */
    public Employee getEmployeeById(Long id) {
        Optional<Employee> emp = employeeRepository.findById(id);

        if (emp.isPresent()) {
            return emp.get();
        }
        throw new EntityNotFoundException("Не удается найти ни одного сотрудника с указанным идентификатором");
    }

    /**
     * Происходит поиск сотрудника по его имени, если такого имени нет выводиться ошибка
     *
     * @param name
     * @return
     */
    public Employee getEmployeeByName(String name) {
        Optional<Employee> emp = employeeRepository.findByName(name);

        if (emp.isPresent()) {
            return emp.get();
        }
        throw new EntityNotFoundException("Не удается найти ни одного сотрудника с указанным именем");
    }

    /**
     * Добавление сотрудника
     */
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Удаление сотрудника по ID
     */
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    /**
     * Редактирование данных сотрудника по ID
     */
//    public Employee updateEmployee(Employee emp, Long id) {
//        Optional<Employee> empl = employeeRepository.findById(id);
//
//        if (empl.isPresent()) {
//            throw new EntityNotFoundException("Нет такого сотрудника");
//        }
//        Optional<Employee> employee = employeeRepository.findById(id);
//
//       employee.setName(emp.getName());
//       employee.setCompany(emp.getCompany());
//       employee.setEmail(emp.getEmail());
//       employee.setPost(emp.getPost());
//       employee.setPhoneNumber(emp.getPhoneNumber());
//       employee.setInnOfTheCompany(emp.getInnOfTheCompany());
//
//
//    }
}
