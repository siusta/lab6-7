package pl.siusta.why.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.siusta.why.model.User;
import pl.siusta.why.repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepo userRepo;

    public UserDetailsServiceImpl(UserRepo userRepo){this.userRepo=userRepo;}

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User username = userRepo.findUserByUsername(s);
        if (username!=null) {
            return username;
        }
        throw new UsernameNotFoundException("User not found");
    }

}
