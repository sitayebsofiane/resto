package fr.opendevup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.opendevup.dao.ClientRepository;

@SpringBootApplication
public class TpSpringMcvApplication {

	public static void main(String[] args) {
	 ApplicationContext ctx=SpringApplication.run(TpSpringMcvApplication.class, args);
	@SuppressWarnings("unused")
	ClientRepository clietRepo=ctx.getBean(ClientRepository.class);
//	 clietRepo.save(new Client("kivine", "je saispas", "mayh.tunis@yahoo.fr","roubaix"));
//	 clietRepo.save(new Client("farid", "box", "farid@box.com ","roubaix"));
//	 clietRepo.save(new Client("kivine", "je saispas", "mayh.tunis@yahoo.fr","roubaix"));
//	 clietRepo.save(new Client("farid", "box", "farid@box.com ","roubaix"));
//	 clietRepo.save(new Client("kivine", "je saispas", "mayh.tunis@yahoo.fr","roubaix"));
//	 clietRepo.save(new Client("farid", "box", "farid@box.com ","roubaix"));
//	 clietRepo.save(new Client("kivine", "je saispas", "mayh.tunis@yahoo.fr","roubaix"));
//	 clietRepo.save(new Client("farid", "box", "farid@box.com ","roubaix"));
//	 clietRepo.save(new Client("kivine", "je saispas", "mayh.tunis@yahoo.fr","roubaix"));
//	 clietRepo.save(new Client("farid", "box", "farid@box.com ","roubaix"));
//	 clietRepo.save(new Client("kivine", "je saispas", "mayh.tunis@yahoo.fr","roubaix"));
//	 clietRepo.save(new Client("farid", "box", "farid@box.com ","roubaix"));
//	 clietRepo.save(new Client("kivine", "je saispas", "mayh.tunis@yahoo.fr","roubaix"));
//	 clietRepo.save(new Client("farid", "box", "farid@box.com ","roubaix"));
//	 
	
	}
	}


