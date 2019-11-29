package fr.opendevup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TpSpringMcvApplication {

	public static void main(String[] args) {
	 ApplicationContext ctx=SpringApplication.run(TpSpringMcvApplication.class, args);
//	 ClientRepository clietRepo=ctx.getBean(ClientRepository.class);
//	 clietRepo.save(new Client("harlein", "bruno", "Bruno@harlein.com ","wasqual"));
//	 clietRepo.save(new Client("thomas", "gossart", "Thomas@Gossart.com ","wasqual"));
//	 clietRepo.findAll().forEach(cl->System.out.println(cl.getNom()));
//	 for (Client cl : clietRepo.findAll()) {
//		System.out.println(cl.getId());
//	}
	}

}
