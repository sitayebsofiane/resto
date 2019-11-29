package fr.opendevup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.opendevup.dao.ClientRepository;
import fr.opendevup.entities.Client;

@SpringBootApplication
public class TpSpringMcvApplication {

	public static void main(String[] args) {
	 ApplicationContext ctx=SpringApplication.run(TpSpringMcvApplication.class, args);
	 ClientRepository clietRepo=ctx.getBean(ClientRepository.class);
	 clietRepo.save(new Client("massia", "assia", "assia.noureddine@yahoo.fr","roubaix"));
	// clietRepo.save(new Client("thomas", "thwin", "Thomas@thwin.com ","roubaix"));
	 clietRepo.findAll().forEach(cl->System.out.println(cl.getNom()));
	 for (Client cl : clietRepo.findAll()) {
		System.out.println(cl.getNom());
	}
	}

}
