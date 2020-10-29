package com.pfe.microserviceusers.controller;

import com.pfe.microserviceusers.exceptions.BadRequestException;
import com.pfe.microserviceusers.models.embedded.Photo;
import com.pfe.microserviceusers.models.enumuration.RoleName;
import com.pfe.microserviceusers.models.Candidat;
import com.pfe.microserviceusers.models.Manager;
import com.pfe.microserviceusers.models.User;
import com.pfe.microserviceusers.requests.ApiResponse;
import com.pfe.microserviceusers.requests.RegistrationForm;
import com.pfe.microserviceusers.requests.ResetPassword;
import com.pfe.microserviceusers.requests.ResetPasswordRequest;
import com.pfe.microserviceusers.service.EmailSenderService;
import com.pfe.microserviceusers.service.UserService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    private final Logger log = LoggerFactory.getLogger(User.class);

    @PostMapping(value = "/signup")
    public User signUp(@Valid @RequestBody RegistrationForm data) throws IOException
    {
        String username=data.getUsername();
        String email=data.getEmail();
        User user=userService.findByUsernameOrEmail(username,email);
        if(user!=null)
        {
            throw new BadRequestException("This user already exists, Try with another user");
        }
        String password=data.getPassword();
        String repassword=data.getRepassword();
        if(!password.equals(repassword))
        {
            throw new RuntimeException("You must confirm your password");
        }
        if(data.getRoleName()==RoleName.CANDIDAT)
        {
            Candidat candidat=new Candidat();
            candidat.setAddress(data.getAddress());
            candidat.setDate_naissance(data.getDate_naissance());
            candidat.setDiplome(data.getDiplome());
            candidat.setInstitut(data.getInstitut());
            candidat.setNiveau(data.getNiveau());
            candidat.setPassword(password);
            candidat.setUsername(username);
            candidat.setEmail(email);
            candidat.setName(data.getName());
            candidat.setLastName(data.getLastName());
            candidat.setTelephone(data.getTelephone());
            candidat.setRole(RoleName.CANDIDAT);
            userService.saveUser(candidat);
            emailSenderService.sendConfirmationEmail(candidat);
            return candidat;
        }
        else if(data.getRoleName()==RoleName.MANAGER)
        {
            Manager manager=new Manager();
            manager.setPassword(password);
            manager.setUsername(username);
            manager.setEmail(email);
            manager.setName(data.getName());
            manager.setLastName(data.getLastName());
            manager.setTelephone(data.getTelephone());
            manager.setNameEntreprise(data.getNameEntreprise());
            manager.setRole(RoleName.MANAGER);
            userService.saveUser(manager);
            emailSenderService.sendConfirmationEmail(manager);
            return manager;
        }
        throw new BadRequestException("User not registred!! Check your form of registration!");
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/addPhoto/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public User updatePhoto(@RequestParam("file") MultipartFile file,@PathVariable Long id) throws IOException
    {
        return userService.findById(id).map(u->{
            //get details of photos and encoding it
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String type=file.getContentType();
            byte[] fileContent = new byte[0];
            try {
                fileContent = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            Photo photo=new Photo(fileName,type,encodedString);
            return userService.addPhotoToUser(u,photo);
        }).orElseThrow(()-> new BadRequestException("This user does not exist!"));

    }
    @GetMapping("/findByUsername/{username}")
    public User findByUsername(@Valid @PathVariable String username)
    {
        User user=userService.findByUsername(username);
        if(user==null)
        {
            throw new BadRequestException("This user not exists, Try with another user");
        }
        return user;
    }

    @GetMapping("/getEncoded/{id}")
    public String encodedImageOfUser(@PathVariable Long id)
    {
        String encodedStringOfImage=userService.encodedStringOfImage(id);
        if(encodedStringOfImage==null)
        {
            throw new BadRequestException("This user not exists, Try with another user");
        }
        return encodedStringOfImage;
    }

    @GetMapping("/findByUsernameOrEmail/{usernameOrEmail}")
    public User findByUsernameOrEmail(@Valid @PathVariable String usernameOrEmail)
    {
        User user=userService.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail);
        if(user==null)
        {
            throw new BadRequestException("This user not exists, Try with another user");
        }
        return user;
    }
    @GetMapping("/findById/{id}")
    public User findById(@Valid @PathVariable Long id)
    {
        return userService.findById(id)
                .orElseThrow(()->new BadRequestException("This user not exists, Try with another user"));
    }

    @GetMapping("/findAllUsers")
    public Page<User> findAllUsers(Pageable pageable)
    {
        return userService.findAll(pageable);
    }

    // compter le nbr de users sur mysql
    @GetMapping("/count")
    public long count(String name) {
        return userService.countByUsername(name);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id)
    {
        return userService.findById(id).map(user->{
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new BadRequestException("This user not exists!"));
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id)
    {
        return userService.findById(id).map( u -> {
            u.setLastName(user.getLastName());
            u.setName(user.getName());
            u.setTelephone(user.getTelephone());
            u.setUsername(user.getUsername());
            u.setLoacked(user.isLoacked());
            return userService.updateUser(u);
        }).orElseThrow(()-> new BadRequestException("This user not exists!"));
    }
    @PutMapping("/update-password/{id}")
    public ResponseEntity<Object> updatePassword(@RequestBody ResetPasswordRequest resetPasswordRequest, @PathVariable Long id)
    {
        if(!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmNewPassword()))
        {
            throw new RuntimeException("You must confirm your password");
        }
        userService.findById(id).map( u -> {
            u.setPassword(resetPasswordRequest.getNewPassword());
            userService.saveUser(u);
            return u;
        }).orElseThrow(()-> new BadRequestException("This user not exists!"));
        return new ResponseEntity<>(
                new ApiResponse("Your password has been successfully changed!",true),
                HttpStatus.OK);
    }

    @PostMapping("/reset")
    public void resetPassword(@RequestBody ResetPassword email)
    {
        User user=userService.findByEmail(email.getEmail());
        if(user==null)
        {
            throw new BadRequestException("This user not exists");
        }
        emailSenderService.sendResetPasswordEmail(user);
    }

    /*@GetMapping("/countUsers")
        public  User countAllUsers (){
        return userService.countAllUsers();

    }*/


}
