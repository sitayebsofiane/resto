package fr.opendevup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import fr.opendevup.dao.ClientRepository;
import fr.opendevup.dao.ProduitRepository;
import fr.opendevup.entities.Produit;

@SpringBootApplication
public class TpSpringMcvApplication {

	public static void main(String[] args) {
	 ApplicationContext ctx=SpringApplication.run(TpSpringMcvApplication.class, args);
	 @SuppressWarnings("unused")
	ClientRepository clientRepo=ctx.getBean(ClientRepository.class);
	 ProduitRepository produitRepo=ctx.getBean( ProduitRepository.class);
	 Page<Produit> produits= produitRepo.findAll(PageRequest.of(0,4));
	 for (Produit produit : produits) {
		System.out.println(produit.getNom());
	}
//	clietRepo.save(new Client("kivine", "je saispas", "mayh.tunis@yahoo.fr","roubaix", "123"));
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

	
	}
	}


