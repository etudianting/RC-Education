package com.rceduc.gestionentites;

import com.rceduc.gestionentites.dao.EntiteRepository;
import com.rceduc.gestionentites.dao.OrganisationRepository;
import com.rceduc.gestionentites.dao.ThematiqueRepository;

import com.rceduc.gestionentites.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.Resource;

@EnableTransactionManagement
@EnableEurekaClient
@EnableSwagger2WebMvc
@SpringBootApplication
@EnableFeignClients("com.rceduc.gestionentites")
@EnableDiscoveryClient
@EnableJpaAuditing
public class GestionentitesApplication  {

	public static void main(String[] args) {
		SpringApplication.run(GestionentitesApplication.class, args);
	}

	@Resource
	FilesStorageService storageService;
	@Autowired
	EntiteRepository entiteRepository;

	@Autowired
	OrganisationRepository organisationRepository;
	@Autowired
	ThematiqueRepository themaRepository ;




	/*@Bean
	CommandLineRunner start(AccountService accountService) {

		return args -> {


			accountService.save(new AppRole(null, "USER"));
			accountService.save(new AppRole(null, "RESPONSABLE"));
			accountService.save(new AppRole(null, "INSPECTEUR"));
			accountService.save(new AppRole(null, "ADMIN"));

			//AJOUTER DES UTILSATEURS
			Stream.of("user1", "user2", "user3", "user4", "admin").forEach(u -> {
				accountService.saveUser(u, "1234", "1234");


			});
			// ajouter pour l'utilisateur Admin le role admin et c parceque par defaut il a le role user sur la BDD on a que le role 1 ,
			// apres avoir enregiter il obtient 2 roles 1 user et 2 admin
			accountService.addRoleToUser("admin", "ADMIN");

		};

	}


	@Override
	public void run(String... args) throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (entiteRepository.count() == 0) {
			// save a list of Customers
			entiteRepository.saveAll(Arrays.asList(new Entite((long) 122, "alerte 1 de passasion", "alerte", "1.1.0", "farouk", "f@gmail.com",
					"moh", "m@hotmail.fr", df.parse("12/10/2019"), "fich 1", null)));

			entiteRepository.saveAll(Arrays.asList(new Entite((long) 142, "alerte 2 de com", "alerte", "2.1.0", "mousa", "s@gmail.com",
					"hela", "m@hotmail.fr", df.parse("11/02/2019"), "fich 1", null)));

			entiteRepository.saveAll(Arrays.asList(new Entite((long) 123, "offre d'emploi", "emploi", "1.1.0", "lisa", "lisa@gmail.com",
					"farid", "farid.touj@hotmail.fr", df.parse("05/07/2020"), "offre1", null)));
		}
	}
*/

	//ajout des organisations
/*

	@Override
	public void run(String... args) throws Exception {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (organisationRepository.count() == 0) {
			// save a list of Customers
			organisationRepository.saveAll(Arrays.asList(new Organisation((long) 12, "RCEDUC",
					"premier organe", df.parse("12/07/2020"))));

			organisationRepository.saveAll(Arrays.asList(new Organisation((long) 06, "RCE",
					"premier test", df.parse("02/11/2020"))));

			organisationRepository.saveAll(Arrays.asList(new Organisation((long) 30, "RC-EDUC",
					"offres1", df.parse("17/07/2020"))));


		}
*/





		/*@Override
		public void run(String... args) throws Exception {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			if (themaRepository.count() == 0) {
				// save a list of Customers
				themaRepository.saveAll(Arrays.asList(new Organisation((long) 18, "Theme1",
						"number 1" , df.parse("17/07/2020")));




			}


		}*/
}

