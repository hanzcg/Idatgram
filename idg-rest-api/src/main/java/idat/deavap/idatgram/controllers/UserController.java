package idat.deavap.idatgram.controllers;

import idat.deavap.idatgram.model.entity.DataResponse;
import idat.deavap.idatgram.model.entity.User;
import idat.deavap.idatgram.model.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/all")
    public ResponseEntity<?> getAll() {
        DataResponse data = new DataResponse(HttpStatus.OK);
        
        try {
            Optional<List<User>> list = userRepository.getAll();
            if (list.isPresent() && !list.get().isEmpty())
                data.setData(list);
            else 
                data.setStatus(HttpStatus.NO_CONTENT);

            return new ResponseEntity(data, HttpStatus.OK);
        } catch (Exception e) {
            data.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            data.setData(e.getMessage());
            
            return new ResponseEntity(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(path="/id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        DataResponse data = new DataResponse(HttpStatus.OK);
        
        try {
            Optional<User> user = userRepository.getById(id);
            if (user.isPresent())
                data.setData(user);
            else 
                data.setStatus(HttpStatus.NOT_FOUND);

            return new ResponseEntity(data, HttpStatus.OK);
        } catch (Exception e) {
            data.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            data.setData(e.getMessage());
            
            return new ResponseEntity(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(path="/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable("email") String email) {
        DataResponse data = new DataResponse(HttpStatus.OK);
        
        try {
            Optional<User> user = userRepository.getByEmail(email);
            if (user.isPresent()) 
                data.setData(user);
            else
                data.setStatus(HttpStatus.NOT_FOUND);

            return new ResponseEntity(data, HttpStatus.OK);
        } catch (Exception e) {
            data.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            data.setData(e.getMessage());
            
            return new ResponseEntity(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody User user) {
        DataResponse data = new DataResponse(HttpStatus.CREATED);
        
        try {
            Optional<User> usr = userRepository.getByEmail(user.getEmail());
            if(usr.isEmpty()){
                userRepository.add(
                    user.getEmail(), 
                    user.getName(), 
                    user.getLast_name()
                );
                
                return new ResponseEntity(data, HttpStatus.CREATED);
            } else {
                data.setStatus(HttpStatus.CONFLICT);
                data.setData("User already exists");

                return new ResponseEntity(data, HttpStatus.OK);
            }
        } catch (Exception e) {
            data.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            data.setData(e.getMessage());
            
            return new ResponseEntity(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/upd")
    public ResponseEntity<?> upd(@RequestBody User user) {
        DataResponse data = new DataResponse(HttpStatus.ACCEPTED);
        
        try {
            Optional<User> usr = userRepository.getById(user.getId());
            if(usr.isPresent()) 
                userRepository.upd(
                    user.getId(),
                    user.getEmail(), 
                    user.getName(), 
                    user.getLast_name()
                );
            else {
                data.setStatus(HttpStatus.NOT_FOUND);
                data.setData("User does not exist");
            }
            
            return new ResponseEntity(data, HttpStatus.OK);
        } catch (Exception e) {
            data.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            data.setData(e.getMessage());
            
            return new ResponseEntity(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/del")
    public ResponseEntity<?> del(@RequestBody  User user) {
        DataResponse data = new DataResponse(HttpStatus.ACCEPTED);
        
        try {
            Optional<User> usr = userRepository.getById(user.getId());
            if(usr.isPresent())
                userRepository.del(user.getId());
            else {
                data.setStatus(HttpStatus.NOT_FOUND);
                data.setData("User does not exist");
            }
            
            return new ResponseEntity(data, HttpStatus.OK);
        } catch (Exception e) {
            data.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            data.setData(e.getMessage());
            
            return new ResponseEntity(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
