package com.example.demo.Service.User;

import com.example.demo.Infrastructure.ConstValue;
import com.example.demo.Infrastructure.Hash;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class UserService implements IUserService   {
    @Autowired
    private UserRepository userRepository;
    private static final int round = 1000000;
    private static final int keyLength = 256;
    private static final byte[] saltUsername = new byte[1];
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void registerUser(String username,String password){
           SecureRandom random = new SecureRandom();
           byte[] saltPassword = new byte[16];
           random.nextBytes(saltPassword);
           String hashPassword = Hash.Encrypt(password,saltPassword,round,keyLength);
           String hashSalt = Hash.saltEncrypt(saltPassword);
           String hashUsername = Hash.Encrypt(username,saltUsername,1,keyLength).replace("/","");

           String path = ConstValue.CurrentDirectory.concat(ConstValue.UserPath).concat("\\"+hashUsername);
           createDirectoryUser(path);
           createFileSalt(path+"\\salt.txt",hashSalt);

           User user = new User();
           user.setUsername(username);
           user.setPassword(hashPassword);
           user = userRepository.save(user);
    }
    public void login(String username, String password) throws AccessDeniedException {
          User user = userRepository.findUserByUsername(username);
          String userDir = Hash.Encrypt(username,saltUsername,1,keyLength).replace("/","");
          String strSalt = readFileSalt(ConstValue.CurrentDirectory.concat(ConstValue.UserPath).concat("\\"+userDir+"\\salt.txt"));
          byte[] salt = Base64.getDecoder().decode(strSalt);
          String pwd = Hash.Encrypt(password,salt,round,keyLength);
          if (user.getPassword().equals(pwd)) {
              System.out.println("get Token !!");
          } else {
              throw new AccessDeniedException("Invalid username or password");
          }

    }
    void createDirectoryUser(String pathName){
        File userDir = new File(pathName);
        boolean created =  userDir.mkdirs();
        if(created)
            System.out.println("Folder was created !");
        else
            System.out.println("Unable to create folder");
    }
    void createFileSalt(String pathName,String hashSalt){
        try{
            FileWriter fw = new FileWriter(pathName);
            fw.write(hashSalt);
            fw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    String readFileSalt(String pathName){
        StringBuilder result = new StringBuilder();
        try {
           List<String> a = Files.readAllLines(Paths.get(pathName));
           for (String item : a){
               result.append(item);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
