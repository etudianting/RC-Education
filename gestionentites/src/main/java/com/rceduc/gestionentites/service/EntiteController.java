package com.rceduc.gestionentites.service;


//import com.rceduc.gestionentites.entities.View;
import net.sf.jasperreports.engine.JRException;
        import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;


        import com.rceduc.gestionentites.dao.EntiteRepository;
import com.rceduc.gestionentites.entities.Entite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


        import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.FileNotFoundException;
        import java.util.*;

@RestController
@RequestMapping("/entites")
public class EntiteController {
    @Autowired
    private EntiteRepository entiteRepository;
    @Autowired
    FilesStorageService storageService;

    @Autowired
    private ReportService service;





    // trouvez tous les entités
    @GetMapping("/getAll")
    public List<Entite> getAll() {
        return this.entiteRepository.findAll();
    }


    // trouver par titre
    @GetMapping("/getByTitre/{titre}")
    public List<Entite> getAllByTitre(@PathVariable(value = "titre") String titre) {
        return this.entiteRepository.findByTitre(titre);
    }


    @GetMapping("/getByAuteur/{auteur}")
    public List<Entite> getAllByAuteur(@PathVariable(value = "auteur") String auteur) {
        return this.entiteRepository.findByAuteur(auteur);
    }


    /*@GetMapping("/getByDate/{datecreation}")
    public List<Entite> getAllByDate(@PathVariable(value = "datecreation") LocalDateTime datecreation) {
        return this.entiteRepository.findByDate(datecreation);
    }*/

    @GetMapping("/getBymainteneur/{mainteneur}")
    public List<Entite> getAllByMainteneur(@PathVariable(value = "mainteneur") String mainteneur) {
        return this.entiteRepository.findByMainteneur(mainteneur);
    }


    @GetMapping("/getByemailm/{emailmainteneur}")
    public List<Entite> getAllByemailM(@PathVariable(value = "emailmainteneur") String emailmainteneur) {
        return this.entiteRepository.findByemailmainteneur(emailmainteneur);
    }


    @GetMapping("/getByemaila/{emailauteur}")
    public List<Entite> getAllByemailA(@PathVariable(value = "emailauteur") String emailauteur) {
        return this.entiteRepository.findByemailauteur(emailauteur);
    }







    //pagination
    // pagination et tri
    @GetMapping("/titres")
    public ResponseEntity<Map<String, Object>> getAllEntities(
            @RequestParam(required = false) String titre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {

        try {
            List<Entite> entites = new ArrayList<Entite>();
            Pageable paging = PageRequest.of(page, size);

            Page<Entite> pageTuts;
            if (titre == null)
                pageTuts = entiteRepository.findAll(paging);
            else
                pageTuts = entiteRepository.findByTitre(titre, paging);

            entites = pageTuts.getContent();

            if (entites.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("entites", entites);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/auteurs")
    public ResponseEntity<Map<String, Object>> getAllEntities1(
            @RequestParam(required = false) String auteur,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {

        try {
            List<Entite> entites = new ArrayList<Entite>();
            Pageable paging = PageRequest.of(page, size);

            Page<Entite> pageTuts;
            if (auteur == null)
                pageTuts = entiteRepository.findAll(paging);
            else
                pageTuts = entiteRepository.findByAuteur(auteur, paging);

            entites = pageTuts.getContent();

            if (entites.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("entites", entites);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    // tri et filter


    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }


    @GetMapping("/entitees")
    public ResponseEntity<Map<String, Object>> getAllEntitesPage(
            @RequestParam(required = false) String titre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            List<Order> orders = new ArrayList<Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Entite> entites = new ArrayList<Entite>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Entite> pageTuts;
            if (titre == null)
                pageTuts = entiteRepository.findAll(pagingSort);
            else
                pageTuts = entiteRepository.findByTitreContaining(titre, pagingSort);

            entites = pageTuts.getContent();

            if (entites.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("entites", entites);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    //reporting files au format pdf

    @GetMapping("/getEntites")
    public List<Entite> getEntites() {

        return entiteRepository.findAll();
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return service.exportReport(format);
    }

    @Autowired
    CSVService fileService;

// csv files
@GetMapping("/csv")
public ResponseEntity<Resource> getFile() {
    String filename = "entites.csv";
    InputStreamResource file = new InputStreamResource(fileService.load());

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/csv"))
            .body(file);
}


}










