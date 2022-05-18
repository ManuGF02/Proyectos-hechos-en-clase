package Pepe;

import org.hibernate.query.Query;
import java.util.Date;
import java.util.List;
import org.hibernate.*;

public class EjercicioFinal {

	public static void main(String[] args) {
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
		Session sesion = sessionFactory.openSession();
		sesion.beginTransaction();
		
		//Creo los objetos de Prácticas
		Practicas p1 = new Practicas();
			p1.setApellido("Ibisate");
			p1.setComision(123);
			p1.setDeptno(10);
			p1.setEmpno(300);
			p1.setFechalta(new Date(2002,12,23));
			p1.setJefe(782);
			p1.setOficio("EMPLEADO");
			p1.setSueldo(23000);
			sesion.save(p1);
		Practicas p2 = new Practicas();
			p2.setApellido("Ugarte");
			p2.setComision(124);
			p2.setDeptno(20);
			p2.setEmpno(301);
			p2.setFechalta(new Date(2008, 3, 30));
			p2.setJefe(782);
			p2.setOficio("VENDEDOR");
			p2.setSueldo(12000);
			sesion.save(p2);
		Practicas p3 = new Practicas();
			p3.setApellido("Sarasua");
			p3.setComision(1123);
			p3.setDeptno(30);
			p3.setEmpno(302);
			p3.setFechalta(new Date(2010, 9, 25));
			p3.setJefe(782);
			p3.setOficio("ANALISTA");
			p3.setSueldo(20000);
			sesion.save(p3);
			
		//Insertar mediante Objetos de hibernate y Save
		Query q  = sesion.createQuery("from Practicas");
		List<Practicas> resultados = q.list();
		for(Practicas practica : resultados){
			Practicas p = sesion.load(Practicas.class, practica.getEmpno());
			Emp e = new Emp();
			e.setEmpno(p.getEmpno());
			e.setApellido(p.getApellido());
			e.setOficio(p.getOficio());
			e.setFechalta(p.getFechalta());
			e.setSueldo(p.getSueldo());
			e.setComision(p.getComision());
			sesion.save(e);
		}
		
		//Insertar mediante lenguaje de Mysql
		Query q2 = sesion.createQuery("insert into Emp select * from Practicas");
		int resultados2 = q2.executeUpdate();
			
		sesion.getTransaction().commit();
		sesion.close();
		sessionFactory.close();
	}

}
