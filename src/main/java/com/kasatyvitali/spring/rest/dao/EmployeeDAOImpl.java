package com.kasatyvitali.spring.rest.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kasatyvitali.spring.rest.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
	
	//Будет автоматически внедрена зависимость к бинам в applicationContex
	//отвечающая за подлкючения к БД
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional //Передаёт Spring'у ответственность за открытие/закрытие транзакций
	public List<Employee> getAllEmployees() {	
		
		Session session = sessionFactory.getCurrentSession();
		
		//Query<Employee> query = session.createQuery("from Employee", Employee.class);
		//List<Employee> allEmployees = query.getResultList();		
		//Более короткий вариант получения списка из БД
		List<Employee> allEmployees = session.createQuery("from Employee", Employee.class)
				.getResultList();	
		
		return allEmployees;
	}

	@Override
	public void saveEmployee(Employee employee) {	
		
		Session session = sessionFactory.getCurrentSession();
		//saveOrUpdate - если поступает работник с существующим id, то строка в БД переписывает
		//иначе - добавляется новая запись
		//в нашем случае при заполнении анкеты нового сотрудника его id не вводится
		//а автоматически присваивается id=0(равно новый сотрудник) и hibernate сам добавит юзера в базу
		session.saveOrUpdate(employee);		
	}

	@Override
	public Employee getEmployee(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Employee employee = session.get(Employee.class, id);
		
		return employee;
	}

	@Override
	public void deleteEmployee(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Query<Employee>	query = session.createQuery("delete from Employee where id=:employeeId");
		//Подставляем id не напрямую, а через setParametr чтобы обезопасить от sql-инъекций, 		
		query.setParameter("employeeId", id);
		query.executeUpdate();
	}

}
